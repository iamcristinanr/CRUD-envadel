package com.envadel.Envadel;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;


/**
 * View for displaying and managing a list of cars.
 */

@PageTitle("Car List")
@Route(value = "")
public class CarView extends VerticalLayout {
    private CarService service;
    Grid<Car> grid = new Grid<>(Car.class);
    TextField filterText = new TextField();
    CarForm form;


    /**
     * Constructs a CarView with the specified CarService.
     * @param service The CarService to be used
     */

    public CarView(CarService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();
        closeEditor();
    }


    /**
     * Closes the editor form.
     */

    private void closeEditor() {
        form.setCar(null);
        form.setVisible(false);
        removeClassName("editing");
    }


    /**
     * Updates the grid with the list of cars based on the filter text.
     */

    private void updateList() {
        grid.setItems(service.findAll(filterText.getValue()));

    }


    /**
     * Retrieves the content component, which includes the grid and form.
     * @return The content component
     */

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }


    /**
     * Configures the car form.
     */

    private void configureForm() {
        form = new CarForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveCar);
        form.addDeleteListener(this::deleteCar);
        form.addCloseListener(e -> closeEditor());

    }


    /**
     * Saves the car data.
     * @param event The save event
     */

    private void saveCar(CarForm.SaveEvent event) {
        service.update(event.getCar());
        updateList();
        closeEditor();
    }


    /**
     * Deletes the selected car.
     * @param event The delete event
     */

    private void deleteCar(CarForm.DeleteEvent event) {
        service.delete(event.getCar());
        updateList();
        closeEditor();
    }


    /**
     * Retrieves the toolbar component.
     * @return The toolbar component
     */

    private Component getToolbar() {
        filterText.setPlaceholder("Search car");
        filterText.setClearButtonVisible(true);
        filterText.addValueChangeListener(e -> updateList());

        Button addCarButton = new Button("ADD CAR");
        addCarButton.addClickListener(e -> addCar());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCarButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


    /**
     * Opens the editor form to add a new car.
     */

    private void addCar() {
        grid.asSingleSelect().clear();
        editCar(new Car());

    }


    /**
     * Configures the grid to display car data.
     */

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setColumns("brand", "model", "manufacture_year", "color");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editCar(e.getValue()));
    }


    /**
     * Opens the editor form to edit the selected car.
     * @param car The selected car
     */

    private void editCar(Car car) {
        if (car == null) {
            closeEditor();
        } else {
            form.setCar(car);
            form.setVisible(true);
            addClassName("editing");

        }

    }
}