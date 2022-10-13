package com.ciclo3.projectc3.Service;


import com.ciclo3.projectc3.Entities.Client;
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
        if (client.getId() == null){
            return clientRepository.save(client);
        } else {
            Optional<Client> client1 = clientRepository.getClient(client.getId());
            if(client1.isPresent()){
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }
}
