# GateWay To Multiple Web Socket Servers

Clients in public area sends Web Socket Requests with query parameter which including web socket ip in a protected network.
This application takes this requests as a Web Socket Server and redirect to other web socket servers in local network.

Client1 send a requests as "ws://localhost:8080/name?websocketip=ws://echo.websocket.org"
For testing we use demo websockets is always running.Ip of "ws://echo.websocket.org" is a test ip.
Gateway opens connection and create a WebSocketClient for target web socket("ws://echo.websocket.org").
For message traffic controlling, put this client session and websocketClient a hashmap.
When same client sends a message our Gateway Web Socket server find the WebsocketClient of this client session from Hashmap,
and sends message by using this WebSocketClient.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

java-1.11.0-openjdk-amd64 (or other versions.)
Intellij Idea (or Eclipse other versions.)

### Installing

A step by step series of examples that tell you how to get a development env running

Install java-1.11.0-openjdk-amd64 <br />
Install Intellij Idea<br />
clone https://github.com/ZekaiUregen/GateWayToWebSockets.git


## Running the tests

At first, run the application as a Web socket Server. 
	-run as java application src/main/java/ config.Application.java.
	 by this, embeded Tomcat server will start tu run and listen client requests. 

After then, send client requests 
        -run as java application src/test/java Client1.java. Then watch Server console.
        -run as java application src/test/java Client2.java. Then watch Server console. 

### Break down into end to end tests

By these test,
       Client1 send a requests as "ws://localhost:8080/name?websocketip=ws://echo.websocket.org"
       for testing we use demo websockets is always running.Ip of "ws://echo.websocket.org" is a test ip.
       Gateway opens connection and create a WebSocketClient for target web socket("ws://echo.websocket.org").
       For message traffic controlling put this client session and websocketClient a hashmap.
       When same client sends a message our Gateway Web Socket server find the WebsocketClient of this client session from Hashmap,
       and sends message by using this WebSocketClient.          



## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Zekai Imran Uregen** - *Initial work* - (https://github.com/ZekaiUregen)


