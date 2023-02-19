package takerai.io_server.socket;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import takerai.io_server.model.Message;
import takerai.io_server.model.MessageType;
import takerai.io_server.service.MessageService;

@RequiredArgsConstructor
@Service
@Slf4j
public class SocketService {
    
    private final MessageService messageService;

    public void sendSocketMessage(SocketIOClient senderClient, Message message, String room) {
        //clientってあれか接続してるやつか
        for (
            SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {

                // //senderClientはおそらく送信者だ
                // log.info(client.getSessionId().toString());
                // log.info(senderClient.getSessionId().toString());
                //ここでfalseになってるらしい
                if (!client.getSessionId().equals(senderClient.getSessionId())) {
                    client.sendEvent("read_message", message);
                } 
                
                
            }
    }

    public void saveMessage(SocketIOClient senderClient, Message message) {
        Message storedMessage = messageService.saveMessage(Message.builder()
                .messageType(MessageType.CLIENT)
                .content(message.getContent())
                .room(message.getContent())
                .username(message.getUsername())
                .build()
                );
        log.info("sendmessage");
        sendSocketMessage(senderClient, storedMessage, message.getRoom());
    }

    public void saveInfoMessage(SocketIOClient senderClient, String message, String room) {
        Message storedMessage = messageService.saveMessage(Message.builder()
                .messageType(MessageType.SERVER)
                .content(message)
                .room(room)
                .build());
        sendSocketMessage(senderClient, storedMessage, room);
    }
}
