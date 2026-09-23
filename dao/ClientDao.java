package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Client;
import utils.Database;

public class ClientDao {

  public ArrayList<Client> getList() {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("select * from client");
        ResultSet rs = ps.executeQuery()) {
      ArrayList<Client> list = new ArrayList<>();
      while (rs.next()) {
        int id = rs.getInt("id");
        String nom = rs.getString("nom");
        String email = rs.getString("email");
        Client client = new Client(id, nom, email);
        list.add(client);
      }
      return list;
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
      return null;
    }
  }

  public void add(Client client) {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("insert into client(nom,email) values(? ,?)");) {
      ps.setString(1, client.getNom());
      ps.setString(2, client.getEmail());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }
}
