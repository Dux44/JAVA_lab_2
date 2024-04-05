public class Main {
    public static void main(String args[]) {
        int numElements = 100_000;
        int numThreads = 100;

        MyArray operation = new MyArray(numElements, numThreads);
        operation.initArr();

        operation.findMinimumElement();
    }
}