package BackGammon;

public class DoubleCube
{
	private Player currentPlayer;
	private int currentPoints;
	private final int MAX = 64;
	private final int RESET = 1;
	
	public DoubleCube()
	{
		reset();
	}
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public int getCurrentPoints()
	{
		return currentPoints;
	}
	
	public boolean doubleThePoints(Player p)
	{
		if(p != currentPlayer && currentPoints != MAX)
		{
			currentPlayer = p;
			currentPoints *= 2;
			return true;
		}
		else
			return false;
	}

	public boolean checkDouble(Checker_Color current)
	{
		if (currentPlayer == null)
			return true;
		else if (current == currentPlayer.getColor())
			return false;
		else
			return true;

	}
	
	public void reset()
	{
		currentPlayer = null;
		currentPoints = RESET;
	}
}