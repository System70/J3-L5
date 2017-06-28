import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

        // added to limit tunnel bandwidth
    private Semaphore sem;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
            // added to limit tunnel bandwidth
        this.sem = new Semaphore(MainClass.CARS_COUNT / 2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);

                    // added to limit tunnel bandwidth
                sem.acquire();

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);

                // added to limit tunnel bandwidth
                sem.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
