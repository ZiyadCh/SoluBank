package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Compte;
import utils.Database;

public class CompteDao {

  public ArrayList<Compte> getList() {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("select * from compte");
        ResultSet rs = ps.executeQuery()) {
      ArrayList<Compte> list = new ArrayList<>();
      while (rs.next()) {
        int id = rs.getInt("id");
        String nom = rs.getString("nom");
        String email = rs.getString("email");
        Compte compte = new Compte(id, nom, email);
        list.add(compte);
      }
      return list;
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
      return null;
    }
  }

  public void add(Compte compte) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("insert into compte(nom,email) values(? ,?)");) {
      ps.setString(1, compte.getNom());
      ps.setString(2, compte.getEmail());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }

  public void update(Compte compte) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("update compte set nom = ?, email = ? where id = ?");) {
      ps.setString(1, compte.getNom());
      ps.setString(2, compte.getEmail());
      ps.setInt(3, compte.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }

  public void delete(int id) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("delete from compte where id = ?");) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }
}
