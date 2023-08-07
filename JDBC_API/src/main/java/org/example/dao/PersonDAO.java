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
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgresql";
    private static final String PASSPORT = "admin";
    private static Connection connection;
    static {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USERNAME, PASSPORT);
    }

//возвращает список из объектов класса Рerson
    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  people;
    }

//возвращает одного чел по id
    public Person show(int id) {
//        for(Person person : people) {
//            if (person.getId() == id) {
//                return person;
//            }
//
//        }
        return null;
     // return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
       Statement statement = connection.createStatement();
       String SQL = "INCERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
               "', " + person.getAge() + ", '" + person.getEmail() + "')";
       statement.executeUpdate(SQL);
    }

    public void update(int id, Person person) {
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(person.getName());
//        personToBeUpdated.setAge(person.getAge());
//        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
