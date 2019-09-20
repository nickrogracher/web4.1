package servlet;

import model.Car;
import org.hibernate.HibernateException;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(req);
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
        long price = Long.parseLong(req.getParameter("price"));
        try {
            Car car = new Car(brand, model, licensePlate, price);
            CarService carService = CarService.getInstance();
            carService.addCar(car);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        catch (HibernateException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request){
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("brand", "");
        pageVariables.put("model", "");
        pageVariables.put("licensePlate", "");
        pageVariables.put("price", 0);
        return pageVariables;
    }
}
