package BackGammonGUI;
import javafx.scene.image.Image;

public class DiceVisual {


    final Image dice1 = new Image(getClass().getResourceAsStream("dice_1.png"));
    final Image dice2 = new Image(getClass().getResourceAsStream("dice_2.png"));
    final Image dice3 = new Image(getClass().getResourceAsStream("dice_3.png"));
    final Image dice4 = new Image(getClass().getResourceAsStream("dice_4.png"));
    final Image dice5 = new Image(getClass().getResourceAsStream("dice_5.png"));
    final Image dice6 = new Image(getClass().getResourceAsStream("dice_6.png"));

    final Image[] images = new Image[6];


    public DiceVisual(){

        images[0] = dice1;
        images[1] = dice2;
        images[2] = dice3;
        images[3] = dice4;
        images[4] = dice5;
        images[5] = dice6;
    }

    public Image[] getImages(){

        return images;
    }

}
