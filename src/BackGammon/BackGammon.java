package BackGammon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BackGammon extends Application
{

    public void start(Stage stage) throws FileNotFoundException
    {
        //Creating an image
        Image backGround = new Image("BoardPicture.jpeg");

        //Setting the image view
        ImageView imageView = new ImageView(backGround);

        //Setting the position of the image
        imageView.setX(100);
        imageView.setY(50);

        //setting the fit height and width of the image view
        imageView.setFitHeight(910);
        imageView.setFitWidth(1000);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        //Creating a scene object
        Scene scene = new Scene(root, 1200, 1000);

        //Setting title to the Stage
        stage.setTitle("BackGammon Game");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[])
    {
        launch(args);
    }

}


