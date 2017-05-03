package ponggame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PongGame extends JPanel implements KeyListener{  //inherits from JPanel Class
    
    public PongGame ()
    {
        addKeyListener(this);
    }
    
    boolean up;
    boolean down;
    boolean gameOver = true;
    int point;
    int highScore;
    int ball_x = 10;
    int ball_y = 10;
    int width_frame = 1024;
    int height_frame = 768;
    int left_Rect_Position_Height = 100;
    int left_Rect_Bar_Height = 200;
    boolean reverse_x = false;
    boolean reverse_y = false;
    
    @Override
    public void paint(Graphics g) //changes the paint public function
    {
        super.paint(g); //needed for the inheritance
        g.fillOval(ball_x, ball_y, 10, 10);
        g.fillRect(10, left_Rect_Position_Height, 7, left_Rect_Bar_Height);
        g.drawString("HighScore: " + highScore, 450, 10);
        g.drawString("Score: " + point, 550, 10);
    }
    
    private void moveBall()
    {
        if (gameOver == false)
        {
            if (ball_x < (width_frame-20) && reverse_x != true)
            {
                ball_x += 6;
            }
            else
                reverse_x = true;
            if (ball_x > 7 && reverse_x != false)
            {
                ball_x -= 8;
            }
            else
                reverse_x = false;
            if (ball_x <= 7)
                gameOver = true;

            if (ball_y < (height_frame-50) && reverse_y != true)
            {
                ball_y += 7;
            }
            else
                reverse_y = true;
            if (ball_y > 7 && reverse_y != false)
            {
                ball_y -= 5;
            }
            else
                reverse_y = false;
        }
        else
        {
            ball_x = 10;
            ball_y = 10;
            left_Rect_Position_Height = 100;
            left_Rect_Bar_Height = 200;
            reverse_x = false;
            reverse_y = false;
            point = 0;
        }
    }
    
    private void point()    //Adds points and does highScore
    {

        point++;
        if (point > highScore)
            highScore = point;

    }
    
    public void moveBar()
    {
        if (up == true 
            && left_Rect_Position_Height > 5)
        {
            left_Rect_Position_Height +=- 5;
        }
        if (down == true
             && (left_Rect_Position_Height + left_Rect_Bar_Height) < (height_frame-40))
        {
            left_Rect_Position_Height += 5;
        }
        if ((ball_x - 10) <= 7
             && (ball_y - 10) > left_Rect_Position_Height
             && (ball_y - 10) < (left_Rect_Position_Height + left_Rect_Bar_Height)
             && left_Rect_Bar_Height > 75)
        {
            left_Rect_Bar_Height -= 5;
            reverse_x = false;
            point();
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver == false)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                default:
                    break;
            }
        }
        else
        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                gameOver = false;
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            default:
                break;
        }
    }

    
    public static void main(String[] args) throws InterruptedException 
    {
        int width = 1024;
        int height = 768;
        JFrame frame = new JFrame("Pong Game"); //creating a frame object
        frame.setSize(width, height); //sets the size of the frame
        frame.setLocation(400, 100); //tells the frame where to open
        frame.setResizable(false);
        frame.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the default to exig when the X is pushed no matter what
        PongGame game = new PongGame();
        frame.add(game); //adds the game object to the frame
        frame.setVisible(true); //sets the frame to be visible so you can see it
        game.setFocusable(true);
        game.requestFocusInWindow();
        while(true)
        {
            game.moveBall();
            game.moveBar();
            game.repaint();
            Thread.sleep(8); //added throw clause
        }
    }
}
