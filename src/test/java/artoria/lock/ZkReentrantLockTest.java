package artoria.lock;

import artoria.util.ThreadUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.*;

@Ignore
public class ZkReentrantLockTest {
    private ExecutorService pool;
    private Integer num = 100;

    @Before
    public void init() {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        pool = new ThreadPoolExecutor(20, 20, 0L
                , TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
        LockUtils.setLocker(new ZkReentrantLocker("127.0.0.1"));
    }

    @After
    public void destroy() {

        pool.shutdown();
    }

    @Test
    public void test1() {
        final String lockName = "test1";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                long millis = System.currentTimeMillis();
                for (int j = 0; j < 1000000; j++) {
                    if (num < 0) { continue; }
                    try {
                        System.out.println(threadName);
                        LockUtils.lock(lockName);
                        if (num < 0) { continue; }
                        System.out.println(threadName + " | " + (num--));
                    }
                    finally {
                        System.out.println(threadName + " unlock");
                        LockUtils.unlock(lockName);
                    }
                }
                System.out.println(threadName + ": " + (System.currentTimeMillis() - millis) + "ms");
            }
        };
        for (int i = 0; i < 20; i++) {
            pool.submit(runnable);
        }
        ThreadUtils.sleepQuietly(1000);
    }

    @Test
    public void test2() {
        final String lockName = "test2";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                long millis = System.currentTimeMillis();
                for (int j = 0; j < 1000000; j++) {
                    if (num < 0) { continue; }
                    // boolean tryLock = LockUtils.tryLock(lockName);
                    boolean tryLock = false;
                    try {
                        tryLock = LockUtils.tryLock(lockName, 50, TimeUnit.MILLISECONDS);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadName + " tryLock: " + tryLock);
                    if (!tryLock) { continue; }
                    try {
                        if (num < 0) { continue; }
                        System.out.println(threadName + " | " + (num--));
                    }
                    finally {
                        System.out.println(threadName + " unlock");
                        LockUtils.unlock(lockName);
                    }
                }
                System.out.println(threadName + ": " + (System.currentTimeMillis() - millis) + "ms");
            }
        };
        for (int i = 0; i < 20; i++) {
            pool.submit(runnable);
        }
        ThreadUtils.sleepQuietly(1000);
    }

}
