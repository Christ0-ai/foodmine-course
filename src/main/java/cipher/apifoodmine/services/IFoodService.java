package cipher.apifoodmine.services;

import cipher.apifoodmine.models.dao.FoodDAO;
import cipher.apifoodmine.models.dto.Food;

import java.util.List;

public interface IFoodService {
    void create(FoodDAO food);

    List<FoodDAO> getFoods();

    Food getFoodById(String id);
}
