import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException,InterruptedException {
        FutureTask<String> futureTask = new FutureTask(new MyThread());
        // 开启异步任务，主线程执行完成之后来获取
        Thread thread = new Thread(futureTask);
        thread.start();
        // get方法只有获取到结果才会结束
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("========come in");
//        Thread.sleep(10000);
        return "hello Callable";
    }
}
