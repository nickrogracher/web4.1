package DAO;

import model.Car;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import service.DailyReportService;

import java.util.ArrayList;
import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public void addCar(Car car){
        session.save(new Car(car.getBrand(), car.getBrand(), car.getLicensePlate(), car.getPrice()));
    }

    public int checkMountOfBrand(String brand){
        List list = session.createCriteria(Car.class).add(Restrictions.like("brand", brand)).list();
        return list.size();
    }

    public List<Car> getAllCars(){
        List list = session.createCriteria(Car.class).list();
        return list;
    }

    public void sellCar(String brand, String model, String licensePlate){
        List list = session.createCriteria(Car.class).add(Restrictions.like("brand", brand))
                .add(Restrictions.like("model", model))
                .add(Restrictions.like("licensePlate", licensePlate)).list();
        Car car = (Car)list.get(0);
        DailyReportService.getInstance().addSellToReport(car.getPrice());
        session.delete(list.get(0));
    }

}
