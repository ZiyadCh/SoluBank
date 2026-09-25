package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import models.Transaction;
import models.TypeTransaction;
import dao.TransactionDao;

public class TransactionService {
  private static TransactionDao transactionDao = new TransactionDao();

  public ArrayList<Transaction> listTransactions() {
    ArrayList<Transaction> transactionList = transactionDao.getList();
    return transactionList;
  }

  public Map<TypeTransaction, List<Transaction>> groupedBy() {
    ArrayList<Transaction> transactionList = transactionDao.getList();
    return transactionList.stream()
        .collect(Collectors.groupingBy(Transaction::getType));
  }

  public Transaction getTransactionById(int id) {
    return transactionDao.getById(id);
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
}
