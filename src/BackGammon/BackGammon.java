package BackGammon;

import BackGammonGUI.BoardVisual;
import BackGammonGUI.ControlVisual;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class BackGammon extends Application
{
    public static final int TILE_SIZE = 70;
    //Set prefer size for width and height of screen
    public static final int PREFWIDTH = 1250;
    public static final int PREFHEIGHT = 856;

    //Get suitable size for the current displaying screen
    double WIDTH = Screen.getPrimary().getBounds().getWidth() * 0.7;
    double HEIGHT = Screen.getPrimary().getBounds().getHeight() * 0.8;
    public int screenWidth = (int) WIDTH;
    public int screenHeight = (int) HEIGHT;

    //Board image
    Image background;

    //JavaFX start class
    public void start(Stage primaryStage) throws Exception
    {
        //Root Scene set up
        Scene root = new Scene(buildScene(),screenWidth, screenHeight);

        // scale for automatically suit for current screen
        Scale scale = new Scale(1, 1, 0, 0);
        scale.xProperty().bind(root.widthProperty().divide(PREFWIDTH));
        scale.yProperty().bind(root.heightProperty().divide(PREFHEIGHT));

        //Rebuild root scene
        Scene scene = new Scene(buildScene(),screenWidth,screenHeight);

        //Scale root scene
        scene.getRoot().getTransforms().add(scale);

        //Set stage title, set scene, set
        primaryStage.setTitle("CCR BackGammon Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Parent class for build root scene
    private Parent buildScene()
    {
        //Create root pane for insert background image
        GridPane root = new GridPane();
        try
        {
            background = new Image(new FileInputStream("src//BoardPicture.jpeg"));
        }
        catch(java.io.IOException ex)
        {
            System.out.println("Load image failed");
        }
        root.add(new ImageView(background), 0, 0);
        root.setPrefSize(screenWidth,screenHeight);

        Board board = new Board();
        board.setUp();

        BoardVisual boardVisual = new BoardVisual();
        boardVisual.BoardVisual();

        ControlVisual controlVisual = new ControlVisual();
        controlVisual.ControlVisual();
        controlVisual.initControlVisual(controlVisual.ControlVisual());
        controlVisual.getInsert();
        controlVisual.instructMessage();
        controlVisual.gameStart();

        Pane main = new Pane();
        main.getChildren().add(root);
        main.getChildren().add(boardVisual.BoardVisual(board));
        main.getChildren().add(controlVisual.getControls());



        //Set Player Name:
//        controlVisual.outputTextBox.appendText("Please enter the name for Player One: ");
//        board.playerOne.setName(controlVisual.getTypeIn());
//        controlVisual.outputTextBox.appendText("Please enter the name for Player Two: ");
//        board.playerTwo.setName(controlVisual.getTypeIn());


        //Color Decide:
//        do {
//            board.points1 = board.diceToRoll.roll();
//            controlVisual.outputTextBox.appendText("The point for P1: " + board.points1);
//            board.points2 = board.diceToRoll.roll();
//            controlVisual.outputTextBox.appendText("The point for P2: " + board.points2);
//        }while(board.points1 == board.points2);
//
//        if(board.points1 > board.points2)
//        {
//            board.playerOne.setColor(Checker_Color.RED);
//            board.playerTwo.setColor(Checker_Color.WHITE);
//            controlVisual.outputTextBox.appendText(board.playerOne.getName() + ": " + "RED");
//            controlVisual.outputTextBox.appendText(board.playerTwo.getName() + ": " + "WHITE");
//        }else
//        {
//            board.playerOne.setColor(Checker_Color.WHITE);
//            board.playerTwo.setColor(Checker_Color.RED);
//            controlVisual.outputTextBox.appendText(board.playerOne.getName() + ": " + "WHITE");
//            controlVisual.outputTextBox.appendText(board.playerTwo.getName() + ": " + "RED");
//        }
        //move function: (please change the 0 and 1 into others)
//        board.move(0,1);

        return main ;
    }
    public static void main(String args[])
    {
        launch(args);
    }

}


