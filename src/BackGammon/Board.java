package BackGammon;

public class Board {

    private static final int[] RESET = {0,0,0,0,0,0,5,0,3,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,2,0};
    public static final int BAR = 25;           // index of the BAR
    public static final int BEAR_OFF = 0;       // index of the BEAR OFF
    private static final int INNER_END = 6;     // index for the end of the inner board
    public static final int NUM_PIPS = 24;      // excluding BAR and BEAR OFF
    public static final int NUM_SLOTS = 26;     // including BAR and BEAR OFF
    private static final int NUM_CHECKERS = 15;


    private int[][] checkers;
            // 2D array of checkers
            // 1st index is the player id
            // 2nd index is the pip number 0 to 25
            // pip 0 is bear off, pip 25 is the bar, pips 1-24 are on the main board
            // the value is the number of checkers on the point

    Board () {
        checkers = new int[Backgammon.NUM_PLAYERS][NUM_SLOTS];
        for (int player=0; player<Backgammon.NUM_PLAYERS; player++)  {
            for (int pip=0; pip<NUM_SLOTS; pip++)   {
                checkers[player][pip] = RESET[pip];
            }
        }
    }

    public void move (int player, int from, int to) {
        checkers[player][from]--;
        checkers[player][to]++;
    }

    public int getNumCheckers (int player, int pip) {
        return checkers[player][pip];
    }


}