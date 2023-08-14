package org.example.util;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * помечаем класс @Component, т.к. хотим, чтобы spring внедрил dao за нас
 */
@Component
public class PersonValidator implements Validator {
    //чтобы посмотреть есть ли такой email надо к этой БД обратиться
    //обращаться к БД будем через DAO
    //внедряем PersonDAO
    private final PersonDAO personDAO;

    //внедряем через конструктор
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //посмотреть, есть ли человек с таким же email'ом
        //после внедрения мы имеем доступ к PersonDAO
        // и можем обратиться к его методам и получить человека по его email'у
        if (personDAO.show(person.getEmail()).isPresent()) {
            errors.rejectValue("email","", "This email is already taken");
        }
    }
}
