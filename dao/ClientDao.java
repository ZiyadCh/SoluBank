package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Database;

public class ClientDao {
  public void ListClient() {
    try (Connection con = Database.gConnection();
        PreparedStatement ps = con.prepareStatement("select * from clients");
        ResultSet rs = ps.executeQuery()) {
    } catch (SQLException e) {
      System.out.println("Erreur" + e);
    }
  }
}
