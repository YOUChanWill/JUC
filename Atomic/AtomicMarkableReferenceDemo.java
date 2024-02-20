import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;


/**
 * ● AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
 *   ○ 解决修改过几次
 * ● AtomicMarkableReference：原子更新带有标记的引用类型。该类将 boolean 标记与引用关联起来
 *   ○ 解决是否修改过，它的定义就是将标记戳简化为true/false，类似于一次性筷子*/

public class AtomicMarkableReferenceDemo {
    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标识: " + marked);//t1	默认标识: false
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            markableReference.compareAndSet(100, 1000, marked, !marked);//t2	默认标识: false

        }, "t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标识: " + marked);//t2	t2线程CASResult：false
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t" + "t2线程CASResult：" + b);
            System.out.println(Thread.currentThread().getName() + "\t" + markableReference.isMarked());//t2	true
            System.out.println(Thread.currentThread().getName() + "\t" + markableReference.getReference());//t2	1000

        }, "t2").start();
    }
}
