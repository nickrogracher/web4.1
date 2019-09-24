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


    public static void main(String args[]) throws Exception{

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
