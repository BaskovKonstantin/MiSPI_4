package MiSPI_4;

import MiSPI_4.Counter;
import MiSPI_4.Statistic;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Main {
    public static Vector History = new Vector();
    public static int  FalseCount, ShotCount;
    public static double StatisticVariable;

    public static void HistoryCommand(){
        for (int i  = 0; i<History.size(); i++ ){
            System.out.println((i+1)+"."+ History.get(i));
        }

    }

    public static float InputVariable(String InputVariable) throws IOException {
        System.out.println("Please, Enter "+InputVariable+":");
        Scanner in = new Scanner(System.in);
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String VariableString = bufferedReader.readLine();
        float Variable = Float.parseFloat(VariableString);
        return Variable;
    }

    public static boolean CheckShot(float x,float y, float r) throws IOException {

        if ((x>0 && y <0)&&(x*x+y*y)<((r/2)*(r/2))) return true;
        if ((x<0 && y >0)&&((y-x)<(r/2))) return true;
        if ((x<0 && y <0)&&((-x>-(r/2))&&(-y>-r))) return true;
        return false;


    }

    public static void ShotCommand() throws IOException {
        float x,y,r;
        x = InputVariable("x");
        y = InputVariable("y");
        r = InputVariable("r");

        if (CheckShot(x,y,r)){
            System.out.println("True");
            History.add("True");
        }
        else {History.add("False");
            System.out.println("False");
            FalseCount++;
        }
        ShotCount++;

    }
    public static void CommandShot15() throws IOException {
        float x,y,r;
        for ( int i=0; i<15;i++) {
            x=-5+(int)(Math.random()*10);
            y=-5+(int)(Math.random()*10);
            r=-5+(int)(Math.random()*10);
            if (CheckShot(x, y, r)) {
                System.out.println("True");
                History.add("True");
            } else {
                History.add("False");
                System.out.println("False");
                FalseCount++;
            }
            ShotCount++;
        }
    }

    public static void HelpCommand() throws IOException {
        System.out.println("Command list");
        System.out.println("Help - Displaying a list of commands");
        System.out.println("History - Displaying history of results");
        System.out.println("Shot - Just take a shot");
    }

    public static String InputCommand() throws IOException {
        System.out.println("Please, Enter a command");
        Scanner in = new Scanner(System.in);
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String command = bufferedReader.readLine();
        return command;
    }

    public static void main(String[] args) throws IOException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException  {
        FalseCount=0;
        ShotCount=0;
        StatisticVariable =0;

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName BeanCounter = new ObjectName("MiSPI_4:type=Counter");
        Counter counter = new Counter();
        mbs.registerMBean(counter, BeanCounter);

        MBeanServer mbs1 = ManagementFactory.getPlatformMBeanServer();
        ObjectName BeanStatistic = new ObjectName("MiSPI_4:type=Statistic");
        Statistic statistic = new Statistic();
        mbs1.registerMBean(statistic, BeanStatistic);

        while (true) {
            String command = InputCommand();
            if (command.equals("help")){
                HelpCommand();
            }
            if (command.equals("shot")){
                ShotCommand();
                StatisticVariable = (FalseCount/ShotCount)*100;
                }
            if (command.equals("history")){
                HistoryCommand();
                }
            if (command.equals("shot15")){
                CommandShot15();
                StatisticVariable =100;
                StatisticVariable = FalseCount;
                StatisticVariable = ShotCount;
                StatisticVariable = (((float) FalseCount/ (float) ShotCount)*100);

                System.out.println(StatisticVariable);
            }


            counter.setFalseCounter(FalseCount);
            counter.setShotCounter(ShotCount);


            statistic.setStatistic((float) StatisticVariable);



        }

    }


}
