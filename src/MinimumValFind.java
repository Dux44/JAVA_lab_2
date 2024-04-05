public class MinimumValFind implements Runnable {
    private final int start;
    private final int end;
    private final MyArray myArr;

    public MinimumValFind(MyArray myArr, int startIdx, int endIdx) {
        this.start = startIdx;
        this.end = endIdx;
        this.myArr = myArr;
    }

    @Override
    public void run() {
        int currentMinIdx = 0;
        for(int i = start; i < end; i++) {
            if (myArr.array[i] < myArr.array[currentMinIdx])
                currentMinIdx = i;
        }
        myArr.setMinimumIndex(currentMinIdx);
        myArr.latch.countDown();
    }

}
