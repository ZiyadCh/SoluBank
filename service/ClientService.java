package service;

import java.util.ArrayList;

import models.Client;
import dao.ClientDao;

public class ClientService {
  private static ClientDao clientDao = new ClientDao();

  public ArrayList<Client> listClients() {
    ArrayList<Client> clientList = clientDao.getList();
    return clientList;

  }

  public Client getClientById(int id) {
    return clientDao.getById(id);
  }

  public void addClient(Client client) {
    clientDao.add(client);
  }
}
