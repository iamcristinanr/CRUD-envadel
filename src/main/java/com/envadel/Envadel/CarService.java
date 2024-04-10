package com.envadel.Envadel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;


/**
 * Service class for managing Car entities.
 */
@Service
public class CarService implements CrudListener<Car> {

    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository){
        this.repository = repository;
    }


    /**
     * Finds all cars based on the provided filter text.
     * If the filter text is null or empty, returns all cars.
     * Otherwise, returns cars matching the filter text.
     * @param filterText The text to filter cars by
     * @return A collection of cars matching the filter text, or all cars if the filter text is empty
     */

    public Collection<Car> findAll(String filterText){
        if (filterText == null || filterText.isEmpty()){
            return repository.findAll();
        } else {
            return repository.search(filterText);
        }
    }

    @Override
    public Collection<Car> findAll() {
        return List.of();
    }


    /**
     * Adds a new car to the repository.
     * @param car The car to add
     * @return The added car
     */

    public Car add(Car car){
        return repository.insert(car);
    }


    /**
     * Updates an existing car in the repository.
     * @param car The car to update
     * @return The updated car
     */

    public Car update(Car car){
        return repository.save(car);
    }


    /**
     * Deletes a car from the repository.
     * @param car The car to delete
     */

    public void delete(Car car){
        repository.delete(car);
    }

}