import java.util.concurrent.Phaser;

public class FirstThread extends Thread {
    Phaser phaser;
    double[] array;
    int start;
    int end;

    int N;

    public FirstThread(Phaser phaser, double[] array, int start, int end, int iters) {
        this.phaser = phaser;
        this.array = array;
        this.start = start;
        this.end = end;
        this.N = iters;
        this.phaser.register();
    }

    @Override
    public void run() {
        double[] copy = this.array.clone();
        double[] main = this.array.clone();
        for (int iters = 0; iters < N; iters++) {
            for (int i = start; i <= end; i++) {
//                main[i] = (copy[i + 1] + copy[i - 1]) / 2;
                array[i] = (copy[i + 1] + copy[i - 1]) / 2;
            }
            this.phaser.arriveAndAwaitAdvance();


//            for (int i = start; i <= end; i++) {
//                array[i] = main[i];
//                copy[i] = main[i];
//            }
//            phaser.arriveAndAwaitAdvance();
        }
        //нужно ли после записи ожидать завершения еще одной фазы?
    }
}
