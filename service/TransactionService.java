package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Transaction;
import models.TypeTransaction;
import dao.TransactionDao;

public class TransactionService {
  private static TransactionDao transactionDao = new TransactionDao();

  public List<Transaction> listTransactions() {
    ArrayList<Transaction> transactionList = transactionDao.getList();
    return transactionList
        .stream()
        .sorted(Comparator.comparing(Transaction::getDate))
        .collect(Collectors.toList());
  }

  public Map<TypeTransaction, List<Transaction>> groupedBy() {
    ArrayList<Transaction> transactionList = transactionDao.getList();
    return transactionList.stream()
        .collect(Collectors.groupingBy(Transaction::getType));
  }

  public Transaction getTransactionById(int id) {
    return transactionDao.getById(id).orElse(null);
  }

  public void addTransaction(Transaction transaction) {
    transactionDao.add(transaction);
  }

  public void modifyTransaction(Transaction transaction) {
    transactionDao.update(transaction);
  }

  public void deleteTransaction(int id) {
    transactionDao.delete(id);
  }

  public Map<Integer, Double> sumTransaction() {
    return transactionDao.getList()
        .stream()
        .collect(Collectors.groupingBy(Transaction::getIdCompte, Collectors.summingDouble(Transaction::getMontant)));
  }

  private List<Transaction> montantEleve(List<Transaction> all) {
    return all.stream()
        .filter(n -> n.getMontant() > 1000)
        .collect(Collectors.toList());
  }

  private Map<LocalDate, Long> toofrequent(List<Transaction> all, int id) {
    Map<LocalDate, Long> dates = all.stream()
        .filter(n -> n.getIdCompte() == id)
        .collect(Collectors.groupingBy(Transaction::getDate, Collectors.counting()));
    return dates;
  }

  private String differentLieu(List<Transaction> all, int id) {
    Optional<Map.Entry<String, Long>> findLieu = all.stream()
        .filter(n -> n.getIdCompte() == id)
        .collect(Collectors.groupingBy(Transaction::getLieu, Collectors.counting()))
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue());
    String mainLieu = "";
    if (findLieu.isPresent()) {
      mainLieu = findLieu.get().getKey();
    }
    return mainLieu;
  }

  public void susTransactions() {
    List<Transaction> all = transactionDao.getList();

    List<Transaction> montantEleves = montantEleve(all);

    List<Transaction> lieuxInhabituels = new ArrayList<>();
    List<Transaction> frequencesExcessives = new ArrayList<>();
    for (Transaction transaction : all) {
      if (!transaction.getLieu().equals(differentLieu(all, transaction.getIdCompte()))) {
        lieuxInhabituels.add(transaction);
      }
      if (toofrequent(all, transaction.getIdCompte())
          .values()
          .stream()
          .anyMatch(n -> n > 3)) {
        frequencesExcessives.add(transaction);
      }
    }

    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│     Montant eleve (>1000)           │");
    System.out.println("├─────────────────────────────────────┤");
    if (montantEleves.isEmpty()) {
      System.out.println("│     (aucune transaction)            │");
    } else {
      montantEleves.forEach(System.out::println);
    }

    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│ Lieu different de l'habituel        │");
    System.out.println("├─────────────────────────────────────┤");
    if (lieuxInhabituels.isEmpty()) {
      System.out.println("│     (aucune transaction)            │");
    } else {
      lieuxInhabituels.forEach(System.out::println);
    }

    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│ Frequence excessive (>3 par jour)   │");
    System.out.println("├─────────────────────────────────────┤");
    if (frequencesExcessives.isEmpty()) {
      System.out.println("│     (aucune transaction)            │");
    } else {
      frequencesExcessives.forEach(System.out::println);
    }
  }
}
