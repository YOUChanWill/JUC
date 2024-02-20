import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo3 {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----------come in");
            // Peimit许可证默认没有不能放行，所以一开始调用park()方法当前线程会阻塞
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----------被唤醒");
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----------发出通知");
            // 调用unpack(thread)方法后 就会将thread线程的许可证peimit发放，会自动唤醒park线程
            LockSupport.unpark(t1);
        }, "t2").start();

    }
}
