package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  private static String url = "jdbc:postgresql://localhost/solbank?user=kaiser&password=kaiser";

  public static Connection gConnection() throws SQLException {
    return DriverManager.getConnection(url);
  }

}
