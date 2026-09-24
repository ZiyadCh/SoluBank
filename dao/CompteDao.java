package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Compte;
import models.CompteCourant;
import models.CompteEpargne;
import utils.Database;

public class CompteDao {

  public ArrayList<Compte> getList() {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement(
            "select id, numero, solde, idclient, typecompte, decouvertautorise, tauxinteret from compte");
        ResultSet rs = ps.executeQuery()) {
      ArrayList<Compte> list = new ArrayList<>();
      while (rs.next()) {
        int id = rs.getInt("id");
        String numero = rs.getString("numero");
        double solde = rs.getDouble("solde");
        int idClient = rs.getInt("idclient");
        String type = rs.getString("typecompte");
        if ("courant".equals(type)) {
          list.add(new CompteCourant(id, numero, solde, idClient, rs.getDouble("decouvertautorise")));
        } else {
          list.add(new CompteEpargne(id, numero, solde, idClient, rs.getDouble("tauxinteret")));
        }
      }
      return list;
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
      return null;
    }
  }

  public void add(Compte compte) {
    try (Connection con = Database.gConnection()) {
      if (compte instanceof CompteCourant cc) {
        try (PreparedStatement ps = con.prepareStatement(
            "insert into compte(numero,solde,idclient,typecompte,decouvertautorise) values(?,?,?,?,?)")) {
          ps.setString(1, compte.getNumero());
          ps.setDouble(2, compte.getSolde());
          ps.setInt(3, compte.getIdClient());
          ps.setString(4, "courant");
          ps.setDouble(5, cc.getDecouvertAutorise());
          ps.executeUpdate();
        }
      } else if (compte instanceof CompteEpargne ce) {
        try (PreparedStatement ps = con.prepareStatement(
            "insert into compte(numero,solde,idclient,typecompte,tauxinteret) values(?,?,?,?,?)")) {
          ps.setString(1, compte.getNumero());
          ps.setDouble(2, compte.getSolde());
          ps.setInt(3, compte.getIdClient());
          ps.setString(4, "epargne");
          ps.setDouble(5, ce.getTauxInteret());
          ps.executeUpdate();
        }
      }
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }

  public void update(Compte compte) {
    try (Connection con = Database.gConnection()) {
      if (compte instanceof CompteCourant cc) {
        try (PreparedStatement ps = con.prepareStatement(
            "update compte set numero=?, solde=?, idclient=?, decouvertautorise=? where id=?")) {
          ps.setString(1, compte.getNumero());
          ps.setDouble(2, compte.getSolde());
          ps.setInt(3, compte.getIdClient());
          ps.setDouble(4, cc.getDecouvertAutorise());
          ps.setInt(5, compte.getId());
          ps.executeUpdate();
        }
      } else if (compte instanceof CompteEpargne ce) {
        try (PreparedStatement ps = con.prepareStatement(
            "update compte set numero=?, solde=?, idclient=?, tauxinteret=? where id=?")) {
          ps.setString(1, compte.getNumero());
          ps.setDouble(2, compte.getSolde());
          ps.setInt(3, compte.getIdClient());
          ps.setDouble(4, ce.getTauxInteret());
          ps.setInt(5, compte.getId());
          ps.executeUpdate();
        }
      }
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }

  public Compte getById(int id) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement(
            "select id, numero, solde, idclient, typecompte, decouvertautorise, tauxinteret from compte where id = ?");) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          String type = rs.getString("typecompte");
          if ("courant".equals(type)) {
            return new CompteCourant(rs.getInt("id"), rs.getString("numero"), rs.getDouble("solde"),
                rs.getInt("idclient"), rs.getDouble("decouvertautorise"));
          } else {
            return new CompteEpargne(rs.getInt("id"), rs.getString("numero"), rs.getDouble("solde"),
                rs.getInt("idclient"), rs.getDouble("tauxinteret"));
          }
        }
        return null;
      }
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
      return null;
    }
  }
}
