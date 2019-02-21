package BackGammonGUI;

import BackGammon.Checker_Color;
import BackGammon.Checker_vis;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.LinkedList;

public class ControlVisual
{
    GridPane grid = new GridPane();

    Label backGammon = new Label("BackGammon");
    Label player1 = new Label("Player Name Here");
    Label player2 = new Label("Player Name Here");
    TextField insertbox = new TextField();
    Button insertTextBox = new Button("       Return      ");
    TextArea outputTextBox = new TextArea();
    LinkedList output = new LinkedList();


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
        //Label backGammon = new Label("BackGammon");
        backGammon.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(backGammon,4);
        grid.add(backGammon, 15, 0);

        //Label player1 = new Label("Player Name Here");
        player1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player1,6);
        grid.add(player1, 1, 0);

        //Label player2 = new Label("Player Name Here");
        player2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player2,6);
        grid.add(player2, 8, 0);

        //TextField insertbox = new TextField();
        insertbox.setPromptText("Please insert...");
        grid.add(insertbox,0,35, 14, 1);

        //Button insertTextBox = new Button("       Return      ");
        grid.add(insertTextBox,14,35);

        //TextArea outputTextBox = new TextArea();
        outputTextBox.setEditable(false);
        grid.add(outputTextBox,15, 1,4,35);
        outputTextBox.setWrapText(true);
        return grid;
    }

    public void getInsert ()
    {
        insertTextBox.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if ((insertbox.getText() != null && !insertbox.getText().isEmpty()))
                {
                    if(insertbox.getText().equals("move"))
                    {
                        outputTextBox.appendText("Move");
                    }
                    else if (insertbox.getText().equals("clear"))
                    {
                        output.clear();
                        output.add("Welcome to BackGammon!\nGame instruction:\nType move to move;\nType clear to clear;\nType quit to exit;\n");
                        outputTextBox.setText(output.toString());
                    }
                    else if (insertbox.getText().equals("quit"))
                    {
                        System.exit(0);
                    }
                }
                else {
                    insertbox.setText("Nothing entered");
                }
            }
        });
    }

    public void output()
    {
        output.add("Welcome to BackGammon!\nGame instruction:\nType move to move;\nType clear to clear;\nType quit to exit;\n");
        outputTextBox.setText(output.toString());
        //System.out.println("this"+checkerCount);

    }

    public GridPane getControls ()
    {
        return this.grid;
    }


}
