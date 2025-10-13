package Packageclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 1900;

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connecté au serveur " + HOST + ":" + PORT);

            // Option A: on lit toute l'opération sur une ligne
            System.out.print("Entrez une opération (ex: 55 * 25) : ");
            String operation = sc.nextLine().trim();

            // Validation côté client (empêche d'envoyer des chaînes manifestement invalides)
            String regex = "\\s*-?\\d+\\s*[+\\-*/]\\s*-?\\d+\\s*";
            if (!operation.matches(regex)) {
                System.out.println("Syntaxe invalide côté client. Format attendu : <entier> <op> <entier>");
                return;
            }

            // Envoi de l'opération au serveur
            out.println(operation);

            // Lire la réponse du serveur
            String response = in.readLine();
            if (response == null) {
                System.out.println("Aucune réponse du serveur.");
            } else {
                System.out.println("Réponse du serveur : " + response);
            }

        } catch (IOException e) {
            System.err.println("Erreur socket : " + e.getMessage());
        }
    }
}
