package Sprint5;

public class CCR_Bot implements BotAPI{
    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;

    CCR_Bot (PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
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

        return Integer.toString(playNumber);
    }

    public String getDoubleDecision() {
        // Add your code here
        return "n";
    }

}
