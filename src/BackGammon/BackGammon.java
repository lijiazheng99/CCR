package BackGammon;

import BackGammonGUI.GameController;
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


    public static final int NUM_PLAYERS = 2;
    private final Players players = new Players();
    private final Board board = new Board(players);


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
        GridPane rootPane =  new GridPane();
        //Create root pane for insert background image
        GridPane backgroundPic = new GridPane();
        try
        {
            background = new Image(new FileInputStream("src//board.jpg"));
            System.out.println("BackGammon.BackGammon: Background image load Successful.");
        }
        catch(java.io.IOException e)
        {
            System.out.println("BackGammon.BackGammon: Load image failed.");
        }

        backgroundPic.add(new ImageView(background), 0, 0);
        backgroundPic.setPrefSize(screenWidth,screenHeight);

        GameController gameController = new GameController();

        gameController.GameController();
        gameController.gameStart();
        gameController.getInsert();

        rootPane.getChildren().add(backgroundPic);
        rootPane.getChildren().add(gameController.getPane());
        return rootPane ;
    }
    public static void main(String args[])
    {
        launch(args);
    }
}


