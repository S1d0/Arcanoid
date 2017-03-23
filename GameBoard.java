import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GameBoard extends JFrame{

    public static int boardWidth = 1000;
    public static int boardHeight = 800;

    public static void main(String[] args) {
        new GameBoard();
    }

    public GameBoard(){

        this.setSize(boardWidth,boardHeight);
        this.setTitle("Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameDrawingPanel gamePanel = new GameDrawingPanel();
        this.add(gamePanel, BorderLayout.CENTER);

        //ScheduledThreadPoolExecutor
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new RepaintTheBoard(this),0L,20L, TimeUnit.MILLISECONDS);
        this.setVisible(true);
    }
}

class RepaintTheBoard implements Runnable{

    GameBoard theBoard;

    public RepaintTheBoard(GameBoard theBoard){
        this.theBoard = theBoard;
    }

    @Override
    public void run() {
        theBoard.repaint();
    }
}

class GameDrawingPanel extends JComponent{

    //Holds every rock created
    public ArrayList<Rock> rocks = new ArrayList<Rock>();

    //Get the orginal x & y points for the Rock polygon
    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;

    int width = GameBoard.boardWidth;
    int height = GameBoard.boardHeight;

    public GameDrawingPanel(){

        for(int i=0; i < 10; i++){
            int randomXStartPos = (int) (Math.random()*(GameBoard.boardWidth - 50)+1);
            int randomYStartPos = (int) (Math.random()*(GameBoard.boardHeight -50)+1);

            rocks.add(new Rock(
                    Rock.getpolyXArray(randomXStartPos),
                    Rock.getpolyYArray(randomYStartPos),
                    13,
                    randomXStartPos,
                    randomYStartPos
                    ));
        }
    }

    public void paint(Graphics g){

        Graphics2D graphicSettings = (Graphics2D)g;
        graphicSettings.setColor(Color.BLACK);
        graphicSettings.fillRect(0, 0, getWidth(), getHeight());
        graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        for(Rock rock:rocks){

            graphicSettings.setColor(Color.YELLOW);
            rock.move();
            graphicSettings.draw(rock);
        }
    }
}