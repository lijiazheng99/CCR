package BackGammon;

public class Board {

    private static final int[] RESET = {0,2,0,0,0,0,5,0,3,0,0,0,5,5,0,0,0,3,0,5,0,0,0,0,2};
    private static final int INNER_END = 6;     // index for the end of the inner board
    public static final int NUM_SLOTS = 25;     // index 0 is not used -> means 24 slots on board
    private static final int NUM_CHECKERS = 15; // number of checkers in total

    private Slot[] slots;
    private Slot[] bearOff;
    private Player playerOne;
    private Player playerTwo;

    public Board ()
    {
        slots = new Slot[NUM_SLOTS];
        for (int pip=0; pip<NUM_SLOTS; pip++)
            slots[pip] = new Slot(CheckerColor.EMPTY, RESET[pip]);
        bearOff = new Slot[2];
        for(int i = 0; i < 2; i++)
            bearOff[i] = new Slot(null,0);

        slots[1].setCheckerColor(CheckerColor.RED);
        slots[12].setCheckerColor(CheckerColor.RED);
        slots[17].setCheckerColor(CheckerColor.RED);
        slots[19].setCheckerColor(CheckerColor.RED);
        slots[6].setCheckerColor(CheckerColor.WHITE);
        slots[8].setCheckerColor(CheckerColor.WHITE);
        slots[13].setCheckerColor(CheckerColor.WHITE);
        slots[24].setCheckerColor(CheckerColor.WHITE);

        playerOne = new Player(null, CheckerColor.RED);
        playerTwo = new Player(null, CheckerColor.WHITE);
    }

    public void move (CheckerColor checkerColor, int from, int to) {
        slots[from].moveOut(checkerColor);
        slots[to].moveIn(checkerColor);
    }

}