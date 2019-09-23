package service;

import DAO.CarDao;
import model.Car;
import model.DailyReportHandler;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public boolean addCar(Car car) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CarDao carDao = new CarDao(session);
            if (carDao.checkMountOfBrand(car.getBrand()) > 10) {
                throw new HibernateException("Already 10");
            }
            else {
                carDao.addCar(car);
            }
            session.getTransaction().commit();
            session.clear();
            return true;
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    public List<Car> getAllCars(){
        List<Car> cars = new ArrayList<>();
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CarDao carDao = new CarDao(session);
            cars =  carDao.getAllCars();
            transaction.commit();
            session.close();
            return cars;
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    public boolean sellCar(String brand, String model, String licensePlate){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CarDao carDao = new CarDao(session);
            carDao.sellCar(brand, model, licensePlate);
            transaction.commit();
            session.clear();
            return true;
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    public void deleteAllCars() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CarDao carDao = new CarDao(session);
            carDao.deleteReports();
            transaction.commit();
            session.clear();
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }
}
