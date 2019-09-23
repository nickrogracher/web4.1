package service;

import DAO.DailyReportDao;
import model.DailyReport;
import model.DailyReportHandler;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }


    public DailyReport getLastReport() {
        try {
            Session session = sessionFactory.openSession();
            DailyReportDao dailyReportDao = new DailyReportDao(session);
            DailyReport dailyReport = dailyReportDao.lastReport();
            session.clear();
            return dailyReport;
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }



    public boolean makeNewDayReport(){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            DailyReportDao dailyReportDao = new DailyReportDao(session);
            dailyReportDao.newDayReport();
            DailyReportHandler.getInstance().refreshReportHandler();
            transaction.commit();
            session.clear();
            return true;
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    public void deleteAllReports() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            DailyReportDao dailyReportDao = new DailyReportDao(session);
            dailyReportDao.deleteReports();
            transaction.commit();
            session.clear();
        }
        catch (HibernateException e){
            throw new HibernateException(e);
        }
    }
}
