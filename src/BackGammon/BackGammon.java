package BackGammon;

import java.util.concurrent.TimeUnit;



public class BackGammon {

    public static final int NUM_PLAYERS = 2;

    private final Board board = new Board();
    private final UI ui = new UI(board);

    private void testUI() throws InterruptedException {
        // Moves a checker on to the bar and then moves it one pip at a time around the board.
        // Does this for both players.
        ui.display();
        for (int player=0; player<NUM_PLAYERS; player++) {
            board.move(player,Board.NUM_PIPS,Board.BAR);
            ui.display();
            TimeUnit.SECONDS.sleep(1);
            for (int pip=Board.BAR; pip>0; pip--) {
                board.move(player, pip, pip-1);
                ui.display();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        String message;
        do {
            message = ui.getCommand();
            ui.displayString(message);
        } while (!message.equals("quit"));
    }

    public static void main (String[] args) throws InterruptedException {
        BackGammon game = new BackGammon();
        game.testUI();
        System.exit(0);
    }
}
