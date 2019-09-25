package DAO;

import model.Car;
import model.DailyReportHandler;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public void addCar(Car car){
        session.save(new Car(car.getBrand(), car.getModel(), car.getLicensePlate(), car.getPrice()));
    }

    public int checkMountOfBrand(String brand){
        Query query = session.createQuery("FROM Car where brand = :brand");
        query.setParameter("brand", brand);
        List list = query.list();
        return list.size();
    }

    public List<Car> getAllCars(){
        Query query = session.createQuery("FROM Car");
        return (List<Car>) query.list();
    }

    public void sellCar(String brand, String model, String licensePlate){
        Query query = session.createQuery("FROM Car where brand = :brand, model = :model, licensePlate = :licensePlate");
        query.setParameter("brand", brand);
        query.setParameter("model", model);
        query.setParameter("licensePlate", licensePlate);
        List list = query.list();
        Car car = (Car)list.get(0);
        DailyReportHandler.getInstance().addToReportHandler(car.getPrice());
        session.delete(list.get(0));
    }

    public void deleteReports() {
        Query query =  session.createQuery("delete from Car");
        query.executeUpdate();
    }
}
