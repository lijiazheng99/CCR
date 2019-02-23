package BackGammonGUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Dice {

    public ImageView diceFace;
    public Image images[];
    public int diceFaceValue;


    public Dice(Image[] images){

        this.images = images;
        diceFace = new ImageView(this.images[0]);
    }

    public Dice(Image[] images, int diceFaceValue){

        this.images = images;

        diceFace = new ImageView(this.images[diceFaceValue-1]); // array is from 0 to 5

    }


    public ImageView getDiceFace(){

        return diceFace;
    }

    public void setDiceFace(int diceFaceValue){

        diceFace.setImage(this.images[diceFaceValue-1]); // array is from 0 to 5

    }

}
