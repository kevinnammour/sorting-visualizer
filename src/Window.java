import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {
    public int WIDTH = 1525;
    public int HEIGHT = 780;
    public Rod[] rods;

    public Window() {
        this.setBackground(Color.DARK_GRAY);
        rods = new Rod[168];
        for (int i = 1; i <= rods.length; i++) {
            rods[i - 1] = new Rod(i, HEIGHT - (i * 4), 5, (i * 4), Color.WHITE);
        }
    }

    public void randomize() throws InterruptedException {
        for (int i = rods.length - 1; i >= 1; i--) {
            int k = (int) (Math.random() * (i + 1));
            swap(i, k);
        }
        repaint();
    }

    public void swap(int i, int j) throws InterruptedException {
        Rod temp = rods[i];
        rods[i] = rods[j];
        rods[j] = temp;
        rods[i].color = Color.RED;
        rods[j].color = Color.RED;
        repaint();
        Thread.sleep(20);
        rods[i].color = Color.WHITE;
        rods[j].color = Color.WHITE;
    }

    public void put(int index, Rod rod) throws InterruptedException {
        rods[index] = rod;
        rod.color = Color.RED;
        repaint();
        Thread.sleep(20);
        rod.color = Color.WHITE;
    }

    public void endIt() throws InterruptedException {
        Thread.sleep(50);
        for (int i = 0; i < rods.length; i++) {
            rods[i].color = Color.GREEN;
            repaint();
            Thread.sleep(5);
            // rods[i].color = Color.WHITE;
        }
        Thread.sleep(25);
        for (int i = 0; i < rods.length; i++) {
            rods[i].color = Color.WHITE;
        }
        repaint();

    }

    @Override
    public Dimension getPreferredSize() {
        // Overriding this method removes the default 10x10 dimension
        return new Dimension(WIDTH, HEIGHT);
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        super.paintComponent(graphics2D);
        int i = 1;
        for (Rod rod : rods) {
            graphics.setColor(rod.color);
            graphics.fillRect(i * 9, rod.y, rod.w, rod.h);
            i++;
        }
    }
}
