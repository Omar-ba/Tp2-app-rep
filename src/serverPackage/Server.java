package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 1900; // ou le port que vous utilisez

        System.out.println("Serveur démarré. En attente de connexions sur le port " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {

                    System.out.println("Client connecté : " + clientSocket.getRemoteSocketAddress());

                    // Lire l'opération envoyée par le client sous forme "operand1 operator operand2"
                    String operation = in.readLine(); // exemple: "55 * 25"
                    System.out.println("Reçu du client : " + operation);

                    