public class BotCCR implements BotAPI{
    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;

    //SLOPE HERE:
    //TODO
    private final int BASIC_SLOPE = 1;
    private final int KICK_SLOPE = 2;



    BotCCR (PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.cube = cube;
        this.match = match;
        this.info = info;
    }

    public String getName() {
        return "BotCCR"; // must match the class name
    }

    public String getCommand(Plays possiblePlays) {
        // Add your code here
        int bestChoice = 0;
        int boardScores[] = new int[possiblePlays.number()];

        for(int i = 0; i < possiblePlays.number(); i++)
        {
            boardScores[i] = getBoardScore(possiblePlays,i);
            if(boardScores[bestChoice] < boardScores[i])
                bestChoice = i;
        }

        return Integer.toString(bestChoice+1);
    }

    public String getDoubleDecision() {
        int[][] boardCopy = board.get();
        int myPip = 0;
        int oppoPip = 0;
        for(int i = 0; i <= Board.BAR; i++)
        {
            myPip += boardCopy[me.getId()][i];
            oppoPip += boardCopy[opponent.getId()][i];
        }
        if((myPip >= 100) && (myPip + getBoardScore(me,boardCopy) <= 1.2*(oppoPip + getBoardScore(opponent,boardCopy))))
            return "y";//we may win then just double it
        else if((myPip >= 50 && myPip <= 100) && (myPip + getBoardScore(me,boardCopy) <= 1.1*(oppoPip + getBoardScore(opponent,boardCopy))))
            return "y";
        else if((myPip >= 10 && myPip <= 50) && (myPip + getBoardScore(me,boardCopy) <= (oppoPip + getBoardScore(opponent,boardCopy))))
            return "y";
        else if(me.getScore() < opponent.getScore() && (opponent.getScore() + cube.getValue()) > match.getLength())
            return "y";//if I give up, I will lose the match
        else
            return "n";
    }

    public int getBoardScore(Plays possiblePlays, int index)
    {
        int[][] boardCopy = board.get();
        Play targetPlay = possiblePlays.get(index);
        move(boardCopy,targetPlay);
        int boardScore = getBoardScore(me,boardCopy);
        return boardScore;
    }

    public int getBoardScore(PlayerAPI currentPlayer, int[][] boardCopy)
    {
        int boardScore = 0;
        //boardScore + features calculators;
        //FORMULA: boardScore += XXX_SLOPE*Finder(player,boardCopy);
        //TODO:
        boardScore += BASIC_SLOPE * basicBoardScore(currentPlayer,boardCopy);
        boardScore += KICK_SLOPE * kickOffBoardScore(currentPlayer,boardCopy);
        return boardScore;
    }

    private void move(int[][] boardCopy, Play targetPlay)
    {
        for (Move move : targetPlay) {
            move(boardCopy,move);
        }
    }

    private void move(int[][] boardCopy, Move targetMove)
    {
        boardCopy[me.getId()][targetMove.getFromPip()]--;
        boardCopy[me.getId()][targetMove.getToPip()]++;
        if (targetMove.getToPip()<Board.BAR && targetMove.getToPip()>Board.BEAR_OFF &&
                boardCopy[opponent.getId()][calculateOpposingPip(targetMove.getToPip())] == 1) {
            boardCopy[opponent.getId()][calculateOpposingPip(targetMove.getToPip())]--;
            boardCopy[opponent.getId()][Board.BAR]++;
        }
    }

    private int calculateOpposingPip(int pip) {
        return Board.NUM_PIPS-pip+1;
    }

    private int basicBoardScore(PlayerAPI player, int[][] boardCopy)
    {
        int counter = 0;

        for(int i = 0; i <= Board.BAR; i++)
        {
            if(i == 0)
            {
                counter += 5*boardCopy[player.getId()][i];//more bear off get higher score
            }
            else if(i == 25)
            {
                counter += (-5)*boardCopy[player.getId()][i];//more bar get lower score
            }
            else//1-24
            {
                if(boardCopy[player.getId()][i] == 1)
                    counter += (-2);//single checker left
                else if(boardCopy[player.getId()][i] > 1)
                    counter += 2;//checkers group
            }
        }

        return counter;
    }

    private int kickOffBoardScore(PlayerAPI player, int[][] boardCopy)
    {
        if(player == me)
            return boardCopy[opponent.getId()][25];
        else
            return boardCopy[me.getId()][25];
    }
}
