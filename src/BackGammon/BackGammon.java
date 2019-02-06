package BackGammon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class BackGammon extends Application
{

    public static final int TILE_SIZE = 70;
    public static final int PREFWIDTH = 1250;
    public static final int PREFHEIGHT = 860;

    double WIDTH = Screen.getPrimary().getBounds().getWidth() * 0.7;
    double HEIGHT = Screen.getPrimary().getBounds().getHeight() * 0.7;

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
        primaryStage.setResizable(true);
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
        for (int i = 0; i < 35;i++)
        {
            if (i == 0)
                grid.getRowConstraints().add(new RowConstraints(60));
            else if (i >= 1 && i <= 33)
                grid.getRowConstraints().add(new RowConstraints(22));
            else
                grid.getRowConstraints().add(new RowConstraints(70));
        }



        //Insert background picture
        Image image = new Image("BoardPicture.jpeg");
        root.add(new ImageView(image), 0, 0);
        root.setPrefSize(1246,856);

        //ONLY FOR DEVELOP USE
        //grid.setGridLinesVisible(true);



//        Button button = new Button();
//        grid.add(new Button(), 2, 0);





        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),1,1);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),1,2);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),1,3);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),1,4);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),1,5);


        grid.add(new Checker_vis(Checker_Color.RED,1,1),5,1);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),5,2);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),5,3);


        grid.add(new Checker_vis(Checker_Color.RED,1,1),8,1);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),8,2);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),8,3);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),8,4);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),8,5);


        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),13,1);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),13,2);






        grid.add(new Checker_vis(Checker_Color.RED,1,1),1,32);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),1,31);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),1,30);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),1,29);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),1,28);


        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),5,32);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),5,31);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),5,30);


        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),8,32);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),8,31);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),8,30);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),8,29);
        grid.add(new Checker_vis(Checker_Color.WHITE,1,1),8,28);


        grid.add(new Checker_vis(Checker_Color.RED,1,1),13,32);
        grid.add(new Checker_vis(Checker_Color.RED,1,1),13,31);











        root.getChildren().addAll(grid);

        return root ;
    }





    public static void main(String args[])
    {
        launch(args);
    }

}


