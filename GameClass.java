

public class GameClass
{

	int pPoints;
	int cP1;
	int cP2;
	int cP3;

	public GameClass(int yay)
	{
		pPoints = yay;
		cP1 = yay;
		cP3 = yay;
		cP2 = yay;
	}
	public void checkAndGetOut(String[] yeet)
	//checks who has below -1 and promptly changes the [] so that they will not be included in the game
	{
		if(pPoints <0)
			yeet[0] = "k";
		if(cP1 <0)
			yeet[1] = "k";
		if(cP2 <0)
			yeet[2] = "k";
		if(cP3 <0)
			yeet[3] = "k";
		return;

	}
	public void downPoints(int index)
	//Gets an integer of who has the least points and subtracts their points by one
	{
		if(index==1)
			cP1--;
		else if(index == 2)
			cP2--;
		else if(index == 3)
			cP3--;
		else if(index==0)
			pPoints--;
		return;

	}
	public int getC3()
	{
		return cP3;
	}
	public int getC2()
	{
		return cP2;
	}
	public int getC1()
	{
		return cP1;
	}
	public int getPPoints()
	{
		return pPoints;
	}
	public boolean didPWin(String[] pIG)
	//simple method to check wether the player won or not by checking if their even in the game
	{
		if(pIG[0].equalsIgnoreCase("Player"))
			return true;
		return false;
	}
	public String whoWon(String[] pIG)
	//receives the [] and checks wwho the last person left is
	{
	
		String yeet = "";

		if(pIG[0].equalsIgnoreCase("PLAYER")&& !pIG[1].equalsIgnoreCase("comp1") && !pIG[2].equalsIgnoreCase("COMP2") && !pIG[3].equalsIgnoreCase("COMP3"))
			yeet="player";
		if(!pIG[0].equalsIgnoreCase("PLAYER")&& pIG[1].equalsIgnoreCase("comp1") && !pIG[2].equalsIgnoreCase("COMP2") && !pIG[3].equalsIgnoreCase("COMP3"))
			yeet="Computer 1";
		if(!pIG[0].equalsIgnoreCase("PLAYER") && !pIG[1].equalsIgnoreCase("comp1") && pIG[2].equalsIgnoreCase("COMP2") && !pIG[3].equalsIgnoreCase("COMP3"))
			yeet="Computer 2";
		if(!pIG[0].equalsIgnoreCase("PLAYER")&& !pIG[1].equalsIgnoreCase("comp1") && !pIG[2].equalsIgnoreCase("COMP2") && pIG[3].equalsIgnoreCase("COMP3"))
			yeet="Computer 3";

		return yeet;
	}
	public boolean someoneWon(String[] pIG, int yay)
	//checks if their is winner, doesnt check who won tho, returns boolean wether or not there is a winner
	{
		if(yay!=1)
			return false;

		int nullCounter = 0;

		if(!pIG[0].equalsIgnoreCase("player"))
			nullCounter++;
		if(!pIG[1].equalsIgnoreCase("comp1"))
			nullCounter++;
		if(!pIG[2].equalsIgnoreCase("comp2"))
			nullCounter++;
		if(!pIG[3].equalsIgnoreCase("comp3"))
			nullCounter++;

		if(nullCounter >= 3)
			return true;
		return false;

	}
}