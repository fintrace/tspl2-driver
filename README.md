tspl2-driver
============

This library will help you to communicate with TSPL2 (TSC brand) based printers.

This drivers supports communication with printer over 
* USB Communication
* Ethernet (TODO)
* Bluetooth (TODO)

How to use
=================

Add dependency in your pom

  	  
```xml

<dependency>
    <groupId>com.finium.core.drivers</groupId>
    <artifactId>tspl2-driver</artifactId>
    <version>0.0.2</version>
</dependency>
```


Create a USB Client

```java
TSPLConnectionClient tsplConnectionClient = new USBConnectionClient(
        (short) Integer.parseInt(properties().getProperty("tsc.vendor.id"), 16),
        (short) Integer.parseInt(properties().getProperty("tsc.product.id"), 16));

```

Initialize the printer with defaults
```java
tsplConnectionClient.init();
```

To Establish the connection
```java
tsplConnectionClient.connect();
```

Use any available commands
```java
tsplConnectionClient.send(TSPLStatusPollCommands.STATUS.getCommand());
```

to disconenct and shutdown
```java
tsplConnectionClient.disconnect();
tsplConnectionClient.shutdown();
```

This library also has a nice listener pattern to receive notifications 
for Data and Network. 

Implement [DataListener](src/main/java/org/fintrace/core/drivers/tspl/listeners/DataListener.java) to listen for data related events from printer.

Implement [ClientListener](src/main/java/org/fintrace/core/drivers/tspl/listeners/ClientListener.java) to listen for the network related events.


Other documentation
=================

Documentation about TSPL could be find here
http://www.tscprinters.com/cms/upload/download_en/TSPL_TSPL2_Programming.pdf
