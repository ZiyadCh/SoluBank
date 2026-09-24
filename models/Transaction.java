package models;

public class Transaction {
  private int id;
  private double montant;
  private TypeTransaction type;
  private String lieu;
  private int idCompte;

  public Transaction(int id, double montant, TypeTransaction type, String lieu, int idCompte) {
    this.id = id;
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

  @Override
  public String toString() {
    return "============\nId: " + this.id + " \nMontant: " + this.montant
        + "\nType: " + this.type + "\nLieu: " + this.lieu + "\nIdCompte: " + this.idCompte;
  }
}
