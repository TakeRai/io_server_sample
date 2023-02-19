package takerai.io_server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import takerai.io_server.model.Message;
import takerai.io_server.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final MessageRepository messageRepository;

    public List<Message> getMessages(String room) {
        return messageRepository.findAllByRoom(room);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
