package hiber.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_model")
    private String model;

    @Column(name = "car_series")
    private int series;

    @OneToOne(mappedBy = "userCar",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private User carUser;

    public Car() {

    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public User getCarUser() {
        return carUser;
    }

    public void setCarUser(User carUser) {
        this.carUser = carUser;
    }

    @Override
    public String toString() {
        return  "Серия: " + series +
                ", Модель: '" + model + '\'';
    }
}
