import java.util.Scanner;
import java.time.LocalDate;
import service.ClientService;
import service.CompteService;
import service.TransactionService;
import models.Client;
import models.Compte;
import models.CompteCourant;
import models.CompteEpargne;
import models.Transaction;
import models.TypeTransaction;

public class Main {

  public static Scanner scanner = new Scanner(System.in);
  public static ClientService clientService = new ClientService();
  public static CompteService compteService = new CompteService();
  public static TransactionService transactionService = new TransactionService();

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
      case "4" -> deleteClientUI();
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
    Client client = clientService.getClientById(id);

    System.out.println("Nom du clients");
    String nom = scanner.nextLine();
    client.setNom(nom);
    System.out.println("Email du clients");
    String email = scanner.nextLine();
    client.setEmail(email);
    clientService.modifyClient(client);
    System.out.println("client modifie avec success");
  }

  public static void deleteClientUI() {
    System.out.println("Id du client:");
    int id = scanner.nextInt();
    scanner.nextLine();
    clientService.deleteClient(id);
    System.out.println("client suppimre avec success");
  }

  public static void addCompteUI() {
    System.out.println("Numero du compte");
    String numero = scanner.nextLine();
    System.out.println("Solde du compte");
    double solde = scanner.nextDouble();
    scanner.nextLine();
    System.out.println("Id du client");
    int idClient = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Type du compte (1=courant, 2=epargne)");
    String type = scanner.nextLine();
    if ("1".equals(type)) {
      System.out.println("Decouvert autorise");
      double decouvertAutorise = scanner.nextDouble();
      scanner.nextLine();
      compteService.addCompte(new CompteCourant(0, numero, solde, idClient, decouvertAutorise));
    } else {
      System.out.println("Taux interet");
      double tauxInteret = scanner.nextDouble();
      scanner.nextLine();
      compteService.addCompte(new CompteEpargne(0, numero, solde, idClient, tauxInteret));
    }
    System.out.println("compte ajoute avec success");
  }

  public static void modifyCompteUI() {
    System.out.println("Id du compte:");
    int id = scanner.nextInt();
    scanner.nextLine();
    Compte compte = compteService.getCompteById(id);

    System.out.println("Numero du compte");
    String numero = scanner.nextLine();
    compte.setNumero(numero);
    System.out.println("Solde du compte");
    double solde = scanner.nextDouble();
    scanner.nextLine();
    compte.setSolde(solde);
    System.out.println("Id du client");
    int idClient = scanner.nextInt();
    scanner.nextLine();
    compte.setIdClient(idClient);
    if (compte instanceof CompteCourant cc) {
      System.out.println("Decouvert autorise");
      double decouvertAutorise = scanner.nextDouble();
      scanner.nextLine();
      cc.setDecouvertAutorise(decouvertAutorise);
    } else if (compte instanceof CompteEpargne ce) {
      System.out.println("Taux interet");
      double tauxInteret = scanner.nextDouble();
      scanner.nextLine();
      ce.setTauxInteret(tauxInteret);
    }
    compteService.modifyCompte(compte);
    System.out.println("compte modifie avec success");
  }

  public static void addTransactionUI() {
    System.out.println("Date de la transaction (yyyy-MM-dd)");
    LocalDate date = LocalDate.parse(scanner.nextLine());
    System.out.println("Montant de la transaction");
    double montant = scanner.nextDouble();
    scanner.nextLine();
    System.out.println("Type de la transaction (VERSEMENT, RETRAIT, VIREMENT)");
    String type = scanner.nextLine().toUpperCase();
    System.out.println("Lieu de la transaction");
    String lieu = scanner.nextLine();
    System.out.println("Id du compte");
    int idCompte = scanner.nextInt();
    scanner.nextLine();
    transactionService.addTransaction(new Transaction(0, date, montant, TypeTransaction.valueOf(type), lieu, idCompte));
    System.out.println("transaction ajoute avec success");
  }

  public static void modifyTransactionUI() {
    System.out.println("Id de la transaction:");
    int id = scanner.nextInt();
    scanner.nextLine();
    Transaction transaction = transactionService.getTransactionById(id);

    System.out.println("Montant de la transaction");
    double montant = scanner.nextDouble();
    scanner.nextLine();
    transaction.setMontant(montant);
    System.out.println("Type de la transaction (VERSEMENT, RETRAIT, VIREMENT)");
    String type = scanner.nextLine().toUpperCase();
    transaction.setType(TypeTransaction.valueOf(type));
    System.out.println("Lieu de la transaction");
    String lieu = scanner.nextLine();
    transaction.setLieu(lieu);
    System.out.println("Id du compte");
    int idCompte = scanner.nextInt();
    scanner.nextLine();
    transaction.setIdCompte(idCompte);
    transactionService.modifyTransaction(transaction);
    System.out.println("transaction modifie avec success");
  }

  public static void deleteTransactionUI() {
    System.out.println("Id de la transaction:");
    int id = scanner.nextInt();
    scanner.nextLine();
    transactionService.deleteTransaction(id);
    System.out.println("transaction suppime avec success");
  }

  public static void afficherMenuComptes() {
    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│      Gestion des Comptes            │");
    System.out.println("├─────────────────────────────────────┤");
    System.out.println("│1❯ Lister les Comptes                │");
    System.out.println("│2❯ Ajouter un Compte                 │");
    System.out.println("│3❯ Modifier un Compte                │");
    System.out.println("│4❯ min/max solde compte              │");
    System.out.println("└─────────────────────────────────────┘");
    switch (scanner.nextLine()) {
      case "1" -> compteService.listComptes().forEach(System.out::println);
      case "2" -> addCompteUI();
      case "3" -> modifyCompteUI();
      default -> System.out.println("choisir un option appropriat");
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
    System.out.println("│5❯ Grouper Transaction par type      │");
    System.out.println("│6❯ Somme des Transaction             │");
    System.out.println("└─────────────────────────────────────┘");
    switch (scanner.nextLine()) {
      case "1" -> transactionService.listTransactions().forEach(System.out::println);
      case "2" -> addTransactionUI();
      case "3" -> modifyTransactionUI();
      case "4" -> deleteTransactionUI();
      case "5" -> transactionService.groupedBy().forEach((key, value) -> System.out.println(key + " " + value));
      case "6" -> transactionService.sumTransaction()
          .forEach((key, value) -> System.out.println("Id " + key + ": moyen " + value));
      default -> System.out.println("choisir un option appropriat");
    }
  }
}
