package cipher.apifoodmine.models.dao;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Tags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDAO {
    @Id
    private int id;
    private String name;
    private int count;
}
