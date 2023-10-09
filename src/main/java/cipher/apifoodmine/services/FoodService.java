package cipher.apifoodmine.services;


import cipher.apifoodmine.models.dao.FoodDAO;
import cipher.apifoodmine.models.dto.Food;
import cipher.apifoodmine.repositories.FoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService{

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void create(FoodDAO foodDAO) {
        this.foodRepository.save(foodDAO);
    }

    @Override
    public List<FoodDAO> getFoods() {return this.foodRepository.findAll();}

    @Override
    public Food getFoodById(String id) {
        FoodDAO foodDAO = this.foodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aucun food n'existe avec cet id"));
        return new Food(foodDAO);
    }
}
