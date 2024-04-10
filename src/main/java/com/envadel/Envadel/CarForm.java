package com.envadel.Envadel;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;



/**
 * Form component for editing or adding a car.
 */

public class CarForm extends FormLayout {

    Binder<Car> binder = new BeanValidationBinder<>(Car.class);

    TextField brand = new TextField("Brand");
    TextField model = new TextField("Model");

    TextField manufacture_year = new TextField("Manufacture_Year");

    TextField color = new TextField("Color");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Car car;

    public CarForm() {
        addClassName("car-form");
        binder.bindInstanceFields(this);

        add(
                brand,
                model,
                manufacture_year,
                color,
                createButtonLayout()
        );
    }


    /**
     * Sets the car to be edited or added.
     * @param car The car to be set in the form.
     */

    public void setCar(Car car){
        this.car = car;
        binder.readBean(car);
    }



    /**
     * Creates and configures the layout for save, delete, and cancel buttons.
     * @return The horizontal layout containing the buttons.
     */

    private Component createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this,car)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));


        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save,delete, cancel);
    }

    /**
     * Validates the form fields and saves the car if validation is successful.
     */

    private void validateAndSave(){
        try{
            binder.writeBean(car);
            fireEvent(new SaveEvent(this, car));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }


    /**
     * This section defines event classes and methods for handling events in the CarForm component.
     */

    public static abstract class CarFormEvent extends ComponentEvent<CarForm> {

        /**
         * The Car associated with the event.
         */
        private final Car car;

        protected CarFormEvent(CarForm source, Car car) {
            super(source, false);
            this.car = car;
        }

        public Car getCar() {
            return car;
        }
    }


    public static class SaveEvent extends CarFormEvent {
        SaveEvent(CarForm source, Car car) {
            super(source, car);
        }
    }


    public static class DeleteEvent extends CarFormEvent {
        DeleteEvent(CarForm source, Car car) {
            super(source, car);
        }

    }


    public static class CloseEvent extends CarFormEvent {
        CloseEvent(CarForm source) {
            super(source, null);
        }
    }

    /**
     * Registers an event listener for the specified event type.
     * @param eventType The type of event to listen for
     * @param listener The event listener to register
     * @param <T> The type of event
     * @return A registration for the event listener
     */

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }


    public void addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        addListener(DeleteEvent.class, listener);
    }


    public void addSaveListener(ComponentEventListener<SaveEvent> listener) {
        addListener(SaveEvent.class, listener);
    }


    public void addCloseListener(ComponentEventListener<CloseEvent> listener) {
        addListener(CloseEvent.class, listener);
    }


    /**
     * Gets the TextField
     * @return The TextField
     */

    public TextField getBrand() {
        return brand;
    }

    public void setBrand(TextField brand) {
        this.brand = brand;
    }

    public TextField getModel() {
        return model;
    }

    public void setModel(TextField model) {
        this.model = model;
    }

    public TextField getManufacture_year() {
        return manufacture_year;
    }
    public void setManufacture_year(TextField manufacture_year) {
        this.manufacture_year = manufacture_year;
    }

    public TextField getColor() {
        return color;
    }

    public void setColor(TextField color) {
        this.color = color;
    }
}
