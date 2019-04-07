package BackGammonGUI;

import BackGammon.Board;
import BackGammon.Checker_Color;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class BoardVisual
{
    GridPane grid = new GridPane();
    CheckerVisual[] checkers = new CheckerVisual[30];
    CheckerVisual[] hitcheckers = new CheckerVisual[30];
    FinishCheckerVisual[] finishCheckerVisuals = new FinishCheckerVisual[30];

    //Assign gridpane
    public GridPane BoardVisual ()
    {
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
        System.out.println("BackGammonGUI.BoardVisual.BoardVisual(): Gridpane assign successful");
        return this.grid;
    }

    //Paint checkers on the board
    public GridPane BoardVisual (Board board)
    {
        int indexNum;
        int bottomNum;
        int checkerCount = 0;

        for (int i = 1; i < 25; i++)
        {
            indexNum = board.bars[i].getCheckerNumber();

            if (i <= 12)
            {
                bottomNum = 32;
                while(indexNum != 0)
                {
                    if (i <= 6)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),14 - i,bottomNum);
                        grid.add(checkers[checkerCount],14-i,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum--;
                        indexNum--;
                    }
                    else if (i > 6)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),13-i,bottomNum);
                        grid.add(checkers[checkerCount],13-i,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum--;
                        indexNum--;
                    }
                }
            }

            else if (i > 12)
            {
                bottomNum = 1;
                while(bottomNum < indexNum + 1)
                {
                    if (i <= 18)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),i - 11,bottomNum);
                        grid.add(checkers[checkerCount],i - 12,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum++;
                    }
                    else if (i > 18)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),i - 12,bottomNum);
                        grid.add(checkers[checkerCount],i - 11,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum++;
                    }
                }
            }
        }



        int hitred = board.getRedHit();
        int hitwhite = board.getWhiteHit();
        int hitCounter = 0;


        for (int i = 0; i < 15; i++)
        {
            if (hitred > 0)
            {
                hitcheckers[hitCounter] = new CheckerVisual(Checker_Color.RED,0,0);
                grid.add(hitcheckers[hitCounter],7,16-i);
                hitCounter++;
                hitred--;
            }
            else break;
        }

        for (int i = 0; i < 15; i++)
        {
            if (hitwhite > 0)
            {
                hitcheckers[hitCounter] = new CheckerVisual(Checker_Color.WHITE,0,0);
                grid.add(hitcheckers[hitCounter],7,19+i);
                hitCounter++;
                hitwhite--;
            }
            else break;
        }

        int bearred = board.getRedBear();
        int bearwhite = board.getWhiteBear();
        int bearCounter = 0;

        for (int i = 0; i < 15; i++)
        {
            if (bearred > 0)
            {
                finishCheckerVisuals[bearCounter] = new FinishCheckerVisual(Checker_Color.RED,0,0);
                grid.add(finishCheckerVisuals[bearCounter],14,31 - i);
                bearCounter++;
                bearred--;
            }
            else break;
        }

        for (int i = 0; i < 15; i++)
        {
            if (bearwhite > 0)
            {
                finishCheckerVisuals[bearCounter] = new FinishCheckerVisual(Checker_Color.WHITE,0,0);
                grid.add(finishCheckerVisuals[bearCounter],14,15 - i);
                bearCounter++;
                bearwhite--;
            }
            else break;
        }

        System.out.println("BackGammonGUI.BoardVisual.BoardVisual(board): Checkers map on gridpane successful");
        return this.grid;
    }

    private GridPane BoardTest (Board board)
    {
        int indexNum;
        int bottomNum;
        int checkerCount = 0;

        for (int i = 1; i < 25; i++)
        {
            indexNum = board.bars[i].getCheckerNumber();

            if (i <= 12)
            {
                bottomNum = 32;
                while(indexNum != 0)
                {
                    if (i <= 6)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),14 - i,bottomNum);
                        grid.add(checkers[checkerCount],14-i,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum--;
                        indexNum--;
                    }
                    else if (i > 6)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),13-i,bottomNum);
                        grid.add(checkers[checkerCount],13-i,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum--;
                        indexNum--;
                    }
                }
            }

            else if (i > 12)
            {
                bottomNum = 1;
                while(bottomNum < indexNum + 1)
                {
                    if (i <= 18)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),i - 11,bottomNum);
                        grid.add(checkers[checkerCount],i - 12,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum++;
                    }
                    else if (i > 18)
                    {
                        checkers[checkerCount] = new CheckerVisual(board.bars[i].getColor(),i - 12,bottomNum);
                        grid.add(checkers[checkerCount],i - 11,bottomNum);
                        if (checkers[checkerCount].getType() != Checker_Color.EMPTY)
                        {
                            checkerCount++;
                        }
                        bottomNum++;
                    }
                }
            }
        }



        int hitred = board.getRedHit();
        int hitwhite = board.getWhiteHit();
        int hitCounter = 0;


        for (int i = 0; i < 15; i++)
        {
            if (hitred > 0)
            {
                hitcheckers[hitCounter] = new CheckerVisual(Checker_Color.RED,0,0);
                grid.add(hitcheckers[hitCounter],7,16-i);
                hitCounter++;
                hitred--;
            }
            else break;
        }

        for (int i = 0; i < 15; i++)
        {
            if (hitwhite > 0)
            {
                hitcheckers[hitCounter] = new CheckerVisual(Checker_Color.WHITE,0,0);
                grid.add(hitcheckers[hitCounter],7,19+i);
                hitCounter++;
                hitwhite--;
            }
            else break;
        }

        int bearred = board.getRedBear();
        int bearwhite = board.getWhiteBear();
        int bearCounter = 0;

        for (int i = 0; i < 15; i++)
        {
            if (bearred > 0)
            {
                finishCheckerVisuals[bearCounter] = new FinishCheckerVisual(Checker_Color.RED,0,0);
                grid.add(finishCheckerVisuals[bearCounter],14,31 - i);
                bearCounter++;
                bearred--;
            }
            else break;
        }

        for (int i = 0; i < 15; i++)
        {
            if (bearwhite > 0)
            {
                finishCheckerVisuals[bearCounter] = new FinishCheckerVisual(Checker_Color.WHITE,0,0);
                grid.add(finishCheckerVisuals[bearCounter],14,15 - i);
                bearCounter++;
                bearwhite--;
            }
            else break;
        }

        System.out.println("BackGammonGUI.BoardVisual.BoardVisual(board): Checkers map on gridpane successful");
        return this.grid;
    }

    //Remove all checkers on the gridpane
    public void removeElements()
    {
        for (int i = 0; i < 30; i++)
            grid.getChildren().removeAll(checkers[i]);
        for (int i = 0; i < 30; i++)
            grid.getChildren().removeAll(hitcheckers[i]);
        for (int i = 0; i < 30; i++)
            grid.getChildren().removeAll(finishCheckerVisuals[i]);
        System.out.println("BackGammonGUI.BoardVisual.removeElements(): Checkers remove from gridpane successful");
    }

}
