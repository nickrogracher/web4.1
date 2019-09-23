package DAO;

import model.DailyReport;
import model.DailyReportHandler;
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

    public void newDayReport(){
        session.save(new DailyReport(DailyReportHandler.getInstance().getEarnings(), DailyReportHandler.getInstance().getSoldCars()));
    }

    public DailyReport lastReport(){
        Query query = session.createQuery("from DailyReport by id DESC");
        query.setMaxResults(1);
        return (DailyReport) query.uniqueResult();
    }

    public void deleteReports() {
        Query query =  session.createQuery("delete from DailyReport");
        query.executeUpdate();
    }
}
