package Packageserver;

import java.io.*;
import java.net.*;
import shared.Operation;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(1900)) {
            System.out.println(" Serveur en attente d’un client...");

            Socket socket = ss.accept();
            System.out.println(" Client connecté.");

            //  Créer d’abord ObjectOutputStream, ensuite ObjectInputStream
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Réception de l’objet envoyé par le client
            Operation op = (Operation) ois.readObject();
            System.out.println(" Objet reçu : " + op.getOp1() + " " + op.getOperateur() + " " + op.getOp2());

            double resultat = 0;
            switch (op.getOperateur()) {
                case '+': 
                	resultat = op.getOp1() + op.getOp2(); break;
                case '-': 
                	resultat = op.getOp1() - op.getOp2(); break;
                case '*':
                	resultat = op.getOp1() * op.getOp2(); break;
                case '/':
                    if (op.getOp2() != 0) resultat = op.getOp1() / op.getOp2();
                    
                    else System.out.println(" Division par zéro !");
                    break;
                default:
                    System.out.println(" Opérateur invalide !");
            }

            // Envoi du résultat
            oos.writeDouble(resultat);
            oos.flush();

            System.out.println(" Résultat envoyé au client : " + resultat);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
