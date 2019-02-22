package BackGammonGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ControlVisual
{
    GridPane grid = new GridPane();

    Label backGammon = new Label("BackGammon");
    Label player1 = new Label("Player Name Here");
    Label player2 = new Label("Player Name Here");
    TextField insertbox = new TextField();
    Button insertTextBox = new Button("       Return      ");
    TextArea outputTextBox = new TextArea();
    String messegeBuffer;

    public GridPane ControlVisual()
    {
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
        return this.grid;
    }

    public GridPane initControlVisual(GridPane grid)
    {
        //Game Title
        backGammon.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(backGammon,4);
        grid.add(backGammon, 15, 0);

        //Player 1 Label
        player1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player1,6);
        grid.add(player1, 1, 0);

        //Player 2 Label
        player2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player2,6);
        grid.add(player2, 8, 0);

        //Insert box
        insertbox.setPromptText("Please insert...");
        grid.add(insertbox,0,35, 14, 1);

        //Insert button
        grid.add(insertTextBox,14,35);

        //Output Area
        outputTextBox.setEditable(false);
        outputTextBox.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, 15));
        grid.add(outputTextBox,15, 1,4,35);
        outputTextBox.setWrapText(true);
        return grid;
    }

    public void getInsert()
    {
        insertTextBox.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                judgeInsert();
            }
        });

        insertbox.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    judgeInsert();
                }
            }
        });
    }

    public void judgeInsert ()
    {
        messegeBuffer =  new String(insertbox.getText());
        messegeBuffer = messegeBuffer.toUpperCase();
        System.out.println("Reached");
        if ((insertbox.getText() != null && !insertbox.getText().isEmpty()))
        {
            if(messegeBuffer.substring(0,4).equals("MOVE"))
            {
                outputTextBox.appendText("Move\n");
                insertbox.clear();
                outputTextBox.appendText("Please insert start pip:\n");
            }
            else if (messegeBuffer.substring(0,4).equals("QUIT") ||messegeBuffer.substring(0,4).equals("EXIT")  )
            {
                System.exit(0);
            }
            else if (messegeBuffer.substring(0,5).equals("CLEAR") )
            {
                outputTextBox.clear();
                insertbox.clear();
                instructMessage();
            }
            else
                outputTextBox.appendText(messegeBuffer);
        }
        else
            outputTextBox.appendText("Nothing Entered\n");
    }

    public String getTypeIn()
    {
        return this.messegeBuffer;
    }

    public void gameStart()
    {
        outputTextBox.appendText("Game Start!\n");
    }

    public void instructMessage()
    {
        outputTextBox.appendText("Welcome to BackGammon!\nGame instruction:\nType move to move;\nType clear to clear board messages;\nType quit to exit;\n");
        outputTextBox.appendText("Type Move<pip1><pip2>, move one disk from pip1 to pip2\n");
    }

    public GridPane getControls ()
    {
        return this.grid;
    }
}
