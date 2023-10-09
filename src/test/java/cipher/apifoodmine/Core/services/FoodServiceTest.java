package cipher.apifoodmine.Core.services;

import cipher.apifoodmine.models.dao.FoodDAO;
import cipher.apifoodmine.models.dao.TagDAO;
import cipher.apifoodmine.models.dto.Food;
import cipher.apifoodmine.repositories.FoodRepository;
import cipher.apifoodmine.services.FoodService;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
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

    /* ON VERIFIE SI LE SERVICE APPEL LE REPOSITORY */
    @Test
    void should_call_repository_when_findAll(){

        // WHEN
        foodService.getFoods();

        // THEN
        verify(foodRepository).findAll();
    }

    /* ON VERIFIE SI L'ID PASSE EN PARAMETRE EST EGAL A CELUI QU'ON RECUPERE VIA LE REPO */
    @Test
    void should_find_food_by_id(){

        Food foodFromListDAO = new Food(foods.get(1));
        String foodId = "6523cb6a11ab667c9baedacd";

        // GIVEN
        when(foodRepository.findById(foodId)).thenReturn(Optional.ofNullable(foods.get(1)));

        // WHEN
        Food food = foodService.getFoodById(foodId);

        // THEN
        assertThat(food.getId()).isEqualTo(foodFromListDAO.getId());
    }

    /* ON VERIFIE SI ON RECUPERE UNE LISTE LORSQU'ON APPELLE LE REPOSITORY */
    @Test
    void should_find_all_food_when_call_repository(){

        // GIVEN
        when(foodRepository.findAll()).thenReturn(foods);

        // WHEN
        List<FoodDAO> foods = foodService.getFoods();

        // THEN
        assertThat(foods.isEmpty()).isFalse();
    }

    /* ON VERIFIE QU'UNE EXCEPTION SE MANIFESTE SI FOOD N'EXISTE PAS */

    @Test
    void should_throw_EntityNotFoundException_if_food_is_null(){

        String foodId = "6523cb6a11ab667c9baedacd";

        // GIVEN
        when(foodRepository.findById(foodId)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(EntityNotFoundException.class, () -> {foodService.getFoodById(foodId);});
    }

}
