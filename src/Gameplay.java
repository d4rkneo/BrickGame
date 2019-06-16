import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;

    private int score = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int positionBallX = 120;
    private int positionBallY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        //The Background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);

        //The Borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        //The Paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550, 100,8);

        //The Ball
        g.setColor(Color.yellow);
        g.fillOval(positionBallX,positionBallY, 20,20);

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){
            if(new Rectangle(positionBallX,positionBallY,20,20).intersects(new Rectangle(playerX,550,100,8)))
                ballYDir = -ballYDir;
            
            positionBallX += ballXDir;
            positionBallY += ballYDir;

            if(positionBallX < 0)
                ballXDir = - ballXDir;
            if(positionBallY < 0)
                ballYDir = - ballYDir;
            if(positionBallX > 670)
                ballXDir = - ballXDir;

        }
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX >= 600)
                playerX = 600;
            else
                moveRight();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX <= 10)
                playerX = 10;
            else
                moveLeft();
        }
    }
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
