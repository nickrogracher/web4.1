import model.Car;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.CustomerServlet;
import servlet.DailyReportServlet;
import servlet.NewDayServlet;
import servlet.ProducerServlet;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
//        List<Car> newCarList = new ArrayList<Car>();
//        String[] brands = { "Mitsubisi", "Touota", "Rarsedess", "AZAZAZ", "LIAZ", "GAZ" };
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 5 && j < brands.length; j++) {
//                int b = (i < brands[j].length()) ? i : (i % brands[j].length());
//                String model = brands[j].substring(b) + brands[j].substring(0, b);
//                newCarList.add(new Car(brands[j], model, model + i + j, Long.valueOf((1 + i + (j + 1) * 2))));
//            }
//        }
        CustomerServlet customerServlet = new CustomerServlet();
        DailyReportServlet dailyReportServlet = new DailyReportServlet();
        NewDayServlet newDayServlet = new NewDayServlet();
        ProducerServlet producerServlet = new ProducerServlet();

        ServletContextHandler contextHandler =new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(customerServlet), "/customer");
        contextHandler.addServlet(new ServletHolder(dailyReportServlet), "/*");
        contextHandler.addServlet(new ServletHolder(newDayServlet), "/newday");
        contextHandler.addServlet(new ServletHolder(producerServlet), "/producer");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        server.join();


    }
}
