import java.awt.*;

public class Rock extends Polygon {

    private int uLeftXPos;
    private int uLeftYPos;

    private int xDirection;
    private int yDirection;

    private int boardWidth = GameBoard.boardWidth;
    private int boardHeight = GameBoard.boardHeight;

//    int[] polyYArray; int[] polyXArray;


    public static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
    public static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};

    public Rock( int[] polyXArray, int[]polyYArray,int pointsPoly, int randomXStartPos,int randomYStartPos){

        //Create a polygon by calling parent class
        super(polyXArray,polyYArray,pointsPoly);

        //Generate random speed of polygon
        this.xDirection = (int) (Math.random()*4+1);
        this.yDirection = (int) (Math.random()*4+1);

        //Holds the starting x&y position for the Rock
        this.uLeftXPos = randomXStartPos;
        this.uLeftYPos = randomYStartPos;
    }

    public void move(){

        // Get the upper left and top most point for the Polygon
        int uLeftXPos = super.xpoints[0];
        int uLeftYPos = super.ypoints[0];

        if(uLeftXPos < 0 || (uLeftXPos+25) > boardWidth){
            xDirection = -xDirection;
        }
        if(uLeftYPos < 0 || (uLeftYPos+25) > boardHeight){
            yDirection = -yDirection;
        }
        for (int i=0; i<super.xpoints.length; i++){
            super.xpoints[i] += xDirection;
            super.ypoints[i] += yDirection;
        }
    }

    public static int[] getpolyXArray(int randomXStartPos){
        int[] tempXArray = sPolyXArray.clone();

        for(int i=0; i<tempXArray.length; i++){
            tempXArray[i] +=randomXStartPos;
        }
        return tempXArray;
    }

    public static int[] getpolyYArray(int randomYStartPos){
        int[] tempYrray = sPolyYArray.clone();

        for(int i=0; i<tempYrray.length; i++){
            tempYrray[i] +=randomYStartPos;
        }
        return tempYrray;
    }

}
