import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

    class MyVar {
        public volatile Boolean isInit = Boolean.FALSE;
        AtomicReferenceFieldUpdater<MyVar, Boolean> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");

        public void init(MyVar myVar) {
            if (referenceFieldUpdater.compareAndSet(myVar, Boolean.FALSE, Boolean.TRUE)) {
                System.out.println(Thread.currentThread().getName() + "\t" + "--------------start init ,need 2 secondes");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "--------------over init");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t" + "--------------已经有线程进行初始化工作了。。。。。");
            }
        }
    }

    public class AtomicReferenceFieldUpdaterDemo1 {

        public static void main(String[] args) {
            MyVar myVar = new MyVar();
            for (int i = 1; i <= 5; i++) {
                new Thread(() -> {
                    myVar.init(myVar);
                }, String.valueOf(i)).start();
            }
        }
    }

