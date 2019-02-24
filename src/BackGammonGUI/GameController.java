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

import java.util.Scanner;

public class GameController
{
    private GridPane grid = new GridPane();

    private GridPane mainPane = new GridPane();
    private BoardVisual boardVisual = new BoardVisual();
    private DiceVisual diceVisual = new DiceVisual();
    private Board board = new Board();
    private Player player1 = new Player();
    private Player player2 = new Player();

    private Label backGammon = new Label("BackGammon");
    private Label player1Lab = new Label();
    private Label player2Lab = new Label();
    private TextField insertbox = new TextField();
    private Button insertTextBox = new Button("       Return      ");
    private TextArea outputTextBox = new TextArea();
    private String messegeBuffer;
    private String messegeBufferForCom;
    private Label[] piplabels = new Label[24];

    private int player1StartPoint = 7;
    private int player2StartPoint = 7;



    private Checker_Color currentTurn;

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
        outputTextBox.appendText("Game Start! Type START to roll start dice\n");
    }

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
        grid.add(insertTextBox,14,35);

        //Output Area
        outputTextBox.setEditable(false);
        outputTextBox.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, 15));
        grid.add(outputTextBox,15, 1,4,35);
        outputTextBox.setWrapText(true);

        return grid;
    }

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

    public void getInsert()
    {
        insertTextBox.setOnAction(new EventHandler<ActionEvent>()
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

    private void judgeInsert ()
    {
        messegeBuffer =  new String(insertbox.getText());
        messegeBufferForCom = messegeBuffer.toUpperCase();

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
                    insertbox.clear();
                    outputTextBox.appendText("Make a Move:\n");

                    Scanner input = new Scanner(messegeBuffer);
                    //int startPip = input.nextInt();
                    //int endPip = input.nextInt();

//                String move = new String(messegeBuffer.substring(4,messegeBuffer.length()));
//                int startNumPos = move.indexOf("<");
//                int startPip = (int)move.charAt(startNumPos+1);

                    //outputTextBox.appendText("Move from "+startPip+" to "+endPip+".\n");


                    outputTextBox.appendText("Please insert start pip:\n");
                    outputTextBox.appendText("-----------------------------\n");
                }
                else if (messegeBufferForCom.substring(0,4).equals("ROLL"))
                {
                    diceInGame();
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
                        outputTextBox.appendText("Sorry START roll dice is not valid call anymore\n");
                    }
                    else if (messegeBufferForCom.substring(0,7).equals("RESTART"))
                    {
                        insertbox.clear();
                        GameController();
                    }
                    else if (messegeBufferForCom.substring(0,5).equals("CLEAR"))
                    {
                        clear();
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

    private void currentTurn()
    {
        if (player1.getColor() == currentTurn)
        {
            outputTextBox.appendText("->"+player1.getName()+"'s turn\n");
        }
        else if (player2.getColor() == currentTurn)
        {
            outputTextBox.appendText("->"+player1.getName()+"'s turn\n");
        }
    }

    private Checker_Color changeTurn(Checker_Color currentTurn)
    {
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

    private void assignPipNum(Checker_Color currentTurn)
    {
        for (int i = 0; i <24; i++)
        {
        }
        if (currentTurn == Checker_Color.RED)
        {


        }
        else if (currentTurn == Checker_Color.WHITE)
        {

        }
        else
            throwLogicFailure();
    }


    private void startRoll()
    {
        insertbox.clear();
        Dice dice = new Dice();
        if (player1StartPoint == 7)
        {
            player1StartPoint = dice.roll();
            diceVisual.DiceVisual();
            diceVisual.singleDisplay(player1StartPoint);
            outputTextBox.appendText("First dice point: " + player1StartPoint + ".\n");
            outputTextBox.appendText("Please do second roll.\n");
        }
        else if (player2StartPoint == 7)
        {
            player2StartPoint = dice.roll();
            diceVisual.DiceVisual();
            diceVisual.singleDisplay(player2StartPoint);
            outputTextBox.appendText("Second dice point: " + player2StartPoint + ".\n");
            if (player1StartPoint == player2StartPoint)
            {
                outputTextBox.appendText("Got same point. Roll again.\n");
                player1StartPoint = 7;
                player2StartPoint = 7;
            }
            else if (player1StartPoint > player2StartPoint)
            {
                outputTextBox.appendText("->"+player1.getName()+" starts first.\n");
                outputTextBox.appendText("-----------------------------\n");
            }
            else if (player2StartPoint > player1StartPoint)
            {
                outputTextBox.appendText("->"+player2.getName()+" starts first.\n");
                outputTextBox.appendText("-----------------------------\n");
            }
            else
                throwLogicFailure();
        }
        else
            throwLogicFailure();
    }

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

    private void exit()
    {
        System.exit(0);
    }

    private void clear()
    {
        insertbox.clear();
        outputTextBox.clear();
        instructMessage();
    }

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

     private void throwInalidTypo()
    {
        insertbox.clear();
        outputTextBox.appendText("! Your typed: "+messegeBuffer+", it seems an invalid type.\n");
    }

    private  void throwLogicFailure()
    {
        outputTextBox.appendText("! Sorry currently meet a logic failure. We recommend you reopen game.\n");
        outputTextBox.appendText("-----------------------------\n");
        insertbox.clear();
    }

    private void instructMessage()
    {
        outputTextBox.appendText("Welcome to BackGammon!\n");
        outputTextBox.appendText("***********************\n");
        outputTextBox.appendText("Game instruction:\n");
        outputTextBox.appendText("Type Move<pip1><pip2>, move one disk from pip1 to pip2\n");
        outputTextBox.appendText("Type CLEAR to clear board messages\nType QUIT to exit\n");
        outputTextBox.appendText("Type NAME1 to enter player1 name\n");
        outputTextBox.appendText("Type NAME2 to enter player2 name\n");
        outputTextBox.appendText("***********************\n");
    }

    public GridPane getPane()
    {
        mainPane.getChildren().add(boardVisual.BoardVisual(board));
        mainPane.getChildren().add(diceVisual.getGrid());
        mainPane.getChildren().add(grid);
        return this.mainPane;
    }
}
