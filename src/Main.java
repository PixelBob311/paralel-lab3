import java.util.concurrent.Phaser;


public class Main {


    public static void main(String[] args) {
        double[] array = {1, 5, 2, 3, 7, 3, 6, 1};
        int iters = 2;
        System.out.print("seq:\t ");
        seq(array, iters);


        int threadsCount = 3;
        int phasersCount = threadsCount - 1;
        Phaser[] phasers = new Phaser[phasersCount];
        for (int k = 0; k < phasers.length; k++) {
            phasers[k] = new Phaser();
        }
        Thread first = new FirstThread(phasers[0], array, 1, 2, iters);
        Thread mid = new MedThread(phasers[0], phasers[1], array, 3, 4, iters);
        Thread last = new LastThread(phasers[1], array, 5, 6, iters);
        first.start();
        mid.start();
        last.start();


//        boolean flag = true;
//        do{
//            for(int i = 0; i < phasers.length; i++){
//                flag = flag && phasers[i].isTerminated();
//            }
//        }while(!flag);
        System.out.print("par:\t ");
        for (double element : array) {
            System.out.print(element + "\t ");
        }
    }

    static void seq(double[] array, int itersCount) {
        double[] copy = array.clone();
        double[] main = array.clone();
        for (int iters = 0; iters < itersCount; iters++) {
            for (int i = 1; i <= array.length - 2; i++) {
                main[i] = (copy[i - 1] + copy[i + 1]) / 2;
            }
            copy = main.clone();
        }
        printArray(copy);
    }

    static void printArray(double[] arr) {
        for (double item : arr) {
            System.out.print(item + "\t ");
        }
        System.out.println();
    }
}