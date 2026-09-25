package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import models.Compte;
import dao.CompteDao;

public class CompteService {
  private static CompteDao compteDao = new CompteDao();

  public ArrayList<Compte> listComptes() {
    ArrayList<Compte> compteList = compteDao.getList();
    return compteList;
  }

  public Compte getCompteById(int id) {
    return compteDao.getById(id);
  }

  public void addCompte(Compte compte) {
    compteDao.add(compte);
  }

  public void modifyCompte(Compte compte) {
    compteDao.update(compte);
  }

  public Optional<Compte> maxSolde() {
    return compteDao.getList().stream().max(Comparator.comparingDouble(Compte::getSolde));
  }

  public Optional<Compte> minSolde() {
    return compteDao.getList().stream().min(Comparator.comparingDouble(Compte::getSolde));
  }

}
