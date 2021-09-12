tspl2-driver
============

This library will help you to communicate with TSPL2 (by TSC) based printers.

This drivers supports communication with printer over 
* USB Communication
* Ethernet
* Bluetooth (TODO)

> **Disclaimer:** This is not official or supported by TSC Auto ID Technology Co., Ltd. 
> This work is originated purely because there are no equivalent libraries exists for java to communicate to TSPL based printers.

How to use
=================

[![Maven Central](https://img.shields.io/maven-central/v/org.fintrace.core.drivers/tspl2-driver.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22org.fintrace.core.drivers%22%20AND%20a:%22tspl2-driver%22)

Add dependency in your pom
  	  
```xml

<dependency>
    <groupId>org.fintrace.core.drivers</groupId>
    <artifactId>tspl2-driver</artifactId>
    <version>0.0.12</version>
</dependency>
```


Create a connection client

```java
// USB Client
TSPLConnectionClient tsplConnectionClient = new USBConnectionClient(
        (short) xxxx, 16), // vendor id of TSPL2 based printer
        (short) xxxx, 16)); // product id of TSPL2 based printer

// Or Ethernet Client

TSPLConnectionClient tsplConnectionClient = new EthernetConnectionClient("x.x.x.x", 9100);
```

Initialize the printer with defaults
```java
tsplConnectionClient.init();
```

Once initialized, Establish the connection
```java
tsplConnectionClient.connect();
```

You may use any available device config command to overwrite the printer defaults.

Once the connection is established, Either construct the label (using fluent API) or send the plain TSPL string to print the label.
```java
TSPLLabel tsplLabel = TSPLLabel.builder()
                .element(Size.builder().labelWidth(4f).labelLength(3f).build())
                .element(Gap.builder().labelDistance(0f).labelOffsetDistance(0f).build())
                .element(Direction.builder().printPositionAsFeed(Boolean.TRUE).build())
                .element(ClearBuffer.builder().build())
                .element(DataMatrix.builder().xCoordinate(10).yCoordinate(110).width(400)
                        .height(400).content("DMATRIX EXAMPLE 1").build())
                .element(DataMatrix.builder().xCoordinate(310).yCoordinate(110).width(400)
                        .height(400).moduleSize(6).content("DMATRIX EXAMPLE 2").build())
                .element(DataMatrix.builder().xCoordinate(10).yCoordinate(310).width(400)
                        .height(400).moduleSize(8).nbRows(18).nbCols(18)
                        .content("DMATRIX EXAMPLE 3").build())
                .element(Print.builder().nbLabels(1).nbCopies(1).build())
                .build();

tsplConnectionClient.send(tsplLabel);

```

The above will send the following TSPL2 code to printer 
```text
SIZE 4,3
GAP 0,0
DIRECTION 1
CLS
DMATRIX 10,110,400,400, "DMATRIX EXAMPLE 1"
DMATRIX 310,110,400,400,x6, "DMATRIX EXAMPLE 2"
DMATRIX 10,310,400,400,x8,18,18, "DMATRIX EXAMPLE 3"
PRINT 1,1
```


Additionally, You may use any available status poll commands to get the status of the printer. In order to get the 
status, you need to register the data listener so that the poll commands sends the result to listener.
```java
tsplConnectionClient.send(TSPLStatusPollCommands.STATUS.getCommand());
```

To disconnect and shutdown
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
or 
http://mediaform.de/fileadmin/support/handbuecher/Armilla/Handbuecher/TSC_TSPL_TSPL2_Programming.pdf

Contributions
=================
Contributions of any type are welcome. Please contact via issues to discuss further.