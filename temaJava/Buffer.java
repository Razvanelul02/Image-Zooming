package temaJava;

public class Buffer {
    private int[][] valoare;
    private boolean produced;

    public synchronized int[][] getValoare() {
        while (!produced) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        produced = false;
        notify();
        return valoare;
    }

    public synchronized void setValoare(int[][] valoare) {
        while (produced) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.valoare = valoare;
        produced = true;
        notify();
    }
}
