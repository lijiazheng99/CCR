package BackGammon;

import BackGammonGUI.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

import static javafx.application.Application.launch;


public class BackGammon extends Application
{

    public static final int NUM_PLAYERS = 2;

    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 600;

    private final Board board = new Board();
    private final UI ui = new UI(board);



//    private void testUI() throws InterruptedException
//    {
//        // Moves a checker on to the bar and then moves it one pip at a time around the board.
//        // Does this for both players.
////        ui.display();
////        for (int player=0; player<NUM_PLAYERS; player++)
////        {
////            board.move(player,Board.NUM_PIPS,Board.BAR);
////            ui.display();
////            TimeUnit.SECONDS.sleep(1);
////            for (int pip=Board.BAR; pip>0; pip--) {
////                board.move(player, pip, pip-1);
////                ui.display();
////                TimeUnit.SECONDS.sleep(1);
////            }
////        }
////        String message;
//
//
//        do {
//            message = ui.getCommand();
//            ui.displayString(message);
//        } while (!message.equals("quit"));
//    }

    public void start(Stage primaryStage) throws Exception
    {
        Scene root = new Scene(ui.returnMainpane(),FRAME_WIDTH,FRAME_HEIGHT );
        primaryStage.setTitle("CCR BackGammon Game");
        primaryStage.setScene(root);
        primaryStage.setResizable(false);
        primaryStage.show();
        String message;
        do {
            message = ui.getCommand();
            ui.displayString(message);
        } while (!message.equals("quit"));
    }


    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
}
