package BackGammonGUI;

import BackGammon.Checker_Color;
import BackGammon.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import java.io.FileInputStream;

public class DoublingCubeVisual extends GridPane
{
    private final GridPane grid = new GridPane();
    private final Image[] images = new Image[6];

    private ImageView doublingDiceImg = new ImageView();

    public GridPane DoublingCubeVisual()
    {

        for (int i = 0; i < 19; i++) {
            if (i == 0)
                grid.getColumnConstraints().add(new ColumnConstraints(70));
            else if (i >= 1 && i <= 6)
                grid.getColumnConstraints().add(new ColumnConstraints(63));
            else if (i == 7)
                grid.getColumnConstraints().add(new ColumnConstraints(90));
            else if (i >= 8 && i <= 13)
                grid.getColumnConstraints().add(new ColumnConstraints(63));
            else if (i == 14)
                grid.getColumnConstraints().add(new ColumnConstraints(110));
            else
                grid.getColumnConstraints().add(new ColumnConstraints(55));
        }
        for (int i = 0; i < 36; i++) {
            if (i == 0)
                grid.getRowConstraints().add(new RowConstraints(55));
            else if (i >= 1 && i <= 33)
                grid.getRowConstraints().add(new RowConstraints(22));
            else
                grid.getRowConstraints().add(new RowConstraints(35));
        }
        System.out.println("BackGammonGUI.DoublingDiceVisual.DiceVisual(): DoublingDice gridpane assign Successful.");
        return this.grid;
    }


    public void inputDoublingDiceImages()
    {
        try
        {
            images[0] = new Image(new FileInputStream("src//doublecube_2.png"), 55, 55, false, false) ;
            images[1] = new Image(new FileInputStream("src//doublecube_4.png"),55,55,false,false);
            images[2] = new Image(new FileInputStream("src//doublecube_8.png"),55,55,false,false);
            images[3] = new Image(new FileInputStream("src//doublecube_16.png"),55,55,false,false);
            images[4] = new Image(new FileInputStream("src//doublecube_32.png"),55,55,false,false);
            images[5] = new Image(new FileInputStream("src//doublecube_64.png"),55,55,false,false);
        }
        catch (java.io.IOException e)
        {
            System.out.println("BackGammonGUI.DoublingDiceVisual.inputDoublingDiceImages(): DoublingDice image input Failed.");
        }
        System.out.println("BackGammonGUI.DoublingDiceVisual.inputDoublingDiceImages(): DoublingDice image input Successful.");

    }

    public void removeDisplay()
    {
        grid.getChildren().removeAll(doublingDiceImg);
    }

    public void cubeDisplay(Player player, int num)
    {
        removeDisplay();
        chooseImage(num);
        if (player == null)
        {
            doublingDiceImg = new ImageView(images[5]);
            grid.add(doublingDiceImg,0,17);
        }
        else if (player.getColor() == Checker_Color.RED)
        {
            grid.add(doublingDiceImg,0,0);

        }
        else if (player.getColor() == Checker_Color.WHITE)
        {
            grid.add(doublingDiceImg,7,0);
        }
    }

    private void chooseImage(int num)
    {
        switch (num)
        {
            case 2:
                doublingDiceImg = new ImageView(images[0]);
                break;
            case 4:
                doublingDiceImg = new ImageView(images[1]);
                break;
            case 8:
                doublingDiceImg = new ImageView(images[2]);
                break;
            case 16:
                doublingDiceImg = new ImageView(images[3]);
                break;
            case 32:
                doublingDiceImg = new ImageView(images[4]);
                break;
            case 64:
                doublingDiceImg = new ImageView(images[5]);
                break;
        }
    }

    public GridPane getGrid()
    {
        return this.grid;
    }
}


