package BackGammonGUI;

import BackGammon.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.concurrent.TimeUnit;

public class GameController
{
    //mainPane for add other pane together, grid for map contollers
    private GridPane mainPane = new GridPane();
    private GridPane grid = new GridPane();

    //GUI classes
    private BoardVisual boardVisual = new BoardVisual();
    private DiceVisual diceVisual = new DiceVisual();
    private DoublingCubeVisual doublingCubeVisual = new DoublingCubeVisual();
    private PipNumVisual pipNumVisual = new PipNumVisual();

    //Algorithm classes
    private Players players = new Players();
    private Board board = new Board(players);
    private Dice dice = new Dice();
    private Plays plays = new Plays();
    private DoubleCude doubleCude = new DoubleCude();

    //dicepoint for start game
    private int dicePoint1 = 7;
    private int dicePoint2 = 7;

    //namebuffer for exchanging name between players
    private String nameBuffer;

    //Assign all control elements on the girdpane
    private Label backGammon = new Label("BackGammon");
    private Label player1Lab = new Label();
    private Label player2Lab = new Label();
    private TextField insertbox = new TextField();
    private Button enterClickButton = new Button("       Return      ");
    private TextArea outputTextBox = new TextArea();
    private String messegeBuffer;
    private String messegeBufferForCom;

    //current Turn for mark current turn
    private Checker_Color currentTurn;

    //Match controller
    private boolean inMatch = false;
    private boolean dcInRequest = false;
    private boolean moveInRequest = false;

    //Assign gridpane for all the control elements
    public GridPane GameController()
    {
        grid = new GridPane();
        for (int i = 0; i < 19;i++)
        {
            if (i == 0)
                grid.getColumnConstraints().add(new ColumnConstraints(70));
            else if (i >= 1 && i<= 6)
                grid.getColumnConstraints().add(new ColumnConstraints(63));
            else if (i == 7)
                grid.getColumnConstraints().add(new ColumnConstraints(90));
            else if (i >= 8 && i<= 13)
                grid.getColumnConstraints().add(new ColumnConstraints(63));
            else if (i == 14)
                grid.getColumnConstraints().add(new ColumnConstraints(110));
            else
                grid.getColumnConstraints().add(new ColumnConstraints(55));
        }
        for (int i = 0; i < 36;i++)
        {
            if (i == 0)
                grid.getRowConstraints().add(new RowConstraints(55));
            else if (i >= 1 && i <= 33)
                grid.getRowConstraints().add(new RowConstraints(22));
            else
                grid.getRowConstraints().add(new RowConstraints(35));
        }
        return this.grid;
    }

    //Game start settings
    public void gameStart()
    {
        boardVisual.BoardVisual();
        diceVisual.DiceVisual();
        diceVisual.inPutDiceImages();
        doublingCubeVisual.DoublingCubeVisual();
        doublingCubeVisual.inputDoublingDiceImages();
        doublingCubeVisual.cubeDisplay(null,64);
        pipNumVisual.PipNumVisual();
        doubleCude.reset();
        initControlVisual();
        instructMessage();
        currentTurn = Checker_Color.EMPTY;
        inMatch = true;
        moveInRequest = false;
        dcInRequest = false;
        players.get(0).setName("Player1");
        players.get(1).setName("Player2");
        assignPlayerLabel();
        outputTextBox.appendText("Game Start!\nType START to roll start dice\n");
    }

    //instruction message for game
    private void instructMessage()
    {
        outputTextBox.appendText("Welcome to BackGammon!\n");
        outputTextBox.appendText("***********************\n");
        outputTextBox.appendText("Game instruction:\n");
        outputTextBox.appendText("Type START to do start dice roll\n");
        outputTextBox.appendText("Type NAME1+YourName to set player1 name\n");
        outputTextBox.appendText("Type NAME2+YourName to set player2 name\n");
        outputTextBox.appendText("Type CHEAT to do a cheat move\n");
        outputTextBox.appendText("Type NEXT to pass move right to next player\n");
        outputTextBox.appendText("Type CLEAR to clear board messages. Type QUIT to exit\n");
        outputTextBox.appendText("***********************\n");
    }

