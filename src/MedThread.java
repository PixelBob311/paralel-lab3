import java.util.concurrent.Phaser;

public class MedThread extends Thread {
    private Phaser leftPhaser;
    private Phaser rightPhaser;
    double[] array;
    int start;
    int end;
    int N;
    public MedThread(Phaser left, Phaser right, double[] array, int start, int end, int iters) {
        this.leftPhaser = left;
        this.rightPhaser = right;
        this.array = array;
        this.start = start;
        this.end = end;
        this.N = iters;
        this.leftPhaser.register();
        this.rightPhaser.register();
    }

    @Override
    public void run() {
        double[] copy = this.array.clone();
        double[] main = this.array.clone();
        for (int iters = 0; iters <N; iters++) {
            for (int i = start; i <= end; i++) {
//                main[i] = (copy[i + 1] + copy[i - 1]) / 2;
                array[i] = (copy[i + 1] + copy[i - 1]) / 2;
//                if (i == start) {
//                    this.leftPhaser.arrive();
//                }
            }
//            this.leftPhaser.arrive();
//            rightPhaser.arriveAndAwaitAdvance();
//            for (int i = start; i <= end; i++) {
//                array[i] = main[i];
//                copy[i] = main[i];
//            }
//            leftPhaser.arrive();
//            rightPhaser.arrive();
        }
        //нужно ли после записи ожидать завершения еще одной фазы?
    }
}
