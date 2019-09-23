package DAO;

import model.Car;
import model.DailyReportHandler;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import service.DailyReportService;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.like;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public void addCar(Car car){
        session.save(new Car(car.getBrand(), car.getModel(), car.getLicensePlate(), car.getPrice()));
    }

    public int checkMountOfBrand(String brand){
        List list = session.createCriteria(Car.class).add(like("brand", brand)).list();
        return list.size();
    }

    public List<Car> getAllCars(){
        List list = session.createCriteria(Car.class).list();
        return list;
    }

    public void sellCar(String brand, String model, String licensePlate){
        List list = session.createCriteria(Car.class).add(like("brand", brand))
                .add(like("model", model))
                .add(like("licensePlate", licensePlate)).list();
        Car car = (Car)list.get(0);
        DailyReportHandler.getInstance().addToReportHandler(car.getPrice());
        session.delete(list.get(0));
    }

    public void deleteReports() {
        Query query =  session.createQuery("delete from Car");
        query.executeUpdate();
    }
}
