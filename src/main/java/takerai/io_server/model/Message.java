package takerai.io_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class Message extends BaseModel{
    
    //ここが原因か
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String content;
    private String room;

    private String username;
}
