public class BotCCR implements BotAPI{
    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;

    BotCCR (PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.cube = cube;
        this.match = match;
        this.info = info;
    }

    public String getName() {
        return "CCR_Bot"; // must match the class name
    }

    public String getCommand(Plays possiblePlays) {
        // Add your code here
        int playNumber = 0;

        int boardScores[] = new int[possiblePlays.number()];
        for(int i = 0; i < possiblePlays.number(); i++)
        {
            boardScores[i] = getBoardScore(possiblePlays,i);
            if(boardScores[playNumber] < boardScores[i])
                playNumber = i;
        }
        return Integer.toString(playNumber+1);
    }

    public String getDoubleDecision() {
        //TODO
        int[][] boardCopy = board.get();
        int myPip = 0;
        int oppoPip = 0;
        for(int i = 0; i <= Board.BAR; i++)
        {
            myPip += boardCopy[me.getId()][i];
            oppoPip += boardCopy[opponent.getId()][i];
        }
        if(myPip + getBoardScore(boardCopy) <= 1.2*(oppoPip + getOppositeBoardScore(boardCopy)))
            return "y";//we may win then just double it
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

        int boardScore = getBoardScore(boardCopy);

        return boardScore;
    }

    public int getBoardScore(int[][] boardCopy)
    {
        int boardScore = 0;
        //boardScore + features calculators;
        //TODO
        return boardScore;
    }

    public int getOppositeBoardScore(int[][] boardCopy)
    {
        int boardScore = 0;
        //boardScore + features calculators;
        //TODO
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
}
