import java.util.concurrent.*;

public class CompletableFutureApiDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
            // 存在依赖关系（当前步错，不走下一步），当前步骤有异常的话就叫停
        }, threadPool).thenApply(f -> {
            System.out.println("222");
            return f + 2;
            // 计算结果存在依赖关系，这两个线程串行化---->有异常也可以往下走一步
        }).handle((f, e) -> {
            System.out.println("3333");
            int i=10/2;
            return f + 2;

        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getCause());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "------主线程先去做其他事情");
    }
}
