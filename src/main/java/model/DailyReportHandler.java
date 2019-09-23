package model;

public class DailyReportHandler {
    private static DailyReportHandler dailyReportHandler;
    private Long earnings;
    private Long soldCars;

    public DailyReportHandler(long earnings, long soldCars){
        this.earnings = earnings;
        this.soldCars = soldCars;
    }

    public static DailyReportHandler getInstance() {
        if (dailyReportHandler == null) {
            dailyReportHandler = new DailyReportHandler(0, 0);
        }
        return dailyReportHandler;
    }

    public Long getSoldCars() {
        return soldCars;
    }

    public void setSoldCars(Long soldCars) {
        this.soldCars = soldCars;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public void addToReportHandler(long price){
        this.earnings = getEarnings() + price;
        this.soldCars = getSoldCars() + 1;
    }

    public void refreshReportHandler(){
        this.earnings = 0L;
        this.soldCars = 0L;
    }
}
