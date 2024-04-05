import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
public class MyArray {

    private final int numThreads;
    public final int[] array;
    private int indexMinValue = 0;
    public final CountDownLatch latch;
    private final ReentrantLock lock =  new ReentrantLock();

    public MyArray(int size, int numThreads) {
        this.array = new int[size];
        this.numThreads = numThreads;
        this.latch = new CountDownLatch(numThreads);
    }

    public void findMinimumElement() {

        int part = array.length / numThreads;
        int[] indexes = new int[numThreads + 1];

        int idx = 0;
        for (int i = 0; i < numThreads; i++) {
            indexes[i] = idx;
            idx += part;
        }
        indexes[numThreads] = array.length;

        for(int i = 0; i < numThreads; i++) {
            new Thread(new MinimumValFind(this, indexes[i], indexes[i+1])).start();;
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Minimal element in array - " + array[indexMinValue]);
        System.out.println("Index of minimal element in array - " + indexMinValue);
    }

    public void initArr() {
        Random rand = new Random();
        for(int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(0,100);
        }
        int idx = rand.nextInt(0, this.array.length);
        array[idx] = -1;
    }



    public void setMinimumIndex(int minIndex) {
        lock.lock();
        if (array[minIndex] < this.array[indexMinValue]) {
            this.indexMinValue = minIndex;
        }
        lock.unlock();
    }

}
