package domin.rent_car.controllers;

import domin.rent_car.models.person.Person;
import domin.rent_car.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/index/{page}")
    public String index(@PathVariable("page") int page, Model model) {
        model.addAttribute("pagePeople", peopleService.findAll(page));
        model.addAttribute("pageNumber", page);
        return "people/index";
    }

    @GetMapping("/find")
    public String findPerson(@RequestParam("query") String lastName, Model model) {
        model.addAttribute("findingPeople", peopleService.findByLastName(lastName));
        return "people/search";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("cars", peopleService.getCarsByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") Person person, @PathVariable("id") int id) {
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
