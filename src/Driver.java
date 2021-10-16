import javax.swing.*;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        Window w = new Window();
        JFrame jFrame = new JFrame();
        jFrame.add(w);
        jFrame.pack();
        jFrame.setVisible(true);
        Thread.sleep(1000);
        w.randomize();
        Thread.sleep(1000);
        Sort sort = new Sort();
        sort.mergeINPLACE(w);
        w.endIt();
        jFrame.setVisible(false);
    }
}
