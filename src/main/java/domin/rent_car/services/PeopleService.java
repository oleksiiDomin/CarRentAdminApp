package domin.rent_car.services;

import domin.rent_car.models.car.Car;
import domin.rent_car.models.person.Person;
import domin.rent_car.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Page<Person> findAll(int page) {
        return peopleRepository.findAll(PageRequest.of(page, 2, Sort.by("lastName")));
    }

    public List<Person> findByLastName(String lastName) {
        return peopleRepository.findAllByLastNameIsStartingWithIgnoreCase(lastName);
    }

    public Person findOne(int id) {
        Optional<Person> foundOptional = peopleRepository.findById(id);
        return foundOptional.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Car> getCarsByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getCars());
            return person.get().getCars();
        } else {
            return Collections.emptyList();
        }
    }
}
