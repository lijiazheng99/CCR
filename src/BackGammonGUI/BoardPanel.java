package BackGammonGUI;

import BackGammon.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;

import static BackGammon.BackGammon.NUM_PLAYERS;

//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Rectangle2D;
//import java.io.IOException;
//import java.awt.image.BufferedImage;
//import java.awt.*;



class BoardPanel extends StackPane
{
    private static final long serialVersionUID = 1L;
    private static final int FRAME_WIDTH = 752, FRAME_HEIGHT = 552;  // must be multiples of 4
    private static final int BORDER_TOP = 40, BORDER_BOTTOM = 75, BORDER_LEFT = 66, BORDER_RIGHT = 60;
    public static final int PIP_WIDTH = 47, BAR_WIDTH = 66;
    private static final int CHECKER_RADIUS = 16, CHECKER_DEPTH = 8, LINE_WIDTH = 2;   // must be even
    public static final int TILE_SIZE = 70;

    private Color[] checkerColors;
    private Board board;
    private Image boardImage;
    private GraphicsContext g2;

    public BoardPanel(Board board)
    {
        checkerColors = new Color[NUM_PLAYERS];
        this.board = board;

        setHeight(FRAME_HEIGHT);
        setWidth(FRAME_WIDTH);

        setStyle("-fx-background-color: yellow");
        //setBackground(new Background());

        checkerColors[0] = Color.rgb(219,59,37);
        checkerColors[1] = Color.rgb(213,213,213);

        try
        {
            boardImage  = new Image(new FileInputStream("src//board.jpg"));
            System.out.println("Good");
        }
        catch (IOException ex)
        {
            System.out.println("Could not find the image file " + ex.toString());
        }
    }

    private void displayChecker (int player, int x, int y)
    {
        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        ellipse.setFill(checkerColors[player]);

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2);

