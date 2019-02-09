package BackGammon;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class BackGammon extends Application
{

    public static final int TILE_SIZE = 70;
    public static final int PREFWIDTH = 1250;
    public static final int PREFHEIGHT = 856;

    public TextArea textField;

    double WIDTH = Screen.getPrimary().getBounds().getWidth() * 0.7;
    double HEIGHT = Screen.getPrimary().getBounds().getHeight() * 0.8;

    int screenWidth = (int) WIDTH;
    int screenHeight = (int) HEIGHT;

    private Group backgroundpicture = new Group();

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
        //primaryStage.setFullScreen(true);
    }

    private Parent buildScene()
{
    GridPane root = new GridPane();
    GridPane grid = new GridPane();


    for (int i = 0; i < 19;i++)
    {
        if (i == 0)
            grid.getColumnConstraints().add(new ColumnConstraints(70));
        else if (i >= 1 && i<= 6)
            grid.getColumnConstraints().add(new ColumnConstraints(63));
        else if (i == 7)
            grid.getColumnConstraints().add(new ColumnConstraints(90));
        else if (i >= 8 && i<= 13)
            grid.getColumnConstraints().add(new ColumnConstraints(63));
        else if (i == 14)
            grid.getColumnConstraints().add(new ColumnConstraints(110));
        else
            grid.getColumnConstraints().add(new ColumnConstraints(55));
    }
    for (int i = 0; i < 36;i++)
    {
        if (i == 0)
            grid.getRowConstraints().add(new RowConstraints(55));
        else if (i >= 1 && i <= 33)
            grid.getRowConstraints().add(new RowConstraints(22));
        else
            grid.getRowConstraints().add(new RowConstraints(35));
    }



    //Insert background picture
    Image image = new Image("BoardPicture.jpeg");
    root.add(new ImageView(image), 0, 0);
    root.setPrefSize(screenWidth,screenHeight);

    //ONLY FOR DEVELOP USE
    //grid.setGridLinesVisible(true);

    Label backGammon = new Label("BackGammon");
    backGammon.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
    grid.setColumnSpan(backGammon,4);
    grid.add(backGammon, 15, 0);

    Label player1 = new Label("Player Name Here");
    player1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
    grid.setColumnSpan(player1,4);
    grid.add(player1, 1, 0);

    TextField insertbox = new TextField();
    insertbox.setPromptText("Please insert...");
    grid.add(insertbox,0,35, 14, 1);

    Button insertTextBox = new Button("       Return      ");
    grid.add(insertTextBox,14,35);

    TextArea outputTextBox = new TextArea();
    outputTextBox.setEditable(false);
    grid.add(outputTextBox,15, 1,4,35);

    System.out.println("OK1");



    Board board = new Board();
    System.out.println("OK2");
    board.setUp();
    System.out.println("OK4");

    int indexNum;
    int bottomNum;

    for (int i = 1; i < 25; i++)
    {
        indexNum = board.bars[i].getCheckerNumber();

        if (i <= 12)
        {
            bottomNum = 32;
            while(indexNum != 0)
            {
                if (i <= 6)
                {
                    grid.add(new Checker_vis(board.bars[i].getColor(),1,1),14-i,bottomNum);
                    bottomNum--;
                    indexNum--;
                }
                else if (i > 6)
                {
                    grid.add(new Checker_vis(board.bars[i].getColor(),1,1),13-i,bottomNum);
                    bottomNum--;
                    indexNum--;
                }
            }
        }

        else if (i > 12)
        {
            bottomNum = 1;
            while(bottomNum < indexNum + 1)
            {
                if (i <= 18)
                {
                    grid.add(new Checker_vis(board.bars[i].getColor(),1,1),i - 12,bottomNum);
                    bottomNum++;
                }
                else if (i > 18)
                {
                    grid.add(new Checker_vis(board.bars[i].getColor(),1,1),i - 11,bottomNum);
                    bottomNum++;
                }
            }
        }
    }

    String input = insertbox.getText();

    if (input.equals("exit"))
    {
        exitFun();
    }

    insertTextBox.setOnAction(action -> {

//        getTextSystem.out.println(outputTextBox.getText());
        outputTextBox.setText(input);

        outputTextBox.setText("Clicked!");
    });




    root.getChildren().addAll(grid);

    return root ;
}




    public static void main(String args[])
    {
        launch(args);
    }

    public void exitFun ()
    {
        System.exit(0);
    }

}


