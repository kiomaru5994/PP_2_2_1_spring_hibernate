package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().persist(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car", Car.class);
        return query.getResultList();
    }

    @Override
    public User getUserByModelAndSeries(String model, int series) {
        User user = sessionFactory.getCurrentSession()
                .createQuery("from User where userCar.model = :model AND userCar.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
        return user;
    }
}
