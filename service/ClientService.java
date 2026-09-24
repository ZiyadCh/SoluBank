package service;

import java.util.ArrayList;

import models.Client;
import dao.ClientDao;

public class ClientService {
  private static ClientDao clientDao = new ClientDao();

  public static ArrayList<Client> listClients() {
    ArrayList<Client> clientList = clientDao.getList();
    return clientList;

  }
}
