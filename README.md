#  Projet Client/Serveur Java — Calculatrice via Objets Sérialisés

##  Description
Ce projet illustre une communication **Client/Serveur** en **Java** utilisant la **sérialisation d’objets** (`ObjectInputStream` / `ObjectOutputStream`).

Le client envoie une opération arithmétique (addition, soustraction, multiplication ou division) au serveur, qui effectue le calcul et renvoie le résultat.

---

##  Structure du projet


---

##  Fonctionnement

###  Côté Serveur (`Server.java`)
- Attend la connexion d’un client sur le **port 1900**.
- Reçoit un objet `Operation` contenant :
  - deux nombres (`op1`, `op2`)
  - un opérateur (`+`, `-`, `*`, `/`)
- Calcule le résultat.
- Envoie le résultat au client.

###  Côté Client (`Client.java`)
- Se connecte au serveur sur le port **1900**.
- Saisit deux nombres et un opérateur via la console.
- Envoie un objet `Operation` au serveur.
- Reçoit et affiche le résultat du calcul.

###  Classe partagée (`Operation.java`)
- Sert de modèle d’objet échangé entre le client et le serveur.
- Implémente `Serializable` pour permettre l’envoi d’objets via un flux réseau.

---

##  Prérequis

- **Java 17** ou plus récent
- Un IDE (Eclipse, IntelliJ, NetBeans) ou une console Java

---

##  Exécution du projet

### Étape 1 — Lancer le serveur
Dans un terminal ou IDE :
```bash
java Packageserver.Server
