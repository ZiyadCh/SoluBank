package models;

import java.sql.Timestamp;

public class Transaction {
  private int id;
  private Timestamp date;
  private double montant;
  private TypeTransaction type;
  private String lieu;
  private int idCompte;

  public Transaction(int id, Timestamp date, double montant, TypeTransaction type, String lieu, int idCompte) {
    this.id = id;
    this.date = date;
    this.montant = montant;
    this.type = type;
    this.lieu = lieu;
    this.idCompte = idCompte;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }

  public double getMontant() {
    return montant;
  }

  public void setMontant(double montant) {
    this.montant = montant;
  }

  public TypeTransaction getType() {
    return type;
  }

  public void setType(TypeTransaction type) {
    this.type = type;
  }

  public String getLieu() {
    return lieu;
  }

  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  public int getIdCompte() {
    return idCompte;
  }

  public void setIdCompte(int idCompte) {
    this.idCompte = idCompte;
  }
}
