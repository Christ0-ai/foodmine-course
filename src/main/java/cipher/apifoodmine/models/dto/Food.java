package cipher.apifoodmine.models.dto;

import cipher.apifoodmine.models.dao.FoodDAO;
import cipher.apifoodmine.models.dao.TagDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class Food {

    private String id;
    private String name;
    private Integer number;
    private List<TagDAO> tags;
    private Boolean favorite;
    private Integer stars;
    private String imageUrl;
    private List<String> origins;
    private String cookTime;

    public Food(FoodDAO foodDAO) {
        this.setId(foodDAO.getId());
        this.setName(foodDAO.getName());
        this.setNumber(foodDAO.getNumber());
        this.setTags(foodDAO.getTags());
        this.setFavorite(foodDAO.getFavorite());
        this.setStars(foodDAO.getStars());
        this.setImageUrl(foodDAO.getImageUrl());
        this.setOrigins(foodDAO.getOrigins());
        this.setCookTime(foodDAO.getCookTime());
    }

}


