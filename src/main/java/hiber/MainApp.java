package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User belek = new User("Belek", "Salchak", "user1@mail.ru");
      User ivan = new User("Ivan", "Ivanov", "user2@mail.ru");
      User boris = new User("Boris", "Popov", "user3@mail.ru");
      User vadim = new User("Vadim", "Nemkov", "user4@mail.ru");

      Car toyota = new Car("Toyota", 123);
      Car honda = new Car("Honda", 456);
      Car bmw = new Car("BMW", 789);
      Car lada = new Car("Lada", 101);

      userService.add(belek.setCar(toyota).setUser(belek));
      userService.add(ivan.setCar(honda).setUser(ivan));
      userService.add(boris.setCar(bmw).setUser(boris));
      userService.add(vadim.setCar(lada).setUser(vadim));

      for (User user : userService.listUsers()){
         System.out.println(user+" "+ user.getCar());
      }

      System.out.println(userService.getUserByCar("BMW", 789));

      try {
         User notUser = userService.getUserByCar("Subaru", 3453);
      }catch (NoResultException e){
         System.out.println("Такой пользователь отсутсвует");
      }


      context.close();
   }
}
