package domin.rent_car.repositories;

import domin.rent_car.models.car.Car;
import domin.rent_car.models.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByLicensePlateIgnoreCase(String licensePlate);
}
