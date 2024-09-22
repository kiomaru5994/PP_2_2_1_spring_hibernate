package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      // Подключение контекста Spring и конфигураций
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      // Получение бинов сервисов
      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      // Создание объектов машин
      Car car1 = new Car("Toyota", 200);
      Car car2 = new Car("BMW", 500);
      Car car3 = new Car("ВАЗ", 14);

      // Создание объектов пользователей
      User user1 = new User("John", "Doe", "john@doe.com");
      User user2 = new User("Jane", "Doe", "jane@doe.com");
      User user3 = new User("Ivan", "Ivanov", "ivan@ivanov.com");

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);

      userService.addCarToUser(user1, car1);
      userService.addCarToUser(user2, car2);
      userService.addCarToUser(user3, car3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getUserCar());
         System.out.println();
      }

      User userOutput = carService.getUserByModelAndSeries("ВАЗ", 14);
      System.out.println("Найден пользователь по машине ВАЗ 14:");
      System.out.println("Id = " + userOutput.getId());
      System.out.println("First Name = " + userOutput.getFirstName());
      System.out.println("Last Name = " + userOutput.getLastName());
      System.out.println("Email = " + userOutput.getEmail());
      System.out.println("Car = " + userOutput.getUserCar());

      context.close();
   }
}
