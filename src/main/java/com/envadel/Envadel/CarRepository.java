package com.envadel.Envadel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


/**
 * Interface define a repository for entity Car, providing methods to access and manipulate car data in the database.
 */

public interface CarRepository extends MongoRepository<Car, Integer> {

        /**
         Searches for cars in the database that match the provided filter text.
         * @param filterText El texto de filtro utilizado para buscar coches.
         */

        @Query("{'$or':[ {'brand': {$regex : ?0, $options: 'i'}}, {'model': {$regex : ?0, $options: 'i'}}, {'manufactureYear': {$regex : ?0, $options: 'i'}}, {'color': {$regex : ?0, $options: 'i'}}]}")
        List<Car> search(String filterText);

}
