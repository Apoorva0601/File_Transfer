import java.io.*;
import java.net.*;

public class FileReceiverServer {
    public static void main(String[] args) {
        int port = 1234; // Port to listen for incoming connections

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected.");

                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                    // Read filename first
                    String fileName = dataInputStream.readUTF();
                    System.out.println("Receiving file: " + fileName);

                    // Create file output stream with received filename
                    FileOutputStream fileOutputStream = new FileOutputStream(fileName);

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    // Receive file data
                    while ((bytesRead = dataInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("File '" + fileName + "' received and saved successfully.");

                    fileOutputStream.close();
                    dataInputStream.close();
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
