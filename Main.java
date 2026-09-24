import java.util.Scanner;
import service.ClientService;
import models.Client;

public class Main {

  public static Scanner scanner = new Scanner(System.in);
  public static ClientService clientService = new ClientService();

  public static void main(String[] args) {

    while (true) {
      System.out.println("┌─────────────────────────────────────┐");
      System.out.println("│      Selectionnez une action        │");
      System.out.println("├─────────────────────────────────────┤");
      System.out.println("│1❯ Gestion des Clients               │");
      System.out.println("│2❯ Gestion des Comptes               │");
      System.out.println("│3❯ Gestion des Transactions          │");
      System.out.println("│4❯ Quitter                           │");
      System.out.println("└─────────────────────────────────────┘");
      switch (scanner.nextLine()) {
        case "1" -> afficherMenuClients();
        case "2" -> afficherMenuComptes();
        case "3" -> afficherMenuTransactions();
        case "4" -> {
          System.out.println("au revoir");
          return;
        }
        default -> System.out.println("choisir un option appropriat");
      }
    }
  }

  public static void afficherMenuClients() {
    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│      Gestion des Clients            │");
    System.out.println("├─────────────────────────────────────┤");
    System.out.println("│1❯ Lister les Clients                │");
    System.out.println("│2❯ Ajouter un Client                 │");
    System.out.println("│3❯ Modifier un Client                │");
    System.out.println("│4❯ Supprimer un Client               │");
    System.out.println("└─────────────────────────────────────┘");
    switch (scanner.nextLine()) {
      case "1" -> clientService.listClients().forEach(System.out::println);
      case "2" -> addClientUI();
      case "3" -> modifyClientUI();
      default -> System.out.println("choisir un option appropriat");
    }
  }

  public static void addClientUI() {
    System.out.println("Nom du clients");
    String nom = scanner.nextLine();
    System.out.println("Email du clients");
    String email = scanner.nextLine();
    clientService.addClient(new Client(nom, email));
    System.out.println("client ajoute avec success");
  }

  public static void modifyClientUI() {
    System.out.println("Id du client:");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Nom du clients");
    String nom = scanner.nextLine();
    System.out.println("Email du clients");
    String email = scanner.nextLine();
    clientService.addClient(new Client(nom, email));
    System.out.println("client ajoute avec success");
  }

  public static void afficherMenuComptes() {
    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│      Gestion des Comptes            │");
    System.out.println("├─────────────────────────────────────┤");
    System.out.println("│1❯ Lister les Comptes                │");
    System.out.println("│2❯ Ajouter un Compte                 │");
    System.out.println("│3❯ Modifier un Compte                │");
    System.out.println("│4❯ Supprimer un Compte               │");
    System.out.println("└─────────────────────────────────────┘");
    switch (scanner.nextLine()) {
      default:
        System.out.println("choisir un option appropriat");
    }
  }

  public static void afficherMenuTransactions() {
    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│   Gestion des Transactions          │");
    System.out.println("├─────────────────────────────────────┤");
    System.out.println("│1❯ Lister les Transactions           │");
    System.out.println("│2❯ Ajouter une Transaction           │");
    System.out.println("│3❯ Modifier une Transaction          │");
    System.out.println("│4❯ Supprimer une Transaction         │");
    System.out.println("└─────────────────────────────────────┘");
    switch (scanner.nextLine()) {
      default:
        System.out.println("choisir un option appropriat");
    }
  }
}
