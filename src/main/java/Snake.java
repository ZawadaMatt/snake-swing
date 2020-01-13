import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class Snake extends JPanel implements ActionListener {

    private int windowWight = 500;
    private int windowHeight = 500;
    private int snakeSize = 3;
    private int appleX;
    private int appleY;
    private ArrayList<Integer> x = new ArrayList<>();
    private ArrayList<Integer> y = new ArrayList<>();
    private Timer timer;
    private Logger logger = Logger.getLogger(Snake.class.getName());

    private boolean leftMove = false;
    private boolean rightMove = true;
    private boolean upMove = false;
    private boolean downMove = false;

    Snake() {
        initGame();
    }

    public void initGame() {
        setFocusable(true);
        setPreferredSize(new Dimension(windowWight, windowHeight));

        timer = new Timer(500, this);

        for (int i = 0; i < snakeSize; i++) {
            x.add(50 - i * 10);
            y.add(50);
        }
        timer.start();
    }

    public void drawApple(Graphics g) {
        Random random = new Random();
        appleX = random.nextInt(windowWight);
        appleY = random.nextInt(windowHeight);
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, 10, 10);
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.green);
        for (int i = 0; i < snakeSize; i++) {
            g.fillOval(x.get(i), y.get(i), 10, 10);
            g.setColor(Color.PINK);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //drawApple(g);
        drawSnake(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
    public void move() {

        for (int i = snakeSize - 1; i > 0; i--) {
            x.set(i, x.get(i - 1));
            y.set(i, y.get(i - 1));
        }

        if (leftMove) {
            x.set(0, x.get(0) - 10);
        }
        if (rightMove) {
            x.set(0, x.get(0) + 10);
        }
        if (upMove) {
            y.set(0, y.get(0) + 10);
        }
        if (downMove) {
            y.set(0, y.get(0) - 10);
        }

    }

    private class Movement extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightMove)) {
                leftMove = true;
                upMove = false;
                downMove = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftMove)) {
                rightMove = true;
                upMove = false;
                downMove = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downMove)) {
                upMove = true;
                rightMove = false;
                leftMove = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upMove)) {
                downMove = true;
                rightMove = false;
                leftMove = false;
            }
        }
    }
}