        /*
        *
         */

//        Ellipse bkg = new Ellipse(x , y,TILE_SIZE * 0.3125,TILE_SIZE * 0.26);
//        bkg.setFill(Color.BLACK);
//        bkg.setStroke(Color.BLACK);
//        bkg.setStrokeWidth(TILE_SIZE * 0.03);


//        g2.setColor(BackGammon.Color.BLACK);
//        Ellipse2D.Double ellipseBlack = new Ellipse2D.Double(x,y,2*CHECKER_RADIUS,2*CHECKER_RADIUS);
//        g2.fill(ellipseBlack);
//        Ellipse2D.Double ellipseColour = new Ellipse2D.Double(x+LINE_WIDTH,y+LINE_WIDTH,2*(CHECKER_RADIUS-LINE_WIDTH),2*(CHECKER_RADIUS-LINE_WIDTH));
//        g2.setColor(checkerCheckerColors[player]);
//        g2.fill(ellipseColour);
    }


    private void displayCheckerSide (int player, int x, int y)
    {
//        g2.setColor(BackGammon.Color.BLACK);
//        Rectangle2D.Double rectangleBlack = new Rectangle2D.Double(x,y,2*CHECKER_RADIUS,CHECKER_DEPTH);
//        g2.fill(rectangleBlack);
//        Rectangle2D.Double rectangleColour = new Rectangle2D.Double(x+LINE_WIDTH,y+LINE_WIDTH,2*(CHECKER_RADIUS-LINE_WIDTH),CHECKER_DEPTH-2*LINE_WIDTH);
//        g2.setColor(checkerCheckerColors[player]);
//        g2.fill(rectangleColour);

        Rectangle rectangleBlack = new Rectangle(x,y,2*CHECKER_RADIUS, CHECKER_DEPTH);
        rectangleBlack.setFill(Color.BLACK);
        Rectangle rectangleColor = new Rectangle(x+LINE_WIDTH,y+LINE_WIDTH,2*(CHECKER_RADIUS-LINE_WIDTH),CHECKER_DEPTH-2*LINE_WIDTH);
        rectangleColor.setFill(checkerColors[player]);

    }



    public void paintComponent(GraphicsContext g) {
        //super.paintComponent(g);
//        g2 =(Graphics2D) g;
        //g2.drawImage(boardImage, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
        for (int player=0; player<BackGammon.NUM_PLAYERS; player++) {
            int x,y;
            // Display Pip Numbers
            for (int pip=1; pip<=Board.NUM_PIPS; pip++) {
                if (pip>3*Board.NUM_PIPS/4) {
                    x = FRAME_WIDTH/2 + BAR_WIDTH/2 + (pip-3*Board.NUM_PIPS/4-1)*PIP_WIDTH+PIP_WIDTH/4;
                } else if (pip>Board.NUM_PIPS/2) {
                    x = BORDER_LEFT + (pip-Board.NUM_PIPS/2-1)*PIP_WIDTH+PIP_WIDTH/4;
                } else if (pip>Board.NUM_PIPS/4) {
                    x = BORDER_LEFT + (Board.NUM_PIPS/2-pip)*PIP_WIDTH+PIP_WIDTH/4;
                } else {
                    x = FRAME_WIDTH/2 + BAR_WIDTH/2 + (Board.NUM_PIPS/4-pip)*PIP_WIDTH+PIP_WIDTH/4;
                }
                if (pip>Board.NUM_PIPS/2) {
                    y = 3*BORDER_TOP/4;
                } else {
                    y = FRAME_HEIGHT-BORDER_BOTTOM/4;
                }
                g2.setFill(checkerColors[1]);
                g2.setFont(new Font("Courier",16));
                //g2.drawString(Integer.toString(pip),x,y);
            }
            // Display Bar
            for (int count=1; count<=board.getNumCheckers(player, Board.BAR); count++) {
                x = FRAME_WIDTH/2-CHECKER_RADIUS;
                if (player==0) {
                    y = FRAME_HEIGHT/4+(count-1)*CHECKER_RADIUS;
                } else {
                    y = 3*FRAME_HEIGHT/4-(count-1)*CHECKER_RADIUS;
                }
                displayChecker(player,x,y);
            }
            // Display Main Board
            for (int pip=1; pip<=Board.NUM_PIPS; pip++) {
                for (int count=1; count<=board.getNumCheckers(player,pip); count++) {
                    if (pip>3*Board.NUM_PIPS/4) {
                        x = FRAME_WIDTH/2 + BAR_WIDTH/2 + (pip-3*Board.NUM_PIPS/4-1)*PIP_WIDTH;
                    } else if (pip>Board.NUM_PIPS/2) {
                        x = BORDER_LEFT + (pip-Board.NUM_PIPS/2-1)*PIP_WIDTH;
                    } else if (pip>Board.NUM_PIPS/4) {
                        x = BORDER_LEFT + (Board.NUM_PIPS/2-pip)*PIP_WIDTH;
                    } else {
                        x = FRAME_WIDTH/2 + BAR_WIDTH/2 + (Board.NUM_PIPS/4-pip)*PIP_WIDTH;
                    }
                    if ( (player==0 && pip>Board.NUM_PIPS/2) || (player==1 && pip<Board.NUM_PIPS/2) ){
                        y = BORDER_TOP + (count-1)*2*CHECKER_RADIUS;
                    } else {
                        y = FRAME_HEIGHT - BORDER_BOTTOM - (count-1)*2*CHECKER_RADIUS;
                    }
                    displayChecker(player,x,y);
                }
            }
            // Display Bear Off
            for (int count=1; count<=board.getNumCheckers(player,Board.BEAR_OFF); count++) {
                x = FRAME_WIDTH - BORDER_RIGHT/2 - CHECKER_RADIUS;
                if (player==0) {
                    y = FRAME_HEIGHT - BORDER_BOTTOM + (count-1)*CHECKER_DEPTH;
                } else {
                    y = BORDER_TOP + (count-1)*CHECKER_DEPTH;
                }
                displayCheckerSide(player,x,y);
            }

        }
    }

//    public void refresh() {
//        revalidate();
//        repaint();
//    }

}
