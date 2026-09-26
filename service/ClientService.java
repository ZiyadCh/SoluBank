package service;

import java.util.ArrayList;
import java.util.Optional;

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

  public Client searchClients(String search) {
    return clientDao.getByName(search).orElse(null);
  }

  public void addClient(Client client) {
    clientDao.add(client);
  }

  public void modifyClient(Client client) {
    clientDao.update(client);
  }

  public void deleteClient(int id) {
    clientDao.delete(id);
  }
}
