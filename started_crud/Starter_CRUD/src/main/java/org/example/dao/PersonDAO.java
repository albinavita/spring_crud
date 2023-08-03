package org.example.dao;

import java.util.ArrayList;
import java.util.List;
import org.example.models.Person;
import org.springframework.stereotype.Component;

/**
 *Задачи:
 * 1. общение со списком
 * 2. извлекать людей из списка
 * 3. находить конкретного чел по id из списка
 * 4. добавлять чел. в список
 * 5. обновлять
 * 6. удалять
 */
@Component
public class PersonDAO {
    private static int PEOPELE_COUNT;
    private List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPELE_COUNT, "Tom"));
        people.add(new Person(++PEOPELE_COUNT, "Bob"));
        people.add(new Person(++PEOPELE_COUNT, "Mike"));
        people.add(new Person(++PEOPELE_COUNT, "Katy"));
    }
//возвращает список из объектов класса Рerson
    public List<Person> index() {
        return  people;
    }
//возвращает одного чел по id
    public Person show(int id) {
        for(Person person : people) {
            if (person.getId() == id) {
                return person;
            }

        }
        return null;
      // return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPELE_COUNT);
        people.add(person);
    }
}
