package service;

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

  private List<Transaction> montantEleve() {
    return transactionDao.getList()
        .stream()
        .filter(n -> n.getMontant() > 1000)
        .collect(Collectors.toList());
  }

  private String differentLieu(int id) {
    Optional<Map.Entry<String, Long>> findLieu = transactionDao.getList()
        .stream()
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

  public List<Transaction> susTransactions() {
    List<Transaction> suspects = new ArrayList<>();
    List<Transaction> lieus = new ArrayList<>();
    suspects.addAll(montantEleve());
    for (Transaction transaction : transactionDao.getList()) {
      if (!transaction.getLieu().equals(differentLieu(transaction.getIdCompte()))) {
        suspects.add(transaction);
      }

    }
    return suspects;
  }
}
