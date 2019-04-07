package BackGammon;

public class Board {
    // Board hold the details for the current board positions, performs moves and returns the list of legal moves

    private static final int[] RESET = {0,0,0,0,0,0,5,0,3,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,2,0};
    private static final int[][] CHEAT = {
            {2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},   // Bear in & Bear off test
            {3,3,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
    };



    public static final int BAR = 25;           // index of the BAR
    public static final int BEAR_OFF = 0;       // index of the BEAR OFF
    private static final int INNER_END = 6;     // index for the end of the inner board
    public static final int NUM_PIPS = 24;      // excluding BAR and BEAR OFF
    public static final int NUM_SLOTS = 26;     // including BAR and BEAR OFF
    private static final int NUM_CHECKERS = 15;

    public Bar[] bars = new Bar[25];

    private int[][] checkers;

    public int currentMatchScore;
    public int currentRound;

    private Players players;
    // 2D array of checkers
    // 1st index: is the player id
    // 2nd index is number pip number, 0 to 25
    // pip 0 is bear off, pip 25 is the bar, pips 1-24 are on the main board
    // the value in checkers is the number of checkers that the player has on the point

    public Board(Players players) {
        this.players = players;
        checkers = new int[BackGammon.NUM_PLAYERS][NUM_SLOTS];
        for (int player=0; player<BackGammon.NUM_PLAYERS; player++)  {
            for (int pip=0; pip<NUM_SLOTS; pip++)   {
                checkers[player][pip] = RESET[pip];
            }
        }
        for(int i = 1; i <= 24; i++) {
            bars[i] = new Bar();
        }
        currentMatchScore = 0;
        currentRound = 0;

        barsBuild();
    }

    public Board(Players players, Board board) {
        this.players = players;
        this.checkers = new int[BackGammon.NUM_PLAYERS][NUM_SLOTS];
        for (int player=0; player<BackGammon.NUM_PLAYERS; player++)  {
            for (int pip=0; pip<NUM_SLOTS; pip++)   {
                this.checkers[player][pip] = board.checkers[player][pip];
            }
        }
        for(int i = 1; i <= 24; i++) {
            bars[i] = new Bar();
        }
        this.currentMatchScore = board.currentMatchScore;
        this.currentRound = board.currentRound;
        barsBuild();
    }

    private int getOpposingId(Player player) {
        if (player.getId()==0) {
            return 1;
        } else {
            return 0;
        }
    }

    private int calculateOpposingPip(int pip) {
        return NUM_PIPS-pip+1;
    }

    private void move(Player player, Move move) {
        checkers[player.getId()][move.getFromPip()]--;
        checkers[player.getId()][move.getToPip()]++;
        if (move.getToPip()<BAR && move.getToPip()>BEAR_OFF &&
                checkers[getOpposingId(player)][calculateOpposingPip(move.getToPip())] == 1) {
            checkers[getOpposingId(player)][calculateOpposingPip(move.getToPip())]--;
            checkers[getOpposingId(player)][BAR]++;
        }
        barsBuild();
    }

    public void move(Player player, Play play) {
        for (Move move : play) {
            move(player,move);
        }
    }

    public int getNumCheckers(int player, int pip) {
        return checkers[player][pip];
    }

    private boolean bearOffIsLegal(Player player) {
        int numberCheckersInInnerBoard=0;
        for (int pip=BEAR_OFF; pip<=INNER_END; pip++) {
            numberCheckersInInnerBoard = numberCheckersInInnerBoard + checkers[player.getId()][pip];
        }
        if (numberCheckersInInnerBoard==NUM_CHECKERS) {
            return true;
        } else {
            return false;
        }
    }

    private int lastCheckerPip(Player player) {
        int pip;
        for (pip=BAR; pip>=BEAR_OFF; pip--) {
            if (checkers[player.getId()][pip]>0) {
                break;
            }
        }
        return pip;
    }

    private Plays findAllPlays(Board board, Player player, Movements movements) {
        // Search recursively for the plays that are possible with a given sequence of movements
        Plays plays = new Plays();
        int fromPipLimit;
        // must take checkers from the bar first
        if (board.checkers[player.getId()][BAR] > 0) {
            fromPipLimit = BAR-1;
        } else {
            fromPipLimit = BEAR_OFF-1;
        }
        // search over the board for valid moves
        for (int fromPip=BAR; fromPip>fromPipLimit; fromPip--) {
            if (board.checkers[player.getId()][fromPip]>0) {
                int toPip = fromPip-movements.getFirst();
                Move newMove = new Move();
                Boolean isNewMove = false;
                if (toPip>BEAR_OFF) {
                    // check for valid moves with and without a hit
                    if (board.checkers[getOpposingId(player)][calculateOpposingPip(toPip)]==0) {
                        newMove = new Move(fromPip,toPip,false);
                        isNewMove = true;
                    } else if (board.checkers[getOpposingId(player)][calculateOpposingPip(toPip)]==1) {
                        newMove = new Move(fromPip,toPip,true);
                        isNewMove = true;
                    }
                } else {
                    // check for valid bear off
                    if (board.bearOffIsLegal(player) && (toPip==0 || (toPip<0 && board.lastCheckerPip(player)==fromPip))) {
                        newMove = new Move(fromPip,BEAR_OFF, false);
                        isNewMove = true;
                    }
                }
                // apply the move to the board and search for a follow on move
                if (isNewMove) {
                    if (movements.number()>1) {
                        Board childBoard = new Board(players,board);
                        childBoard.move(player,newMove);
                        Movements childMovements = new Movements(movements);
                        childMovements.removeFirst();
                        Plays childPlays = findAllPlays(childBoard, player, childMovements);
                        if (childPlays.number()>0) {
                            childPlays.prependAll(newMove);
                            plays.add(childPlays);
                        } else {
                            plays.add(new Play(newMove));
                        }
                    } else {
                        plays.add(new Play(newMove));
                    }
                }
            }
        }
        return plays;
    }


    public Plays getPossiblePlays(Player player, Dice dice) {
        // Search for the plays that are possible with all of the movements that can be made based on the dice
        Plays possiblePlays;
        Movements movements = new Movements(dice);
        if (player.getDice().isDouble()) {
            possiblePlays = findAllPlays(this,player,movements);
        } else {
            possiblePlays = findAllPlays(this,player,movements);
            movements.reverse();
            possiblePlays.add(findAllPlays(this,player,movements));
        }
        possiblePlays.removeIncompletePlays();
        possiblePlays.removeDuplicatePlays();
        return possiblePlays;
    }

    public boolean isGameOver() {
        boolean gameOver = false;
        if ( (checkers[0][BEAR_OFF] == NUM_CHECKERS) || (checkers[1][BEAR_OFF] == NUM_CHECKERS) ) {
            gameOver = true;
        }
        return gameOver;
    }

    public Player getWinner() {
        Player winner = players.get(0);
        if (checkers[0][BEAR_OFF] == NUM_CHECKERS) {
            winner = players.get(0);
        } else if (checkers[1][BEAR_OFF] == NUM_CHECKERS) {
            winner = players.get(1);
        }
        return winner;
    }

    public void cheat() {
        for (int player=0; player<BackGammon.NUM_PLAYERS; player++)  {
            for (int pip=0; pip<NUM_SLOTS; pip++)   {
                checkers[player][pip] = CHEAT[player][pip];
            }
        }
        barsBuild();
    }

    private void barsBuild()
    {
        for(int i = 1; i <= 24; i++)
        {
            if(checkers[0][i] != 0)
            {
                bars[i].setCheckerColor(Checker_Color.RED);
                bars[i].setCheckerNumber(checkers[0][i]);
            }
            if(checkers[1][i] != 0)
            {
                bars[25-i].setCheckerColor(Checker_Color.WHITE);
                bars[25-i].setCheckerNumber(checkers[1][i]);
            }
            if(checkers[0][i] == 0 && checkers[1][25-i] == 0)
            {
                bars[i].setCheckerColor(Checker_Color.EMPTY);
                bars[i].setCheckerNumber(0);
            }
        }
    }

    public int getRedHit ()
    {
        return checkers[0][25];
    }

    public int getWhiteHit ()
    {
        return checkers[1][25];
    }

    public int getRedBear ()
    {
        return checkers[0][0];
    }

    public int getWhiteBear ()
    {
        return checkers[1][0];
    }

}