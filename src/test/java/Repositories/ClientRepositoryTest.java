package Repositories;

import classes.persoane.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    @Test
    public void testInsert(){

        ClientRepository clientRepository = new ClientRepository();
        clientRepository.insert(new Client("Feta Almar", "almar", 20, "Client", "Orhidea 1", "0722134782"));
    }

    @Test
    public void testDelete(){

        ClientRepository clientRepository = new ClientRepository();
        clientRepository.delete(4);
    }

    @Test
    public void testAll(){

        ClientRepository clientRepository = new ClientRepository();
        System.out.println(clientRepository.all());
    }

    @Test
    public void testUpdate(){

        ClientRepository clientRepository = new ClientRepository();
        Client client = new Client("Laurentiu", "1111", 28, "Client", "Valea Macinului 10", "0773934890");
        client.setId(4);
        clientRepository.update(client);
    }

    @Test
    public void testExistsID(){

        ClientRepository clientRepository = new ClientRepository();
        assertEquals(false, clientRepository.existsID(4));
    }

    @Test
    public void testGetClientByID(){

        ClientRepository clientRepository = new ClientRepository();
        System.out.println(clientRepository.getClientByID(4));
    }
}