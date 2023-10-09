package cipher.apifoodmine.repositories;

import cipher.apifoodmine.models.dao.FoodDAO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface FoodRepository extends MongoRepository<FoodDAO,Integer> {
    Optional<FoodDAO> findById(String id);
}
