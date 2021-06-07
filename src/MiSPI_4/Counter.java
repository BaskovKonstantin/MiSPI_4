package MiSPI_4;

import MiSPI_4.CounterMBean;

public class Counter implements CounterMBean {
        private int FalseCounter;
        private int ShotCounter;

    public int getFalseCounter() {
        return FalseCounter;
    }

    public int getShotCounter() {
        if (ShotCounter%15 == 0){
            System.out.println("Количество попыток кратно 15");
        }
        return ShotCounter;
    }

    public void setFalseCounter(int falseCounter) {
        FalseCounter = falseCounter;
    }

    public void setShotCounter(int shotCounter) {
        ShotCounter = shotCounter;
    }
}
