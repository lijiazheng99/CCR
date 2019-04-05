package BackGammonGUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.FileInputStream;

public class DiceVisual extends GridPane
{
    //create gridpane and input images
    private final GridPane grid = new GridPane();
    private final Image[] images = new Image[6];

    //Images for four dices
    private ImageView image1 = new ImageView();
    private ImageView image2 = new ImageView();
    private ImageView image3 = new ImageView();
    private ImageView image4 = new ImageView();

    //Assign dice pane
    public GridPane DiceVisual()
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
        System.out.println("BackGammonGUI.DiceVisual.DiceVisual(): Dice gridpane assign successful");
        return this.grid;
    }

    //insert dice image in the program
    public void inPutDiceImages()
    {
        try
        {
            images[0] = new Image(new FileInputStream("src//dice_1.png"),55,55,false,false);
            images[1] = new Image(new FileInputStream("src//dice_2.png"),55,55,false,false);
            images[2] = new Image(new FileInputStream("src//dice_3.png"),55,55,false,false);
            images[3] = new Image(new FileInputStream("src//dice_4.png"),55,55,false,false);
            images[4] = new Image(new FileInputStream("src//dice_5.png"),55,55,false,false);
            images[5] = new Image(new FileInputStream("src//dice_6.png"),55,55,false,false);
        }
        catch (java.io.IOException ex)
        {
            System.out.println("Dice image failed");
        }
        System.out.println("BackGammonGUI.DiceVisual.DiceVisual(): Dice image insert successful");
    }

    //remove dice from the pane
    public void removeDisplay()
    {
        grid.getChildren().removeAll(image1,image2,image3,image4);
    }

    //dice display (during the game)
    public void diceDisplay(int first, int second)
    {
        removeDisplay();
        image1 = new ImageView(images[first-1]);
        image2 = new ImageView(images[second-1]);
        image3 = new ImageView(images[second-1]);
        image4 = new ImageView(images[second-1]);

        grid.add(image1,9,18);
        grid.add(image2,10,18);
        if (first == second)
        {
            grid.add(image3,11,18);
            grid.add(image4,12,18);
        }
    }

    //dice display (at game start)
    public void singleDisplay(int roll)
    {
        removeDisplay();
        image1 = new ImageView(images[roll-1]);
        grid.add(image1,9,18);
    }

    //return grid
    public GridPane getGrid()
    {
        return this.grid;
    }
}
