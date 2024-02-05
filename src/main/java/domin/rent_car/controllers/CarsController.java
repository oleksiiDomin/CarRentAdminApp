package domin.rent_car.controllers;

import domin.rent_car.models.car.Car;
import domin.rent_car.models.person.Person;
import domin.rent_car.services.CarsService;
import domin.rent_car.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarsController {
    private final CarsService carsService;
    private final PeopleService peopleService;

    @Autowired
    public CarsController(CarsService carsService, PeopleService peopleService) {
        this.carsService = carsService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cars", carsService.findAll());
        return "cars/index";
    }

    @GetMapping("/index/{page}")
    public String index(@PathVariable("page") int page, Model model) {
        model.addAttribute("pageCars", carsService.findAll(page));
        model.addAttribute("pageNumber", page);
        return "cars/index";
    }

    @GetMapping("/find")
    public String findCar(@RequestParam("query") String plate, Model model) {
        model.addAttribute("findingCars", carsService.findAllByLicensePlate(plate));
        return "cars/search";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model) {
        model.addAttribute("car", carsService.findOne(id));

        Person tenant = carsService.getCarTenant(id);

        if (tenant != null) {
            model.addAttribute("tenant", tenant);
        } else {
            model.addAttribute("people", peopleService.findAll());
        }

        return "cars/show";
    }

    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "cars/new";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("car") Car car) {
        carsService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carsService.findOne(id));
        return "cars/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") Car car, @PathVariable("id") int id) {
        carsService.update(id, car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        carsService.delete(id);
        return "redirect:/cars";
    }

    @PatchMapping("/assign/{id}")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        //bookDAO.assignBook(id, selectedPerson);
        carsService.assign(id, selectedPerson);
        return "redirect:/cars";
    }

    @PatchMapping("/release/{id}")
    public String release(@PathVariable("id") int id) {
        carsService.release(id);
        return "redirect:/cars";
    }
}
