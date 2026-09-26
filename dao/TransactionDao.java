package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import models.Transaction;
import models.TypeTransaction;
import utils.Database;

public class TransactionDao {

  public ArrayList<Transaction> getList() {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement(
            "select id, date, montant, type, lieu, idcompte from transaction");
        ResultSet rs = ps.executeQuery()) {
      ArrayList<Transaction> list = new ArrayList<>();
      while (rs.next()) {
        int id = rs.getInt("id");
        LocalDate date = rs.getDate("date").toLocalDate();
        double montant = rs.getDouble("montant");
        TypeTransaction type = TypeTransaction.valueOf(rs.getString("type"));
        String lieu = rs.getString("lieu");
        int idCompte = rs.getInt("idcompte");
        list.add(new Transaction(id, date, montant, type, lieu, idCompte));
      }
      return list;
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
      return null;
    }
  }

  public void add(Transaction transaction) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement(
            "insert into transaction(date,montant,type,lieu,idcompte) values(?,?,?,?,?)");) {
      ps.setDate(1, Date.valueOf(transaction.getDate()));
      ps.setDouble(2, transaction.getMontant());
      ps.setString(3, transaction.getType().name());
      ps.setString(4, transaction.getLieu());
      ps.setInt(5, transaction.getIdCompte());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }

  public void update(Transaction transaction) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement(
            "update transaction set montant=?, type=?, lieu=?, idcompte=? where id=?");) {
      ps.setDouble(1, transaction.getMontant());
      ps.setString(2, transaction.getType().name());
      ps.setString(3, transaction.getLieu());
      ps.setInt(4, transaction.getIdCompte());
      ps.setInt(5, transaction.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }

  public void delete(int id) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("delete from transaction where id = ?");) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }

  public Optional<Transaction> getById(int id) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement(
            "select id, date, montant, type, lieu, idcompte from transaction where id = ?");) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return Optional.of(new Transaction(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getDouble("montant"),
              TypeTransaction.valueOf(rs.getString("type")), rs.getString("lieu"), rs.getInt("idcompte")));
        }
        return Optional.empty();
      }
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
      return Optional.empty();
    }
  }
}
