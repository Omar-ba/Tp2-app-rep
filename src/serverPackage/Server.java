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

                    if (operation == null) {
                        out.println("ERREUR: opération vide");
                        continue;
                    }

                    // Validation simple: regex pour "entier opérateur entier"
                    String regex = "\\s*(-?\\d+)\\s*([+\\-*/])\\s*(-?\\d+)\\s*";
                    if (!operation.matches(regex)) {
                        out.println("ERREUR: syntaxe invalide. Format attendu: <entier> <op> <entier> (ex: 55 * 25)");
                        continue;
                    }

                    // Extraire les parties
                    String[] parts = operation.trim().split("\\s+");
                    // Pour couvrir cas avec espaces variés, on retiendra les captures via parsing manuel :
                    java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
                    java.util.regex.Matcher m = p.matcher(operation);
                    if (!m.matches()) {
                        out.println("ERREUR: impossible d'interpréter l'opération.");
                        continue;
                    }
                    long op1 = Long.parseLong(m.group(1));
                    String operator = m.group(2);
                    long op2 = Long.parseLong(m.group(3));

                    // Calculer en prenant soin des exceptions (division par 0)
                    try {
                        String resultStr;
                        switch (operator) {
                            case "+":
                                resultStr = String.valueOf(op1 + op2);
                                break;
                            case "-":
                                resultStr = String.valueOf(op1 - op2);
                                break;
                            case "*":
                                resultStr = String.valueOf(op1 * op2);
                                break;
                            case "/":
                                if (op2 == 0) {
                                    resultStr = "ERREUR: division par zéro";
                                } else {
                                    // si on veut garder la partie décimale
                                    double resDiv = (double) op1 / (double) op2;
                                    // format propre : si entier, affiche entier sinon décimal
                                    if (op1 % op2 == 0) {
                                        resultStr = String.valueOf(op1 / op2);
                                    } else {
                                        resultStr = String.valueOf(resDiv);
                                    }
                                }
                                break;
                            default:
                                resultStr = "ERREUR: opérateur inconnu";
                        }
                        // Envoyer la réponse au client
                        out.println(resultStr);
                        System.out.println("Envoyé au client : " + resultStr);
                    } catch (ArithmeticException ae) {
                        out.println("ERREUR: " + ae.getMessage());
                    }

                } catch (IOException e) {
                    System.err.println("Erreur dans la gestion du client : " + e.getMessage());
                    // continuer à accepter d'autres clients
                }
            }
        } catch (IOException e) {
            System.err.println("Impossible de démarrer le serveur : " + e.getMessage());
        }
    }
}
