import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        /**
         * CAS(compare and swap)，比较并交换，
         * 执行CAS操作的时候，将内存位置的值与预期原值进行比较：
         * ● 如果相匹配，那么处理器会自动将该位置更新为新值
         * ● 如果不匹配，处理器不做任何操作，多个线程同时执行CAS操作只有一个会成功。*/
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());//true	2022
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());//false	2022
    }
}
