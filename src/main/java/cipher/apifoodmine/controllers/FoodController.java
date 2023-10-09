package cipher.apifoodmine.controllers;

import cipher.apifoodmine.models.dao.FoodDAO;
import cipher.apifoodmine.models.dto.Food;
import cipher.apifoodmine.services.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody FoodDAO food){
        this.foodService.create(food);
    }

    @GetMapping()
    public List<FoodDAO> getFoods(){return this.foodService.getFoods();}

    @GetMapping(path = "{id}")
    public Food getFoodById(@PathVariable String id){return this.foodService.getFoodById(id);}
}
