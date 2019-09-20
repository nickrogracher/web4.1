package DAO;

import model.DailyReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public void addSellToReport(long price){
        Query query = session.createQuery("from DailyReport order by id DESC");
        query.setMaxResults(1);
        DailyReport last = (DailyReport) query.uniqueResult();
        last.setEarnings(last.getEarnings() + price);
        last.setSoldCars(last.getSoldCars() + 1);
    }
}
