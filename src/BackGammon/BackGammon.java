package BackGammon;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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


    //Insert Background picture
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
    grid.setColumnSpan(player1,6);
    grid.add(player1, 1, 0);

    Label player2 = new Label("Player Name Here");
    player2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
    grid.setColumnSpan(player2,6);
    grid.add(player2, 8, 0);

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
    Checker_vis[] checkers = new Checker_vis[30];
    int checkerCount = 0;

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
                    checkers[checkerCount] = new Checker_vis(board.bars[i].getColor(),14 - i,bottomNum);
                    grid.add(checkers[checkerCount],14-i,bottomNum);
                    if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                {
                    checkerCount++;
                }
                    bottomNum--;
                    indexNum--;
                }
                else if (i > 6)
                {
                    checkers[checkerCount] = new Checker_vis(board.bars[i].getColor(),13-i,bottomNum);
                    grid.add(checkers[checkerCount],13-i,bottomNum);
                    if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                    {
                        checkerCount++;
                    }
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
                    checkers[checkerCount] = new Checker_vis(board.bars[i].getColor(),i - 11,bottomNum);
                    grid.add(checkers[checkerCount],i - 12,bottomNum);
                    if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                    {
                        checkerCount++;
                    }
                    bottomNum++;
                }
                else if (i > 18)
                {
                    checkers[checkerCount] = new Checker_vis(board.bars[i].getColor(),i - 12,bottomNum);
                    grid.add(checkers[checkerCount],i - 11,bottomNum);
                    if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                    {
                        checkerCount++;
                    }
                    bottomNum++;
                }
            }
        }
    }

    for (int i = 1; i <= 24; i++)
    {
        Label colNum = new Label(""+i);
        //colNum.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 1));
        if (i <= 12)
        {
            bottomNum = 32;
            if (i <= 6)
            {
                grid.add(colNum,14-i,18);
            }
            else if (i > 6)
            {
                grid.add(colNum,13-i,18);
            }

        }

        else if (i > 12)
        {
            if (i <= 18)
            {
                grid.add(colNum,i-12,17);
            }
            else if (i > 18)
            {
                grid.add(colNum,i-11,17);
            }
        }
    }

    //output game instructions
    LinkedList output = new LinkedList();
    output.add("Welcome to BackGammon!\nGame instruction:\nType move to move;\nType clear to clear;\nType quit to exit;\n");
    outputTextBox.setText(output.toString());
    System.out.println("this"+checkerCount);


    insertTextBox.setOnAction(new EventHandler<ActionEvent>()
    {
        int row = 12;
        int col = 32;
        int counter = 2;

        @Override
        public void handle(ActionEvent e)
        {
            if ((insertbox.getText() != null && !insertbox.getText().isEmpty()))
            {
                if(insertbox.getText().equals("move"))
                {
                    output.add("Moving test now\n");
                    outputTextBox.setText(output.toString());

                    grid.getChildren().remove(checkers[1]);
                    board.bars[1].setCheckerNumber(1);
                    board.bars[counter].setCheckerNumber(1);
                    board.bars[counter].setCheckerColor(Checker_Color.RED);

                    checkers[1] = new Checker_vis(board.bars[1].getColor(),11,32);
                    if (row > 8)
                        grid.add(checkers[1], row--, 32);
                    else if (row == 8 && row == 7)
                    {
                        row = 6;
                        grid.add(checkers[1], row--, 32);
                    }
                    //grid.add(checkers[1], row--, 32);
                }
                else if (insertbox.getText().equals("clear"))
                {
                    output.clear();
                    output.add("Welcome to BackGammon!\nGame instruction:\nType move to move;\nType clear to clear;\nType quit to exit;\n");
                    outputTextBox.setText(output.toString());
                }
                else if (insertbox.getText().equals("quit"))
                {
                    exitFun();
                }
            }
            else {
                insertbox.setText("Nothing entered");
            }
        }
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


