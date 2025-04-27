# File Transfer Project

## Description

This project implements a simple file transfer system with two components:
- **FileReceiverServer**: A server that listens for incoming connections and receives files from clients.
- **FileSenderClient**: A client that allows users to send files to the server.

The client and server communicate over a TCP connection and exchange file data in chunks.

## How to Run

1. **Start the Server**: Run the `FileReceiverServer` to start the server on port 1234.
   ```bash
   javac FileReceiverServer.java
   java FileReceiverServer
   
   ``` 
2. **Start the Client**: Run the `FileSenderClient` and provide the path of the file you want to send.
    ```bash
    javac FileSenderClient.java
    java FileSenderClient
