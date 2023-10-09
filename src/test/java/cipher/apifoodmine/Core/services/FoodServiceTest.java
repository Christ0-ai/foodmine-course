package cipher.apifoodmine.Core.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import cipher.apifoodmine.models.dao.FoodDAO;
import cipher.apifoodmine.models.dao.TagDAO;
import cipher.apifoodmine.models.dto.Food;
import cipher.apifoodmine.repositories.FoodRepository;
import cipher.apifoodmine.services.FoodService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FoodServiceTest {

    private FoodService foodService;

    private List<TagDAO> tags = new ArrayList<>();
    private List<String> origins = new ArrayList<>();
    private List<FoodDAO> foods = new ArrayList<>();

    // MOCK me permet de simuler mon repo car je veux tester seulement mon service
    @Mock
    private FoodRepository foodRepository;

    // Methode appelee avant chaque test
    @BeforeEach
    void setUp(){

        foodService = new FoodService(foodRepository);

        String italy = "Italy";
        String france = "France";

        TagDAO fastFood = new TagDAO(1,"Fastfood",1);
        TagDAO lunch = new TagDAO(2,"Launch",1);

        tags.add(fastFood);
        tags.add(lunch);

        origins.add(italy);
        origins.add(france);

        FoodDAO pizza = new FoodDAO("6523c123384fda2e17d0db57","pizza",1,tags,true,4,"url/image",origins,"5");
        FoodDAO burger = new FoodDAO("6523cb6a11ab667c9baedacd","burger",1,tags,true,4,"url/image",origins,"5");

        foods.add(pizza);
        foods.add(burger);
    }

    @Test
    void should_find_all_food(){

        // GIVEN
        when(foodService.getFoods()).thenReturn(foods);

        // WHEN
        List<FoodDAO> foods = foodService.getFoods();

        // THEN
        assertThat(foods).isNotNull();
    }

    @Test
    void should_find_food_by_id(){

        Food foodFromListDAO = new Food(foods.get(1));
        String foodId = "6523cb6a11ab667c9baedacd";
        // GIVEN
        when(foodService.getFoodById(foodId)).thenReturn(foodFromListDAO);

        // WHEN
         Food food = foodService.getFoodById(foodId);

        // THEN
        assertThat(food).isNotNull();
    }

    @Test
    void should_find_all_food_when_call_repository(){

        // GIVEN
        when(foodRepository.findAll()).thenReturn(foods);

        // WHEN
        List<FoodDAO> foods = foodService.getFoods();

        // THEN
        assertThat(foods).isNotNull();
    }

    @Test
    void should_find_food_by_id_when_call_repository(){

        //GIVEN
        when(foodRepository.findById("6523cb6a11ab667c9baedacd")).thenReturn(Optional.ofNullable(foods.get(1)));

        //WHEN
        Food food = foodService.getFoodById("6523cb6a11ab667c9baedacd");

        //THEN
        assertThat(food.getName()).isEqualTo("burger");
    }
}
