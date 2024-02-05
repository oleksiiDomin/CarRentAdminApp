package domin.rent_car.models.car;

import domin.rent_car.models.car.enums.Color;
import domin.rent_car.models.car.enums.Type;
import domin.rent_car.models.person.Person;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "licence_plate")
    @Pattern(regexp = "[A-Z]{2}\\d{4}[A-Z]{2}", message = "Enter correct licence plate")
    private String licensePlate;

    @Column(name = "price")
    private double price;

    @Column(name = "mark")
    @NotEmpty(message = "Enter mark")
    private String mark;

    @Column(name = "model")
    @NotEmpty(message = "Enter model")
    private String model;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "year_of_manufacture")
    @Min(value = 1990, message = "Year of manufacture should be upper 1990")
    private int yearOfManufacture;

    @Column(name = "car_mileage")
    private int carMileage;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person tenant;

    public Car() {
    }

    public Car(int id, String licensePlate, double price, String mark, String model, Type type, Color color, int yearOfManufacture, int carMileage) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.price = price;
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
        this.carMileage = carMileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    public Person getTenant() {
        return tenant;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", price=" + price +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", type=" + type +
                ", color=" + color +
                ", yearOfManufacture=" + yearOfManufacture +
                ", carMileage=" + carMileage +
                '}';
    }
}
