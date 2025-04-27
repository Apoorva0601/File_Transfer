import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileSenderClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the file path
        System.out.print("Enter the full file path to send: ");
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        // Check if the file exists before proceeding
        if (!file.exists()) {
            System.out.println("The specified file does not exist. Please check the path and try again.");
            return;
        }

        String serverAddress = "localhost"; // Server address (localhost or IP)
        int port = 1234; // Server port

        try (Socket socket = new Socket(serverAddress, port);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             FileInputStream fileInputStream = new FileInputStream(file)) {

            System.out.println("Connecting to the server...");

            // First send the filename
            dataOutputStream.writeUTF(file.getName());
            dataOutputStream.flush();

            // Then send the file data
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");

            dataOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
