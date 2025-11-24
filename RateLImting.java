import java.util.concurrent.atomic.AtomicLong;

interface RateLimiter {
    boolean tryAcquire();
}

class LeakyBucketRateLimiter implements RateLimiter {

    private final long threshold;
    private final long windowUnit = 1000;
    private final AtomicLong water = new AtomicLong(0);

    private volatile long lastLeakTimestamp;

    public LeakyBucketRateLimiter(long threshold) {
        this.threshold = threshold;
        this.lastLeakTimestamp = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();

        long elapsed = currentTime - lastLeakTimestamp;

        if (elapsed > 0) {
            long leakedAmount = (elapsed / windowUnit) * 1;  

            if (leakedAmount > 0) {
                water.addAndGet(-leakedAmount);
                lastLeakTimestamp = currentTime;
            }
        }

        if (water.get() < 0) {
            water.set(0);
        }

        if (water.get() < threshold) {
            water.incrementAndGet();
            return true;
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        RateLimiter limiter = new LeakyBucketRateLimiter(5); // bucket capacity 5

        for (int i = 1; i <= 15; i++) {
            boolean allowed = limiter.tryAcquire();
            System.out.println("Request " + i + " allowed? " + allowed);
            Thread.sleep(200);
        }
    }
}
