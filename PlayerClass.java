
public class PlayerClass
{
	private CardClass card1;
	private CardClass card2;
	private CardClass card3;
	private int points;
	private String primarySuit;

	public PlayerClass(CardClass a, CardClass b, CardClass c)
	{
		card1 = a;
		card2 = b;
		card3 = c;
		points = -1;
	
	}
	public CardClass getCard(int index)
	//receives index of the card the person wants and returns it
	{
		reassignThings();
		if(index == 1)
			return card1;
		else if(index==2)
			return card2;
		else if(index==3)
			return card3;
		return null;
			
	}
	public void printCards()
	//prints all cards in proper format
	{
		reassignThings();
		card1.toPrint();
		card2.toPrint();
		card3.toPrint();
		return;
	}
	public boolean isScat()
	//checks if the player has scat and returns true or false
	{
		reassignThings();
		if(points==31)
			return true;
		return false;
	}
	public CardClass getAndReplaceCard(CardClass nCard, int index)
	//gets a card and the index of the card that is being replaced and returns the card being replaced
	{
		reassignThings();
		CardClass toDiscard;
		if(index == 1)
		{
			toDiscard = card1;
			card1 = nCard;
			return toDiscard;
		}
		else if(index == 2)
		{
			toDiscard = card2;
			card2 = nCard;
			return toDiscard;
		}
		else if(index == 3)
		{
			toDiscard = card3;
			card3 = nCard;
			return toDiscard;
		}
		return null;
	}
	public String getPrimarySuit()
	{
		reassignThings();
		return primarySuit;
	}
	public int getPoints()
	{
		reassignThings();
		return points;
	}
	public void reassignThings()
	//used a lot just goes through and resets the points and suits just to make sure they are accurate at all times
	{
		points = assignPoints();
		primarySuit = assignPrimarySuit();
	}
	private int assignPoints()
	{
		int hPoints = 0;
		int cPoints = 0;
		int sPoints = 0;
		int dPoints = 0;

		String suit = card1.getSuit();
		if(suit.equalsIgnoreCase("hearts"))
			hPoints = hPoints + card1.getValue();
		else if(suit.equalsIgnoreCase("Clubs"))
			cPoints = cPoints + card1.getValue();
		else if(suit.equalsIgnoreCase("spades"))
			sPoints = sPoints + card1.getValue();
		else if(suit.equalsIgnoreCase("diamonds"))
			dPoints = dPoints + card1.getValue();

		suit = card2.getSuit();
		if(suit.equalsIgnoreCase("hearts"))
			hPoints = hPoints + card2.getValue();
		else if(suit.equalsIgnoreCase("Clubs"))
			cPoints = cPoints + card2.getValue();
		else if(suit.equalsIgnoreCase("spades"))
			sPoints = sPoints + card2.getValue();
		else if(suit.equalsIgnoreCase("diamonds"))
			dPoints = dPoints + card2.getValue();

		suit = card3.getSuit();
		if(suit.equalsIgnoreCase("hearts"))
			hPoints = hPoints + card3.getValue();
		else if(suit.equalsIgnoreCase("Clubs"))
			cPoints = cPoints + card3.getValue();
		else if(suit.equalsIgnoreCase("spades"))
			sPoints = sPoints + card3.getValue();
		else if(suit.equalsIgnoreCase("diamonds"))
			dPoints = dPoints + card3.getValue();




		if(hPoints>=cPoints && hPoints>=dPoints && hPoints >= sPoints)
			return hPoints;
		else if(cPoints>=hPoints && cPoints>=dPoints && cPoints>=sPoints)
			return cPoints;
		else if(dPoints>=hPoints && dPoints>=cPoints && dPoints>=sPoints)
			return dPoints;
		else if(sPoints>=hPoints && sPoints>=cPoints && sPoints>=dPoints)
			return sPoints;

		return 0;

	}
	private String assignPrimarySuit()
	{
		int hPoints = 0;
		int cPoints = 0;
		int sPoints = 0;
		int dPoints = 0;

		String suit = card1.getSuit();
		if(suit.equalsIgnoreCase("hearts"))
			hPoints = hPoints + card1.getValue();
		else if(suit.equalsIgnoreCase("Clubs"))
			cPoints = cPoints + card1.getValue();
		else if(suit.equalsIgnoreCase("spades"))
			sPoints = sPoints + card1.getValue();
		else if(suit.equalsIgnoreCase("diamonds"))
			dPoints = dPoints + card1.getValue();

		suit = card2.getSuit();
		if(suit.equalsIgnoreCase("hearts"))
			hPoints = hPoints + card2.getValue();
		else if(suit.equalsIgnoreCase("Clubs"))
			cPoints = cPoints + card2.getValue();
		else if(suit.equalsIgnoreCase("spades"))
			sPoints = sPoints + card2.getValue();
		else if(suit.equalsIgnoreCase("diamonds"))
			dPoints = dPoints + card2.getValue();

		suit = card3.getSuit();
		if(suit.equalsIgnoreCase("hearts"))
			hPoints = hPoints + card3.getValue();
		else if(suit.equalsIgnoreCase("Clubs"))
			cPoints = cPoints + card3.getValue();
		else if(suit.equalsIgnoreCase("spades"))
			sPoints = sPoints + card3.getValue();
		else if(suit.equalsIgnoreCase("diamonds"))
			dPoints = dPoints + card3.getValue();




		if(hPoints>=cPoints && hPoints>=dPoints && hPoints >= sPoints)
			return "hearts";
		else if(cPoints>=hPoints && cPoints>=dPoints && cPoints>=sPoints)
			return "clubs";
		else if(dPoints>=hPoints && dPoints>=cPoints && dPoints>=sPoints)
			return "diamonds";
		else if(sPoints>=hPoints && sPoints>=cPoints && sPoints>=dPoints)
			return "spades";

		return " ";

	}


}