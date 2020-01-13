import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Logger;

public class Snake extends JPanel implements ActionListener {

    private int windowWight = 500;
    private int windowHeight = 500;
    private int snakeSize = 3;
    private int appleX;
    private int appleY;
    private int[] x = new int[10000];
    private int[] y = new int[10000];
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

        timer = new Timer(50, this);
        addKeyListener(new Movement());

        for (int i = 0; i < snakeSize; i++) {
            x[i] = (i * 10);
            y[i] = (50);
        }
        generateAppleLocation();
        timer.start();
    }

    public void generateAppleLocation() {
        Random random = new Random();
        appleX = random.nextInt(Math.round(windowWight / 10)) * 10;
        appleY = random.nextInt(Math.round(windowHeight / 10)) * 10;
    }

    public boolean checkCollision () {

        if (x[0] == appleX && y[0] == appleY) {
            snakeSize++;
            generateAppleLocation();
        }
        return true;
    }

    public void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, 10, 10);
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.green);
        for (int i = 0; i < snakeSize; i++) {
            g.fillOval(x[i], y[i], 10, 10);
            g.setColor(Color.PINK);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawApple(g);
        drawSnake(g);
        setBackground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollision();
        move();
        repaint();
    }

    private void move() {

        for (int z = snakeSize; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftMove) {
            x[0] -= 10;
        }

        if (rightMove) {
            x[0] += 10;
        }

        if (upMove) {
            y[0] -= 10;
        }

        if (downMove) {
            y[0] += 10;
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
