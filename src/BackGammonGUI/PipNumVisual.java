package BackGammonGUI;

import BackGammon.Checker_Color;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PipNumVisual
{
    GridPane grid = new GridPane();
    private Label[] piplabels = new Label[24];

    public GridPane PipNumVisual ()
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
        System.out.println("BackGammonGUI.PipNumVisual.PipNumVisuall(): Gridpane assign successful");
        return this.grid;
    }

    private void assignPipWHITE()
    {
        piplabels[0] =  new Label("   12");
        piplabels[0].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[1] =  new Label("   11");
        piplabels[1].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[2] =  new Label("   10");
        piplabels[2].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[3] =  new Label("    9");
        piplabels[3].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[4] =  new Label("    8");
        piplabels[4].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[5] =  new Label("    7");
        piplabels[5].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[6] =  new Label("    6");
        piplabels[6].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[7] =  new Label("    5");
        piplabels[7].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[8] =  new Label("    4");
        piplabels[8].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[9] =  new Label("    3");
        piplabels[9].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[10] =  new Label("    2");
        piplabels[10].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[11] =  new Label("    1");
        piplabels[11].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[12] =  new Label("   13");
        piplabels[12].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[13] =  new Label("   14");
        piplabels[13].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[14] =  new Label("   15");
        piplabels[14].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[15] =  new Label("   16");
        piplabels[15].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[16] =  new Label("   17");
        piplabels[16].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[17] =  new Label("   18");
        piplabels[17].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[18] =  new Label("   19");
        piplabels[18].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[19] =  new Label("   20");
        piplabels[19].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[20] =  new Label("   21");
        piplabels[20].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[21] =  new Label("   22");
        piplabels[21].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[22] =  new Label("   23");
        piplabels[22].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[23] =  new Label("   24");
        piplabels[23].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
    }

    private void assignPipRED()
    {
        piplabels[0] =  new Label("   13");
        piplabels[0].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[1] =  new Label("   14");
        piplabels[1].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[2] =  new Label("   15");
        piplabels[2].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[3] =  new Label("   16");
        piplabels[3].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[4] =  new Label("   17");
        piplabels[4].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[5] =  new Label("   18");
        piplabels[5].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[6] =  new Label("   19");
        piplabels[6].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[7] =  new Label("   20");
        piplabels[7].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[8] =  new Label("   21");
        piplabels[8].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[9] =  new Label("   22");
        piplabels[9].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[10] =  new Label("   23");
        piplabels[10].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[11] =  new Label("   24");
        piplabels[11].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[12] =  new Label("   12");
        piplabels[12].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[13] =  new Label("   11");
        piplabels[13].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[14] =  new Label("   10");
        piplabels[14].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[15] =  new Label("    9");
        piplabels[15].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[16] =  new Label("    8");
        piplabels[16].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[17] =  new Label("    7");
        piplabels[17].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[18] =  new Label("    6");
        piplabels[18].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[19] =  new Label("    5");
        piplabels[19].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[20] =  new Label("    4");
        piplabels[20].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[21] =  new Label("    3");
        piplabels[21].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[22] =  new Label("    2");
        piplabels[22].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        piplabels[23] =  new Label("    1");
        piplabels[23].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
    }

    private void removeLabels()
    {
        for(int i=0;i<24;i++)
            grid.getChildren().removeAll(piplabels[i]);
    }

    public void assignLabel(Checker_Color color)
    {
        removeLabels();

        if (color == Checker_Color.WHITE)
            assignPipWHITE();
        else if (color == Checker_Color.RED)
            assignPipRED();

        grid.add(piplabels[0],1,14);
        grid.add(piplabels[1],2,14);
        grid.add(piplabels[2],3,14);
        grid.add(piplabels[3],4,14);
        grid.add(piplabels[4],5,14);
        grid.add(piplabels[5],6,14);
        grid.add(piplabels[6],8,14);
        grid.add(piplabels[7],9,14);
        grid.add(piplabels[8],10,14);
        grid.add(piplabels[9],11,14);
        grid.add(piplabels[10],12,14);
        grid.add(piplabels[11],13,14);
        grid.add(piplabels[12],1,21);
        grid.add(piplabels[13],2,21);
        grid.add(piplabels[14],3,21);
        grid.add(piplabels[15],4,21);
        grid.add(piplabels[16],5,21);
        grid.add(piplabels[17],6,21);
        grid.add(piplabels[18],8,21);
        grid.add(piplabels[19],9,21);
        grid.add(piplabels[20],10,21);
        grid.add(piplabels[21],11,21);
        grid.add(piplabels[22],12,21);
        grid.add(piplabels[23],13,21);
    }

    public GridPane returnPane()
    {
        return grid;
    }
}
