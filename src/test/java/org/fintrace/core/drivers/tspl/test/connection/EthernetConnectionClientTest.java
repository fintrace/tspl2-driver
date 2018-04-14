package org.fintrace.core.drivers.tspl.test.connection;

import lombok.extern.slf4j.Slf4j;
import org.fintrace.core.drivers.tspl.commands.status.TSPLStatusPollCommands;
import org.fintrace.core.drivers.tspl.connection.EthernetConnectionClient;
import org.fintrace.core.drivers.tspl.connection.TSPLConnectionClient;
import org.fintrace.core.drivers.tspl.exceptions.ConnectionClientException;
import org.fintrace.core.drivers.tspl.listeners.ClientListener;
import org.fintrace.core.drivers.tspl.listeners.DataListener;
import org.fintrace.core.drivers.tspl.test.TcpServerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.fintrace.core.drivers.tspl.DriverConstants.LF;

/**
 * @author Venkaiah Chowdary Koneru
 */
@Slf4j
public class EthernetConnectionClientTest {

    private TcpServerMock server;
    private TSPLConnectionClient connectionClient;

    @BeforeEach
    public void startTCPEmulator() throws IOException, InterruptedException {
        server = new TcpServerMock(9100);
        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                log.error("Error starting mock server", e);
            }
        }).start();

        while (!server.isRunning()) {
            log.info("Waiting for the server to bootstrap");
            Thread.sleep(1000);
        }

        connectionClient = new EthernetConnectionClient("localhost", 9100);
        connectionClient.addClientListener(new ClientListener() {
            @Override
            public void connectionEstablished(TSPLConnectionClient client) {
                log.info("Connection established");
            }

            @Override
            public void connectionLost(TSPLConnectionClient client) {
                System.out.println("Connection lost");
            }

            @Override
            public void connectionIsFailing(TSPLConnectionClient client, Exception e) {
                log.info("Connection is failing");
            }
        });

        connectionClient.addDataListener(new DataListener() {
            @Override
            public void messageSent(String message) {
                System.out.println("Message " + message + " sent to printer");
            }

            @Override
            public void messageReceived(String message) {
                log.info("Message {} received from printer", message);
            }

            @Override
            public void messageSendFailed(ConnectionClientException exception, String messageToSend) {
                log.info("Message {} failed to send to printer", messageToSend);
            }
        });
        connectionClient.init();
        connectionClient.connect();

        while (!connectionClient.isConnected()) {
            log.info("Waiting for connection");
            Thread.sleep(1000);
        }
    }

    @AfterEach
    public void clear() throws InterruptedException {
        connectionClient.disconnect();

        while (connectionClient.isConnected()) {
            log.info("Waiting for disconnection");
            Thread.sleep(1000);
        }
        connectionClient.shutdown();
        server.stop();
    }

    @Test
    public void checkStatus() {
        connectionClient.send(TSPLStatusPollCommands.STATUS.getCommand() + LF);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
