import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureApiDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName() + "-------come in");
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "task over";
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName() + "------- 执行其他任务");
        while (true){
            // 使用轮询方式查询异步结果
            if (futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else{
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中");
            }
        }
    }
}
