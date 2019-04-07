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
	
	public void reset()
	{
		currentPlayer = null;
		currentPoints = RESET;
	}
}