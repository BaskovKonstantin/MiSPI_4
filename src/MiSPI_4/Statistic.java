package MiSPI_4;

import MiSPI_4.StatisticMBean;

public class Statistic implements StatisticMBean {
    private float statistic;

    public float getStatistic() {
        return statistic;
    }

    public void setStatistic(float statistic) {
        this.statistic = statistic;
    }
}
