package ex01;

public class EggHenPrinter {
    private boolean isEggTurn = true;

    public synchronized  void printEgg(){
        while(!isEggTurn){
            try {
                wait();
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Egg");
        isEggTurn = false;
        notify();
    }
    public synchronized void printHen(){
        while(isEggTurn){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Hen");
        isEggTurn = true;
        notify();
    }


}
