package com.ciclo3.projectc3.Service;


import com.ciclo3.projectc3.Entities.Category;
import com.ciclo3.projectc3.Entities.Client;
import com.ciclo3.projectc3.Entities.Machine;
import com.ciclo3.projectc3.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save (Client client){
        if (client.getIdClient() == null){
            return clientRepository.save(client);
        } else {
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if(client1.isPresent()){
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if(client1.isPresent()) {
                if (client.getName() != null) {
                    client1.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    client1.get().setAge(client.getAge());
                }
                if (client.getPassword() != null) {
                    client1.get().setPassword(client.getPassword());
                }

                clientRepository.save(client1.get());
                return client1.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    public boolean deleteClient(int id){
        Boolean client1 = getClient(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return client1;
    }
}
