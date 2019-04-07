package BackGammonGUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import java.io.FileInputStream;

public class DoublingCubeVisual extends GridPane {



    private final GridPane grid = new GridPane();
    private final Image[] images = new Image[6];


    private ImageView doublingDiceImg = new ImageView();


//    public GridPane DoublingCubeVisual() {
//
//        for (int i = 0; i < 19; i++) {
//            if (i == 0)
//                grid.getColumnConstraints().add(new ColumnConstraints(70));
//            else if (i >= 1 && i <= 6)
//                grid.getColumnConstraints().add(new ColumnConstraints(63));
//            else if (i == 7)
//                grid.getColumnConstraints().add(new ColumnConstraints(90));
//            else if (i >= 8 && i <= 13)
//                grid.getColumnConstraints().add(new ColumnConstraints(63));
//            else if (i == 14)
//                grid.getColumnConstraints().add(new ColumnConstraints(110));
//            else
//                grid.getColumnConstraints().add(new ColumnConstraints(55));
//        }
//        for (int i = 0; i < 36; i++) {
//            if (i == 0)
//                grid.getRowConstraints().add(new RowConstraints(55));
//            else if (i >= 1 && i <= 33)
//                grid.getRowConstraints().add(new RowConstraints(22));
//            else
//                grid.getRowConstraints().add(new RowConstraints(35));
//        }
//        System.out.println("BackGammonGUI.DoublingDiceVisual.DiceVisual(): DoublingDice gridpane assign successful");
//        return this.grid;
//
//
//    }


    public void inputDoublingDiceImages() {
        try {
            images[0] = new Image(new FileInputStream("src//dice_1.png"), 55, 55, false, false) ;
//            images[1] = new Image(new FileInputStream("source"),55,55,false,false);
//            images[2] = new Image(new FileInputStream("source"),55,55,false,false);
//            images[3] = new Image(new FileInputStream("source"),55,55,false,false);
//            images[4] = new Image(new FileInputStream("source"),55,55,false,false);
//            images[5] = new Image(new FileInputStream("source"),55,55,false,false);
        } catch (java.io.IOException ex) {
            System.out.println("DoublingDice image Failed");
        }
        System.out.println("BackGammonGUI.DoublingDiceVisual.inputDoublingDiceImages(): DoublingDice image input successful.");

    }


    public void removeDisplay() {
        grid.getChildren().removeAll(doublingDiceImg);
    }

    public void cubeDisplay() {

        removeDisplay();

        doublingDiceImg = new ImageView(images[0]);

        grid.add(doublingDiceImg, 10, 10);

    }

    public GridPane getGrid() {
        return this.grid;
    }


}


