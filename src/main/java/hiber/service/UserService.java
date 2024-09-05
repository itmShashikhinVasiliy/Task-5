package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void addCar(Car car);
    User getUserByCar(String model, Integer series);
    List<User> listUsers();
}
