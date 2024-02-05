package domin.rent_car.repositories;

import domin.rent_car.models.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllByLastNameIsStartingWithIgnoreCase(String name);
}
