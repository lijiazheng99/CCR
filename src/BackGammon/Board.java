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

    Board () {
        slots = new Slot[NUM_SLOTS];
        for (int pip=0; pip<NUM_SLOTS; pip++)
            slots[pip] = new Slot(Color.EMPTY, RESET[pip]);
        bearOff = new Slot[2];
        for(int i = 0; i < 2; i++)
            bearOff[i] = new Slot(null,0);

        slots[1].setCheckerColor(Color.RED);
        slots[12].setCheckerColor(Color.RED);
        slots[17].setCheckerColor(Color.RED);
        slots[19].setCheckerColor(Color.RED);
        slots[6].setCheckerColor(Color.WHITE);
        slots[8].setCheckerColor(Color.WHITE);
        slots[13].setCheckerColor(Color.WHITE);
        slots[24].setCheckerColor(Color.WHITE);

        playerOne = new Player(null,Color.RED);
        playerTwo = new Player(null, Color.WHITE);
    }

    public void move (Color color, int from, int to) {
        slots[from].moveOut(color);
        slots[to].moveIn(color);
    }

}