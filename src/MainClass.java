    // added to synchronize
import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;

        // added to synchronize start
    public static final CyclicBarrier cbReady = new CyclicBarrier(CARS_COUNT, new Runnable() {
            @Override
            public void run() {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            }
        });

        // added to limit tunnel bandwidth
    public static final Semaphore semTunnel = new Semaphore(CARS_COUNT / 2);

        // added to synchronize finish message
    public static final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
            // moved to barrierAction to sout synchronized
//        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            // added to synchronize finish message
        try {
            cdlFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
