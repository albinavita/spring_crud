package org.example.controller;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;

    //внедряем объект PersonDAO в контроллер через конструктор
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //возвращает список из людей
    @GetMapping()
    public String index(Model model) {
       //Получим всех людей из DAO и передадим
        //на отображение в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
      //Получим одного человека по id из DAO и
      //передадим на отображение в представление
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
    /**
     * возвращает HTML форму для создания нов чел
     */
//    @GetMapping("/new")
//    public String newPerson(Model model){
//        model.addAttribute("person", new Person());
//       return "people/new";
//    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
       return "people/new";
    }


    /**
     * Принимает на вход POST запрос
     * Будет брать данные с этого POST запроса
     * Добавляет нового чел в БД с помощью DAO
     */
    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        //redirect: - это механизм перехода (на другую страницу) на далее указанную страницу
        return "redirect:/people";
    }

}
