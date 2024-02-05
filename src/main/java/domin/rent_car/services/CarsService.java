package domin.rent_car.services;

import domin.rent_car.models.car.Car;
import domin.rent_car.models.person.Person;
import domin.rent_car.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarsService {
    private final CarsRepository carsRepository;

    @Autowired
    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<Car> findAll() {
        return carsRepository.findAll();
    }

    public Page<Car> findAll(int page) {
        return carsRepository.findAll(PageRequest.of(page, 2, Sort.by("mark")));
    }

    public List<Car> findAllByLicensePlate(String licensePlate) {
        return carsRepository.findAllByLicensePlateIgnoreCase(licensePlate);
    }

    public Car findOne(int id) {
        Optional<Car> foundOptional = carsRepository.findById(id);
        return foundOptional.orElse(null);
    }

    @Transactional
    public void save(Car car) {
        carsRepository.save(car);
    }

    @Transactional
    public void update(int id, Car updatedCar) {
        updatedCar.setId(id);
        carsRepository.save(updatedCar);
    }

    @Transactional
    public void delete(int id) {
        carsRepository.deleteById(id);
    }

    public Person getCarTenant(int id) {
        return carsRepository.findById(id).map(Car::getTenant).orElse(null);
    }

    @Transactional
    public void assign(int id, Person personForAssign) {
        carsRepository.findById(id).ifPresent(car -> {
            car.setTenant(personForAssign);
        });
    }

    @Transactional
    public void release(int id) {
        carsRepository.findById(id).ifPresent(car -> {
            car.setTenant(null);
        });
    }
}
