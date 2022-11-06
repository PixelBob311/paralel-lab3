import java.util.concurrent.Phaser;

public class MyThread extends Thread {
    Phaser phaser;
    double[] array;
    int start;
    int end;

    public MyThread(Phaser phaser, double[] array, int start, int end) {
        this.phaser = phaser;
        this.array = array;
        this.start = start;
        this.end = end;
        this.phaser.register();
    }

    //    @Override
//    public void run() {
//        double[] copy = this.array.clone();
//        for (int iters = 1; iters <= 3; iters++) {
//            for (int i = start; i <= end; i++) {
//                copy[i] = copy[i] * iters;
//            }
//            this.phaser.arrive();
//            this.phaser.awaitAdvance(iters - 1);
//            for (int i = start; i <= end; i++) {
//                array[i] = copy[i];
//            }
//            this.phaser.arrive();
//            this.phaser.awaitAdvance(iters + 1);
//        }
//    }
    @Override
    public void run() {
        double[] copy = this.array.clone();
        double[] main = this.array.clone();
        for (int iters = 1; iters <= 3; iters++) {
            for (int i = start; i <= end; i++) {
                main[i] = (copy[i+1] + copy[i-1]) / 2;
                if(i == end - 1){
                    this.phaser.arriveAndAwaitAdvance();
                }
            }

            for (int i = start; i <= end; i++) {
                array[i] = main[i];
                copy[i] = main[i];
            }
//            this.phaser.arrive();
//            this.phaser.awaitAdvance(iters);
        }
    }
}
