package BackGammon;

import BackGammonGUI.BoardVisual;
import BackGammonGUI.ControlVisual;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public static final int PREFWIDTH = 1250;
    public static final int PREFHEIGHT = 856;

    public TextArea textField;

    double WIDTH = Screen.getPrimary().getBounds().getWidth() * 0.7;
    double HEIGHT = Screen.getPrimary().getBounds().getHeight() * 0.8;

    public int screenWidth = (int) WIDTH;
    public int screenHeight = (int) HEIGHT;

    Image background;

    public void start(Stage primaryStage) throws Exception
    {
        Scene root = new Scene(buildScene(),screenWidth, screenHeight);


        Scale scale = new Scale(1, 1, 0, 0);
        scale.xProperty().bind(root.widthProperty().divide(PREFWIDTH));
        scale.yProperty().bind(root.heightProperty().divide(PREFHEIGHT));

        Scene scene = new Scene(buildScene(),screenWidth,screenHeight);

        scene.getRoot().getTransforms().add(scale);

        primaryStage.setTitle("CCR BackGammon Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Parent buildScene()
    {
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

        return main ;
    }
    public static void main(String args[])
    {
        launch(args);
    }

}


