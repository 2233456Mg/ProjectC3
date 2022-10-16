package com.ciclo3.projectc3.Service;

import com.ciclo3.projectc3.Entities.Category;
import com.ciclo3.projectc3.Entities.Machine;
import com.ciclo3.projectc3.Entities.Message;
import com.ciclo3.projectc3.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }

    public Message save (Message message){
        if (message.getIdMessage() == null){
            return messageRepository.save(message);
        } else {
            Optional<Message> message1 = messageRepository.getMessage(message.getIdMessage());
            if(message1.isPresent()){
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> message1 = messageRepository.getMessage(message.getIdMessage());
            if(message1.isPresent()) {
                if (message.getMessageText() != null) {
                    message1.get().setIdMessage(message.getIdMessage());
                }
                messageRepository.save(message1.get());
                return message1.get();
            }else {
                return message;
            }
        }else return message;
    }
    public boolean deleteMessage(int id){
        Boolean message1 = getMessage(id).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return message1;
    }
}
