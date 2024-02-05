package domin.rent_car.models.person;

import domin.rent_car.models.car.Car;
import domin.rent_car.models.person.enums.Sex;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "passport")
    @Pattern(regexp = "[A-Z]{2}\\d{6}", message = "Enter correct passport id")
    private String passport;

    @Column(name = "driving_license")
    @Pattern(regexp = "[A-Z]{3}\\d{6}", message = "Enter correct driver license id")
    private String drivingLicense;

    @Column(name = "sex")
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "tenant")
    private List<Car> cars;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, String passport, String drivingLicense, Sex sex, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.drivingLicense = drivingLicense;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String driverLicense) {
        this.drivingLicense = driverLicense;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Car> getCars() {
//        if (cars == null) {
//            cars = new ArrayList<>();
//        }
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                ", driverLicense='" + drivingLicense + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
