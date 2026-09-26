package service;

import models.Transaction;
import models.TypeTransaction;

import models.Client;
import dao.ClientDao;
import models.Compte;
import dao.CompteDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import dao.TransactionDao;

public class RapportService {
  private static ClientDao clientDao = new ClientDao();
  private static CompteDao compteDao = new CompteDao();
  private static TransactionDao transactionDao = new TransactionDao();

  public Map<Integer, List<Compte>> topFiveSolde() {
    return compteDao.getList()
        .stream()
        .sorted(Comparator.comparingDouble(Compte::getSolde).reversed())
        .limit(5)
        .collect(Collectors.groupingBy(Compte::getIdClient));

  }

  public Map<TypeTransaction, List<Transaction>> grouperPar() {
    return transactionDao.getList()
        .stream()
        .collect(Collectors.groupingBy(Transaction::getType));
  }
}
