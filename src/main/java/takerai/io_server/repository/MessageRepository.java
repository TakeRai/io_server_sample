package takerai.io_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import takerai.io_server.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findAllByRoom(String room);
}