    //Map control elements on the gridpane
    private GridPane initControlVisual()
    {
        //Game Title
        backGammon.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(backGammon,4);
        grid.add(backGammon, 15, 0);

        //Insert box
        insertbox.setPromptText("Please insert...");
        grid.add(insertbox,0,35, 14, 1);

        //Insert button
        grid.add(enterClickButton,14,35);

        //Output Area
        outputTextBox.setEditable(false);
        outputTextBox.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, 15));
        grid.add(outputTextBox,15, 1,4,35);
        outputTextBox.setWrapText(true);

        return grid;
    }

    //assign players name label
    private void assignPlayerLabel()
    {
        //Player 1 Label
        player1Lab = new Label("Player1");
        player1Lab.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player1Lab,6);
        grid.add(player1Lab, 1, 0);

        //Player 2 Label
        player2Lab = new Label("Player2");
        player2Lab.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player2Lab,6);
        grid.add(player2Lab, 8, 0);
    }

    //event handler for get button click or return key type
    public void getInsert()
    {
        enterClickButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                judgeInsert();
            }
        });

        insertbox.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    judgeInsert();
                }
            }
        });
    }

    //judge insert in the insertbox
    private void judgeInsert ()
    {
        messegeBuffer =  new String(insertbox.getText());
        messegeBufferForCom = messegeBuffer.toUpperCase();

        if (inMatch == false)
        {
            if (messegeBufferForCom.substring(0,4).equals("QUIT") || messegeBufferForCom.substring(0,4).equals("EXIT"))
            {
                exit();
            }
            else if (messegeBufferForCom.substring(0,4).equals("QUIT") )
            {
                //Add here

            }
            else
                throwLogicFailure();
        }
        else if (inMatch == true)
        {
            //Judge current status, if is EMPTY lead user to roll dice
            if (currentTurn == Checker_Color.EMPTY)
            {
                if ((insertbox.getText() != null && !insertbox.getText().isEmpty()) && insertbox.getText().length()>= 4)
                {
                    if (messegeBufferForCom.substring(0,4).equals("QUIT") || messegeBufferForCom.substring(0,4).equals("EXIT"))
                    {
                        exit();
                    }
                    else if (insertbox.getText().length()>= 5)
                    {
                        if (messegeBufferForCom.substring(0,5).equals("START"))
                        {
                            startRoll();
                        }
                        else if (messegeBufferForCom.substring(0,5).equals("NAME1"))
                        {
                            player1Name();
                        }
                        else if(messegeBufferForCom.substring(0,5).equals("NAME2"))
                        {
                            player2Name();
                        }
                        else if (messegeBufferForCom.substring(0,5).equals("CLEAR"))
                        {
                            clear();
                        }
                        else
                        {
                            throwInalidTypo();
                            outputTextBox.appendText("You must type START to do start game roll\n");
                        }
                    }
                    else
                    {
                        throwInalidTypo();
                        outputTextBox.appendText("You must type START to do start game roll\n");
                    }
                }
                else
                {
                    throwInalidTypo();
                    outputTextBox.appendText("You must type START to do start game roll\n");
                }
            }
            else if(currentTurn == Checker_Color.RED || currentTurn == Checker_Color.WHITE)
            {
                if ((insertbox.getText() != null && !insertbox.getText().isEmpty()) && insertbox.getText().length()<4 && moveInRequest == true)
                {
                    getMoveDecision(messegeBufferForCom,insertbox.getText().length());
                }
                else if ((messegeBufferForCom.substring(0,3).equals("YES") || messegeBufferForCom.substring(0,2).equals("NO")) && dcInRequest == true)
                {
                    if (messegeBufferForCom.substring(0,3).equals("YES"))
                    {
                        doublingCubeRespond();
                    }
                    else if (messegeBufferForCom.substring(0,2).equals("NO"))
                    {
                        doublingCubeCauseGameOver();
                    }
                    else
                        throwLogicFailure();
                }
                else if ((insertbox.getText() != null && !insertbox.getText().isEmpty()) && insertbox.getText().length()>= 4)
                {
                    if (messegeBufferForCom.substring(0,4).equals("QUIT") || messegeBufferForCom.substring(0,4).equals("EXIT")  )
                    {
                        exit();
                    }
                    else if (messegeBufferForCom.length() >= 5 )
                    {
                        if (messegeBufferForCom.substring(0,5).equals("CHEAT"))
                        {
                            cheatMove();
                        }
                        else if (messegeBufferForCom.substring(0,5).equals("NAME1"))
                        {
                            player1Name();
                        }
                        else if(messegeBufferForCom.substring(0,5).equals("NAME2"))
                        {
                            player2Name();
                        }
                        else if (messegeBufferForCom.substring(0,5).equals("START"))
                        {
                            outputTextBox.appendText("Sorry START roll dice is not a valid call anymore\n");
                            clear();
                        }
                        else if (messegeBufferForCom.substring(0,5).equals("CLEAR"))
                        {
                            clear();
                        }
                        else if (messegeBufferForCom.length() >= 7)
                        {
                            if (messegeBufferForCom.substring(0,7).equals("RESTART"))
                            {
                                insertbox.clear();
                                GameController();
                            }
                            else
                                throwInalidTypo();
                        }
                        else
                            throwInalidTypo();
                    }
                    else
                        throwInalidTypo();
                }
                else
                    throwInalidTypo();
            }
            else
                throwLogicFailure();
        }
        else
            throwLogicFailure();
    }

    /*
    Game turn controls
     */
    //Make a move
    private void cheatMove()
    {
        currentTurn = Checker_Color.WHITE;
        moveInRequest = false;
        board.cheat();
        boardVisual.removeElements();
        boardVisual.BoardVisual(board);
        passTurn();
    }

    private void printDoubleCube (Player player, int num)
    {
        doublingCubeVisual.cubeDisplay(player, num);
    }

    private void doublingCubeRequest()
    {
        outputTextBox.appendText("Do you wanna double the cube?\nPlease type yes or no:\n");
        dcInRequest = true;
    }

    private void doublingCubeCauseGameOver ()
    {
        clear();
        outputTextBox.appendText("You didn't accept doubling cube then game over.\n");
    }

    private void doublingCubeRespond ()
    {
        clear();
        dcInRequest = false;
        if (currentTurn == Checker_Color.RED)
            doubleCude.doubleThePoints(players.get(0));
        else if (currentTurn == Checker_Color.WHITE)
            doubleCude.doubleThePoints(players.get(1));

        passTurn();
    }

    //enter NEXT to pass to next player
    private void passTurn()
    {
        insertbox.clear();
        if (!board.isGameOver())
        {
            diceVisual.removeDisplay();
            doublingCubeVisual.removeDisplay();
            currentTurn = changeTurn(currentTurn);
            currentTurn();
            diceInGame();
            checkAvailable();
        }
        else if (board.isGameOver())
        {
            outputTextBox.appendText("Game is Over. The Winner is:\n");
            outputTextBox.appendText(board.getWinner().toString());
        }
        else
            throwLogicFailure();
    }

    private void getMoveDecision(String s, int length)
    {
        insertbox.clear();

        char c1;
        char c2;
        int num;

        if (length == 1)
        {
            c1 = s.charAt(0);
            num = c1 - 'A';
        }
        else
        {
            c1 = s.charAt(0);
            c2 = s.charAt(1);
            num = c1 - 'A';
            num *= 26;
            num += (c2 - 'A');
        }
        outputTextBox.appendText("You typed:" + s + "\n");
        makeMove(num);
        moveInRequest = false;
        boardVisual.removeElements();
        boardVisual.BoardVisual(board);
        doublingCubeRequest();
    }

    private void makeMove(int num)
    {
        Play play;
        play = plays.get(num);

        if (currentTurn == Checker_Color.RED)
        {
            board.move(players.get(0),play);
        }
        else if (currentTurn == Checker_Color.WHITE)
        {
            board.move(players.get(1),play);
        }
    }

    private void checkAvailable()
    {
        if (currentTurn == players.get(0).getColor())
        {
            plays = board.getPossiblePlays(players.get(0),dice);
        }
        else if (currentTurn == players.get(1).getColor())
        {
            plays = board.getPossiblePlays(players.get(1),dice);
        }
        else
            throwLogicFailure();

        if (plays.number() == 0)
        {
            outputTextBox.appendText("=>No available this turn.\n");
            passTurn();
        }
        else if (plays.number() == 1)
        {
            outputTextBox.appendText("=>There's only one move available:\n");
            printMoves(plays);
            makeMove(0);
            outputTextBox.appendText("Move made.\n");
            passTurn();
        }
        else
        {
            outputTextBox.appendText(">Available moves:\n");
            printMoves(plays);
            outputTextBox.appendText("Please type letter to move:\n");
            moveInRequest = true;
        }
    }

    //print out all possible moves
    private void printMoves( Plays plays )
    {
        int index = 0;
        for (Play play : plays)
        {
            String code;
            if (index<26)
            {
                code = "" + (char) (index%26 + (int) 'A');
            }
            else {
                code = "" + (char) (index/26 - 1 + (int) 'A') + (char) (index % 26 + (int) 'A');
            }

            outputTextBox.appendText(code + ". " + play + "\n");
            index++;
        }
    }

    //Current turn instruction
    private void currentTurn()
    {
        if (currentTurn == Checker_Color.RED)
        {
            outputTextBox.appendText("->" + players.get(0).toString() + "'s turn.\n");
            outputTextBox.appendText("->" + "Checker color RED.\n");
            assignPipNum(currentTurn);
        }
        else if (currentTurn == Checker_Color.WHITE)
        {
            outputTextBox.appendText("->" + players.get(1).toString() + "'s turn.\n");
            outputTextBox.appendText("->" + "Checker color WHITE.\n");
            assignPipNum(currentTurn);
        }
        else
            throwLogicFailure();
    }

    //Automatically change turn
    private Checker_Color changeTurn(Checker_Color currentTurn)
    {
        outputTextBox.appendText("-----------------------------\n");
        if (currentTurn == Checker_Color.WHITE)
            return Checker_Color.RED;
        else if (currentTurn == Checker_Color.RED)
            return Checker_Color.WHITE;
        else
        {
            throwLogicFailure();
            return currentTurn;
        }
    }

    //Each turn assign current pip numbers for current player
    private void assignPipNum(Checker_Color currentTurn)
    {
        pipNumVisual.assignLabel(currentTurn);
    }

    //Start game roll dice to decide who start first
    private void startRoll()
    {
        insertbox.clear();
        if (dicePoint1 == 7)
        {
            outputTextBox.appendText("-----------------------------\n");
            dice.rollDie();
            dicePoint1 = dice.getDie();
            diceVisual.DiceVisual();
            diceVisual.singleDisplay(dicePoint1);
            outputTextBox.appendText("First dice point: " + dicePoint1 + ".\n");
            outputTextBox.appendText("Please type START to do second roll.\n");
        }
        else if (dicePoint2 == 7)
        {
            dice.rollDie();
            dicePoint2 = dice.getDie();
            diceVisual.DiceVisual();
            diceVisual.singleDisplay(dicePoint2);
            outputTextBox.appendText("Second dice point: " + dicePoint2 + ".\n");
            if (dicePoint1 == dicePoint2)
            {
                outputTextBox.appendText("-----------------------------\n");
                outputTextBox.appendText("Got same point. Roll again.\n");
                dicePoint1 = 7;
                dicePoint2 = 7;
            }
            else if (dicePoint1 > dicePoint2)
            {
                outputTextBox.appendText("->"+players.get(0).toString()+" starts first.\n");
                outputTextBox.appendText("-----------------------------\n");
                currentTurn = Checker_Color.RED;
                currentTurn();
                outputTextBox.appendText("Dice point: "+ dicePoint1 +" and "+ dicePoint2 +".\n");
                diceVisual.removeDisplay();
                diceVisual.diceDisplay(dicePoint1,dicePoint2);
                dice.setDice(dicePoint1,dicePoint2);
                checkAvailable();
            }
            else if (dicePoint2 > dicePoint1)
            {
                outputTextBox.appendText("->"+players.get(1).toString()+" starts first.\n");
                outputTextBox.appendText("-----------------------------\n");
                nameBuffer = players.get(0).toString();
                players.get(0).setName(players.get(1).toString());
                players.get(1).setName(nameBuffer);
                currentTurn =  Checker_Color.RED;
                currentTurn();
                outputTextBox.appendText("Dice point: "+ dicePoint1 +" and "+ dicePoint2 +".\n");
                diceVisual.diceDisplay(dicePoint1,dicePoint2);
                dice.setDice(dicePoint1,dicePoint2);
                checkAvailable();
            }
            else
                throwLogicFailure();
        }
        else
            throwLogicFailure();
    }

    //Roll dice in the game
    private void diceInGame()
    {
        insertbox.clear();
        dice.rollDice();
        diceVisual.diceDisplay(dice.getDie(0),dice.getDie(1));
        diceVisual.DiceVisual();
        outputTextBox.appendText("Dice point: "+dice.getDie(0)+" and "+dice.getDie(1)+".\n");
    }

    //exit game
    private void exit()
    {
        System.exit(0);
    }

    //clear control output box
    private void clear()
    {
        insertbox.clear();
        outputTextBox.clear();
        instructMessage();
    }

    //ReAssign player's name
    private void player1Name()
    {
        insertbox.clear();
        grid.getChildren().remove(player1Lab);
        player1Lab = new Label(messegeBuffer.substring(5,messegeBuffer.length()));
        players.get(0).setName(messegeBuffer.substring(5,messegeBuffer.length()));
        outputTextBox.appendText("Player 1 name is: "+ messegeBuffer.substring(5,messegeBuffer.length())+"\n");
        //Player 1 Label
        player1Lab.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player1Lab,6);
        grid.add(player1Lab, 1, 0);
    }

    //ReAssign player's name
    private void player2Name()
    {
        insertbox.clear();
        grid.getChildren().remove(player2Lab);
        player2Lab = new Label(messegeBuffer.substring(5,messegeBuffer.length()));
        players.get(1).setName(messegeBuffer.substring(5,messegeBuffer.length()));
        outputTextBox.appendText("Player 2 name is: "+ messegeBuffer.substring(5,messegeBuffer.length())+"\n");
        //Player 2 Label
        player2Lab.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.setColumnSpan(player2Lab,6);
        grid.add(player2Lab, 8, 0);
    }

    //Invalid type warn
     private void throwInalidTypo()
    {
        insertbox.clear();
        outputTextBox.appendText("! Your typed: "+messegeBuffer+", it seems an invalid type.\n");
    }

    //Warn when game faceing impossible game logic failure
    private  void throwLogicFailure()
    {
        outputTextBox.appendText("! Sorry currently meet a logic failure. We recommend you reopen game.\n");
        outputTextBox.appendText("-----------------------------\n");
        insertbox.clear();
    }

    //return this gridpane
    public GridPane getPane()
    {
        mainPane.getChildren().add(boardVisual.BoardVisual(board));
        mainPane.getChildren().add(diceVisual.getGrid());
        mainPane.getChildren().add(doublingCubeVisual.getGrid());
        mainPane.getChildren().add(pipNumVisual.returnPane());
        mainPane.getChildren().add(grid);
        return this.mainPane;
    }
}
