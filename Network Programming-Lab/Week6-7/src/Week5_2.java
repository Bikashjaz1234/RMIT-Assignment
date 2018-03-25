import java.io.*;
import java.util.*;
import java.util.Base64.Decoder;

//The Writer thread
class Writer implements Runnable {
    //Create some variables for receive the message and send the message
    sleepAndNotify q;
    public static String msg;
    Writer(sleepAndNotify q) {
        this.q = q;
        new Thread(this, "Writer").start();
    }

    //Running the thread
    public void run() {
        //Get user input
        final Scanner in = new Scanner(System.in);
        while (true) {
            msg = in .nextLine();
            //Send the input to Printer thread
            q.put(msg);

            //If user input 'x', stop the Writer thread
            if ("x".equalsIgnoreCase(msg)) {
                System.out.println("Ending Writer thread");
                break;
            } else if (!"x".equalsIgnoreCase(Writer.msg) || Writer.msg == null) {
                try {
                    System.out.println("The input thread will slppe 0.5 sec");
                    Thread.sleep(500);
                    System.out.println("The input thread wake up");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

class Printer implements Runnable {
    sleepAndNotify q;
    Printer(sleepAndNotify q) {
        this.q = q;
        new Thread(this, "Printer").start();
    }
    //Running the thread
    public void run() {
        while (true) {
            //Get user's input
            q.get();
            //Check user's input
            if ("x".equalsIgnoreCase(Writer.msg)) {
                //If user input X, stop thread.
                System.out.println("Ending Printer thread");
                break;
            }
        }

    }
}

//A class for sleep, notify and synchronized thread
class sleepAndNotify {
    boolean valueSet = false;

    //Get method, get user input from Writer thread
    synchronized String get() {
        if (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }

        //Print the message that it get.
        System.out.println("Printer: " + Writer.msg);
        valueSet = false;
        //notify the thread
        notify();
        return Writer.msg;
    }

    //Put method, put user input to Print thread
    synchronized void put(String n) {
        if (valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }

        valueSet = true;
        //Print the message that user input.
        System.out.println("Writer: " + Writer.msg);
        //notify the thread
        notify();
    }
}

//The main class, just invoke 2 threads.
class Week5_2 {
    public static void main(String args[]) {
        String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
        byte[] coreByteArr = null;
        String testInput = "c3ViamVjdF9uYW1lPU5QVzM=";
        sleepAndNotify q = new sleepAndNotify();
        Decoder decoder = Base64.getDecoder();
        coreByteArr = decoder.decode(coreVariable);
        coreVariable = new String(coreByteArr);
        coreByteArr = decoder.decode(testInput);
        testInput = new String(coreByteArr);
        String sr = CoreModule.coreCode(coreVariable, testInput);
        //If load core module success
        if (sr.equals("TRUE")) {
            new Writer(q);
            new Printer(q);
            System.out.println("I am Main method, I will do nothing after invoke 2 threads");
        } else {
            System.err.println("CoreMoudle Load failed\n" + sr);
        }
    }
}