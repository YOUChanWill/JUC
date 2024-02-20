import java.util.concurrent.*;

public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException,InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 设置线程池

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },executorService);

        System.out.println(completableFuture.get());

        CompletableFuture<String> objectCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "hello supplyAsyns";
        },executorService);

        System.out.println(objectCompletableFuture.get());

        executorService.shutdown();
    }
}
