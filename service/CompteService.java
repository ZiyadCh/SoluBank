package service;

import java.util.ArrayList;

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

}
