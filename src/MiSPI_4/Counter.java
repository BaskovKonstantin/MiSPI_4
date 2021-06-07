package MiSPI_4;

import MiSPI_4.CounterMBean;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;


public class Counter extends NotificationBroadcasterSupport implements CounterMBean {
        private int FalseCounter;
        private int ShotCounter;
        private int NotificationCount;

    public int getFalseCounter() {
        return FalseCounter;
    }


    public int getShotCounter() {
        if (ShotCounter%15 == 0){
            System.out.println("Количество попыток кратно 15");
            Notification notification = new Notification(
                    "Notifiaction", this,
                    NotificationCount++, "Кратно 15" );
            sendNotification(notification);
        };
        return ShotCounter;
    }

    public void setFalseCounter(int falseCounter) {
        FalseCounter = falseCounter;
    }

    public void setShotCounter(int shotCounter) {
        ShotCounter = shotCounter;
    }
}
