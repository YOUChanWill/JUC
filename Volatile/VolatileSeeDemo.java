import java.util.concurrent.TimeUnit;

public class VolatileSeeDemo {

    /**
     * ● volatile写之前的操作，都禁止重排序到volatile之后
     * ● volatile读之后的操作，都禁止重排序到volatile之前
     * ● volatile写之后volatile读，禁止重排序*/
    static volatile boolean flag = true; // 在jvm中设置了内存屏障

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-------come in");
            while (flag) {
            }
            System.out.println(Thread.currentThread().getName() + "\t-------flag被设置为false，程序停止");
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //更新flag值
        flag = false;

        System.out.println(Thread.currentThread().getName() + "\t 修改完成");
    }
}
