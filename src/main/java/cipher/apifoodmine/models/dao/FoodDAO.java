package cipher.apifoodmine.models.dao;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document("Foods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FoodDAO {
    @Id
    private String id;
    private String name;
    private Integer number;
    private List<TagDAO> tags;
    private Boolean favorite;
    private Integer stars;
    private String imageUrl;
    private List<String> origins;
    private String cookTime;
}
