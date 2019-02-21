package BackGammon;

public class Board
{

    private static final int[] RESET = {0,2,0,0,0,0,5,0,3,0,0,0,5,5,0,0,0,3,0,5,0,0,0,0,2};
    private static final int INNER_END = 6;     // index for the end of the inner board
    public static final int NUM_SLOTS = 25;     // index 0 is not used -> means 24 slots on board
    private static final int NUM_CHECKERS = 15; // number of checkers in total
    public static final int BAR = 25;           // index of the BAR
    public static final int BEAR_OFF = 0;       // index of the BEAR OFF
    public static final int NUM_PIPS = 24;      // excluding BAR and BEAR OFF

    private Slot[] slots;
    private Slot[] bearOff;
    private Player playerOne;
    private Player playerTwo;

//    public Board ()
//    {
//        slots = new Slot[NUM_SLOTS];
//        for (int pip=0; pip<NUM_SLOTS; pip++)
//            slots[pip] = new Slot(CheckerColor.EMPTY, RESET[pip]);
//        bearOff = new Slot[2];
//        for(int i = 0; i < 2; i++)
//            bearOff[i] = new Slot(null,0);
//
//        slots[1].setCheckerColor(CheckerColor.RED);
//        slots[12].setCheckerColor(CheckerColor.RED);
//        slots[17].setCheckerColor(CheckerColor.RED);
//        slots[19].setCheckerColor(CheckerColor.RED);
//        slots[6].setCheckerColor(CheckerColor.WHITE);
//        slots[8].setCheckerColor(CheckerColor.WHITE);
//        slots[13].setCheckerColor(CheckerColor.WHITE);
//        slots[24].setCheckerColor(CheckerColor.WHITE);
//
//        playerOne = new Player(null, CheckerColor.RED);
//        playerTwo = new Player(null, CheckerColor.WHITE);
//    }
//
//    public void move (CheckerColor checkerColor, int from, int to)
//    {
//        slots[from].moveOut(checkerColor);
//        slots[to].moveIn(checkerColor);
//    }

    private int[][] checkers;
    // 2D array of checkers
    // 1st index is the player id
    // 2nd index is the pip number 0 to 25
    // pip 0 is bear off, pip 25 is the bar, pips 1-24 are on the main board
    // the value is the number of checkers on the point

    Board () {
        checkers = new int[BackGammon.NUM_PLAYERS][NUM_SLOTS];
        for (int player=0; player<BackGammon.NUM_PLAYERS; player++)  {
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
    }}