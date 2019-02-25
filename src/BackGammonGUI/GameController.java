package BackGammonGUI;

import BackGammon.Board;
import BackGammon.Checker_Color;
import BackGammon.Dice;
import BackGammon.Player;
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

public class GameController
{
    //mainPane for add other pane together, grid for map contollers
    private GridPane mainPane = new GridPane();
    private GridPane grid = new GridPane();

    //Create all needed elements
    private BoardVisual boardVisual = new BoardVisual();
    private DiceVisual diceVisual = new DiceVisual();
    private Board board = new Board();
    private Player player1 = new Player();
    private Player player2 = new Player();

    //Assign all control elements on the girdpane
    private Label backGammon = new Label("BackGammon");
    private Label player1Lab = new Label();
    private Label player2Lab = new Label();
    private TextField insertbox = new TextField();
    private Button enterClickButton = new Button("       Return      ");
    private TextArea outputTextBox = new TextArea();
    private String messegeBuffer;
    private String messegeBufferForCom;
    private Label[] piplabels = new Label[24];

    //two player's start dice point
    private int player1StartPoint = 7;
    private int player2StartPoint = 7;

    //current Turn for mark current turn
    private Checker_Color currentTurn;

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
        board.setUp();
        boardVisual.BoardVisual();
        diceVisual.DiceVisual();
        diceVisual.inPutDiceImages();
        initControlVisual();
        instructMessage();
        currentTurn = Checker_Color.EMPTY;
        player1.setName("Player1");
        player2.setName("Player2");
        assignPlayerLabel();
        outputTextBox.appendText("Game Start!\nType START to roll start dice\n");
    }

    //instruction message for game
    private void instructMessage()
    {
        outputTextBox.appendText("Welcome to BackGammon!\n");
        outputTextBox.appendText("***********************\n");
        outputTextBox.appendText("Game instruction:\n");
        outputTextBox.appendText("Type Move<pip1><pip2>, move one disk from pip1 to pip2\n");
        outputTextBox.appendText("Type CLEAR to clear board messages\nType QUIT to exit\n");
        outputTextBox.appendText("Type START to do start dice roll\n");
        outputTextBox.appendText("Type NEXT to pass move right to next player\n");
        outputTextBox.appendText("Type NAME1+YourName to enter player1 name\n");
        outputTextBox.appendText("Type NAME2+YourName to enter player2 name\n");
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

        //Judge current status, if is EMPTY lead user to roll dice
        if (currentTurn == Checker_Color.EMPTY)
        {
            if ((insertbox.getText() != null && !insertbox.getText().isEmpty()) && insertbox.getText().length()>= 5)
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
        else if(currentTurn == Checker_Color.RED || currentTurn == Checker_Color.WHITE)
        {
            if ((insertbox.getText() != null && !insertbox.getText().isEmpty()) && insertbox.getText().length()>= 4)
            {
                if(messegeBufferForCom.substring(0,4).equals("MOVE"))
                {
                    makeMove();
                }
                else if (messegeBufferForCom.substring(0,4).equals("ROLL"))
                {
                    diceInGame();
                }
                else if (messegeBufferForCom.substring(0,4).equals("NEXT"))
                {
                    passTurn();
                }
                else if (messegeBufferForCom.substring(0,4).equals("QUIT") || messegeBufferForCom.substring(0,4).equals("EXIT")  )
                {
                    exit();
                }
                else if (messegeBufferForCom.length() >= 5 )
                {
                    if (messegeBufferForCom.substring(0,5).equals("NAME1"))
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

    /*
    Game turn controls
     */
    //Make a move
    private void makeMove()
    {
        insertbox.clear();
        outputTextBox.appendText("Make a Move:\n");
        int start = -1;
        int end = -1;
        for(int i = 1; messegeBuffer.charAt(i) != '\u0000'; i++)
        {
            if(messegeBuffer.charAt(i-1) == '<' && messegeBuffer.charAt(i+1) == '>' && start == -1)
                start = messegeBuffer.charAt(i) - '0';
            else if(messegeBuffer.charAt(i-1) == '<' && messegeBuffer.charAt(i+1) == '>'  && start != -1)
                end =  messegeBuffer.charAt(i) - '0';
            if(start > 0 && end > 0)
                break;
        }

        Boolean status;

        //Judge move valid or not
        if (currentTurn == Checker_Color.WHITE)
        {
            status = board.move(Checker_Color.WHITE,25-start,25-end);
            boardVisual.removeElements();
            boardVisual.BoardVisual(board);
            if (status == true)
                outputTextBox.appendText("Move from " + start + " to " + end + "\n");
            else
                outputTextBox.appendText("You tried to move from " + start + " to " +end +" but it's invalid.\n");
        }
        else if (currentTurn == Checker_Color.RED)
        {
            status = board.move(Checker_Color.RED,start,end);
            boardVisual.removeElements();
            boardVisual.BoardVisual(board);
            if (status == true)
                outputTextBox.appendText("Move from " + start + " to " + end + "\n");
            else
                outputTextBox.appendText("You tried to move from " + start + " to " +end +" but it's invalid.\n");
        }
        else
            throwLogicFailure();
    }

    //enter NEXT to pass to next player
    private void passTurn()
    {
        insertbox.clear();
        diceVisual.removeDisplay();
        currentTurn = changeTurn(currentTurn);
        currentTurn();
        diceInGame();
    }

    //Current turn instruction
    private void currentTurn()
    {
        if (player1.getColor() == currentTurn)
        {
            outputTextBox.appendText("->"+player1.getName()+"'s turn. ");
            if (player1.getColor() == Checker_Color.WHITE)
                outputTextBox.appendText("Checker color WHITE.\n");
            else if (player1.getColor() == Checker_Color.RED)
                outputTextBox.appendText("Checker color RED.\n");
            else
                throwInalidTypo();
            assignPipNum(currentTurn);
        }
        else if (player2.getColor() == currentTurn)
        {
            outputTextBox.appendText("->"+player2.getName()+"'s turn.");
            if (player2.getColor() == Checker_Color.WHITE)
                outputTextBox.appendText("Checker color WHITE.\n");
            else if (player2.getColor() == Checker_Color.RED)
                outputTextBox.appendText("Checker color RED.\n");
            else
                throwInalidTypo();
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
        if (piplabels[0]!=null)
        {
            for (int i = 0; i <24; i++)
            {
                grid.getChildren().remove(piplabels[i]);
            }
        }
        if (currentTurn == Checker_Color.RED)
        {
            piplabels[0] =  new Label("   12");
            piplabels[0].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[1] =  new Label("   11");
            piplabels[1].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[2] =  new Label("   10");
            piplabels[2].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[3] =  new Label("    9");
            piplabels[3].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[4] =  new Label("    8");
            piplabels[4].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[5] =  new Label("    7");
            piplabels[5].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[6] =  new Label("    6");
            piplabels[6].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[7] =  new Label("    5");
            piplabels[7].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[8] =  new Label("    4");
            piplabels[8].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[9] =  new Label("    3");
            piplabels[9].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[10] =  new Label("    2");
            piplabels[10].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[11] =  new Label("    1");
            piplabels[11].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[12] =  new Label("   13");
            piplabels[12].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[13] =  new Label("   14");
            piplabels[13].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[14] =  new Label("   15");
            piplabels[14].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[15] =  new Label("   16");
            piplabels[15].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[16] =  new Label("   17");
            piplabels[16].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[17] =  new Label("   18");
            piplabels[17].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[18] =  new Label("   19");
            piplabels[18].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[19] =  new Label("   20");
            piplabels[19].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[20] =  new Label("   21");
            piplabels[20].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[21] =  new Label("   22");
            piplabels[21].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[22] =  new Label("   23");
            piplabels[22].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[23] =  new Label("   24");
            piplabels[23].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

            grid.add(piplabels[0],1,14);
            grid.add(piplabels[1],2,14);
            grid.add(piplabels[2],3,14);
            grid.add(piplabels[3],4,14);
            grid.add(piplabels[4],5,14);
            grid.add(piplabels[5],6,14);
            grid.add(piplabels[6],8,14);
            grid.add(piplabels[7],9,14);
            grid.add(piplabels[8],10,14);
            grid.add(piplabels[9],11,14);
            grid.add(piplabels[10],12,14);
            grid.add(piplabels[11],13,14);
            grid.add(piplabels[12],1,21);
            grid.add(piplabels[13],2,21);
            grid.add(piplabels[14],3,21);
            grid.add(piplabels[15],4,21);
            grid.add(piplabels[16],5,21);
            grid.add(piplabels[17],6,21);
            grid.add(piplabels[18],8,21);
            grid.add(piplabels[19],9,21);
            grid.add(piplabels[20],10,21);
            grid.add(piplabels[21],11,21);
            grid.add(piplabels[22],12,21);
            grid.add(piplabels[23],13,21);
        }
        else if (currentTurn == Checker_Color.WHITE)
        {
            piplabels[0] =  new Label("   13");
            piplabels[0].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[1] =  new Label("   14");
            piplabels[1].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[2] =  new Label("   15");
            piplabels[2].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[3] =  new Label("   16");
            piplabels[3].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[4] =  new Label("   17");
            piplabels[4].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[5] =  new Label("   18");
            piplabels[5].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[6] =  new Label("   19");
            piplabels[6].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[7] =  new Label("   20");
            piplabels[7].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[8] =  new Label("   21");
            piplabels[8].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[9] =  new Label("   22");
            piplabels[9].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[10] =  new Label("   23");
            piplabels[10].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[11] =  new Label("   24");
            piplabels[11].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[12] =  new Label("   12");
            piplabels[12].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[13] =  new Label("   11");
            piplabels[13].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[14] =  new Label("   10");
            piplabels[14].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[15] =  new Label("    9");
            piplabels[15].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[16] =  new Label("    8");
            piplabels[16].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[17] =  new Label("    7");
            piplabels[17].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[18] =  new Label("    6");
            piplabels[18].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[19] =  new Label("    5");
            piplabels[19].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[20] =  new Label("    4");
            piplabels[20].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[21] =  new Label("    3");
            piplabels[21].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[22] =  new Label("    2");
            piplabels[22].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
            piplabels[23] =  new Label("    1");
            piplabels[23].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

            grid.add(piplabels[0],1,14);
            grid.add(piplabels[1],2,14);
            grid.add(piplabels[2],3,14);
            grid.add(piplabels[3],4,14);
            grid.add(piplabels[4],5,14);
            grid.add(piplabels[5],6,14);
            grid.add(piplabels[6],8,14);
            grid.add(piplabels[7],9,14);
            grid.add(piplabels[8],10,14);
            grid.add(piplabels[9],11,14);
            grid.add(piplabels[10],12,14);
            grid.add(piplabels[11],13,14);
            grid.add(piplabels[12],1,21);
            grid.add(piplabels[13],2,21);
            grid.add(piplabels[14],3,21);
            grid.add(piplabels[15],4,21);
            grid.add(piplabels[16],5,21);
            grid.add(piplabels[17],6,21);
            grid.add(piplabels[18],8,21);
            grid.add(piplabels[19],9,21);
            grid.add(piplabels[20],10,21);
            grid.add(piplabels[21],11,21);
            grid.add(piplabels[22],12,21);
            grid.add(piplabels[23],13,21);
        }
        else
            throwLogicFailure();
    }

    //Start game roll dice to decide who start first
    private void startRoll()
    {
        insertbox.clear();
        Dice dice = new Dice();
        if (player1StartPoint == 7)
        {
            outputTextBox.appendText("-----------------------------\n");
            player1StartPoint = dice.roll();
            diceVisual.DiceVisual();
            diceVisual.singleDisplay(player1StartPoint);
            outputTextBox.appendText("First dice point: " + player1StartPoint + ".\n");
            outputTextBox.appendText("Please type START to do second roll.\n");
        }
        else if (player2StartPoint == 7)
        {
            player2StartPoint = dice.roll();
            diceVisual.DiceVisual();
            diceVisual.singleDisplay(player2StartPoint);
            outputTextBox.appendText("Second dice point: " + player2StartPoint + ".\n");
            if (player1StartPoint == player2StartPoint)
            {
                outputTextBox.appendText("-----------------------------\n");
                outputTextBox.appendText("Got same point. Roll again.\n");
                player1StartPoint = 7;
                player2StartPoint = 7;
            }
            else if (player1StartPoint > player2StartPoint)
            {
                outputTextBox.appendText("->"+player1.getName()+" starts first.\n");
                outputTextBox.appendText("-----------------------------\n");
                player1.setColor(Checker_Color.WHITE);
                player2.setColor(Checker_Color.RED);
                currentTurn = Checker_Color.WHITE;
                currentTurn();
                outputTextBox.appendText("Dice point: "+player1StartPoint+" and "+player2StartPoint+".\n");
            }
            else if (player2StartPoint > player1StartPoint)
            {
                outputTextBox.appendText("->"+player2.getName()+" starts first.\n");
                outputTextBox.appendText("-----------------------------\n");
                player1.setColor(Checker_Color.RED);
                player2.setColor(Checker_Color.WHITE);
                currentTurn =  Checker_Color.WHITE;
                currentTurn();
                outputTextBox.appendText("Dice point: "+player1StartPoint+" and "+player2StartPoint+".\n");
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
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        final int Num1 = dice1.roll();
        final int Num2 = dice2.roll();
        diceVisual.DiceVisual();
        diceVisual.diceDisplay(Num1,Num2);
        outputTextBox.appendText("Dice point: "+Num1+" and "+Num2+".\n");
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
        player1.setName(messegeBuffer.substring(5,messegeBuffer.length()));
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
        player2.setName(messegeBuffer.substring(5,messegeBuffer.length()));
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
        mainPane.getChildren().add(grid);
        return this.mainPane;
    }
}
