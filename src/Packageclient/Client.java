package Packageclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import shared.Operation;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("0.0.0.0", 1900)) {
            System.out.println(" Connecté au serveur sur localhost:1900");

            // ⚠️ Même ordre que le serveur : OutputStream puis InputStream
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            System.out.print("Entrez le premier nombre : ");
            double a = sc.nextDouble();
            System.out.print("Entrez l’opérateur (+, -, *, /) : ");
            char op = sc.next().charAt(0);
            System.out.print("Entrez le deuxième nombre : ");
            double b = sc.nextDouble();

            // Envoi de l’objet
            Operation operation = new Operation(a, op, b);
            oos.writeObject(operation);
            oos.flush();

            // Lecture du résultat
            double resultat = ois.readDouble();
            System.out.println(" Résultat reçu du serveur : " + resultat);

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
