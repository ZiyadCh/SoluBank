package models;

public class Client {
  private int id;
  private String nom;
  private String email;

  public Client(int id, String nom, String email) {
    this.id = id;
    this.nom = nom;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "============\nId: " + this.id + " \nNom: " + this.nom + "\nEmail: " + this.email;
  }
}
