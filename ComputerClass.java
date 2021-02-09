
public class ComputerClass
{
	private CardClass card1;
	private CardClass card2;
	private CardClass card3;
	private int points;
	private String primarySuit;

	public ComputerClass(CardClass a, CardClass b, CardClass c)
	{
		card1 = a;
		card2 = b;
		card3 = c;
		points = -1;

	}
	public boolean twoEvils(CardClass card1, CardClass card2)
	//gets two cards, sees which one if less points than the pther, returns true if card1, false if card2
	{
		if(card1.getValue() <= card2.getValue())
			return true;
		else
			return false;
		
	}
	public void printCards()
	//prints cards in correct format
	{
		card1.toPrint();
		card2.toPrint();
		card3.toPrint();
		return;
	}
	public boolean shouldKnock(int turnAmount)
	//receives the amount of turns that have gone by and uses that to determine if it should knock or not return boolean
	{
		reassignThings();
		
		if(points>15 && turnAmount<=0)
			return true;
		else if(points>18 && turnAmount >0 && turnAmount<3)
			return true;
		else if(turnAmount >= 3 || turnAmount <= 4 && points>20)
			return true;
		else if(points>22)
			return true;
		return false;
		
		
		
	}
	public boolean shouldTakeDiscard(CardClass nCard)
	//receives card in discard and returns true if you should take the discard, false otherwise
	{
		reassignThings();
		if((nCard.getSuit()).equals(primarySuit))
			return true;
		else 
		{
			int newPoints = nCard.getValue();
			if(card1.getSuit().equalsIgnoreCase(nCard.getSuit()))
				newPoints= newPoints + card1.getValue();
			if(card2.getSuit().equalsIgnoreCase(nCard.getSuit()))
				newPoints= newPoints + card2.getValue();
			if(card3.getSuit().equalsIgnoreCase(nCard.getSuit()))
				newPoints= newPoints + card3.getValue();
			
			if(newPoints>points)
				return true;
						
		}
		return false;
	}
	public int compareCard(CardClass nCard)
	//takes a a new card and compares it to the predertermined lesser card and returns an int of the card u should get rid of
	{
		reassignThings();
		int lesserCardI = lesserCard();
		
		CardClass lesserCard = new CardClass ("i", "i");
		if(lesserCardI == 1)
			lesserCard = card1;
		else if(lesserCardI ==2)
			lesserCard = card2;
		else if(lesserCardI == 3)
			lesserCard = card3;
		
		if(nCard.getSuit().equalsIgnoreCase(lesserCard.getSuit()))
		{
			int nPoints = nCard.getValue() + lesserCard.getValue();
			if(nPoints > points)
			{
				if(lesserCardI == 1)
				{
					boolean what = twoEvils(card2, card3);
					if(what==true)
						return 2;
					else
						return 3;
					
				}
				else if(lesserCardI ==2)
					{
					boolean what = twoEvils(card1, card3);
					if(what==true)
						return 1;
					else
						return 3;
					
					
					}
				else if(lesserCardI == 3)
				{
					boolean what = twoEvils(card1, card2);
					if(what==true)
						return 1;
					else
						return 2;
					
				}
			}
			
		}
		if(nCard.getSuit().equalsIgnoreCase(primarySuit))
		{
			
			
			if(lesserCard.getSuit().equals(primarySuit))
			{
				if(lesserCard.getValue()>=nCard.getValue())
					return -1;
				else
					return lesserCardI;
				
			}
			else
			{
				return lesserCardI;
			}
			
		}
		else
		{

			if(lesserCard.getSuit().equals(primarySuit))
			{
				return -1;
			}
			else
			{
				if(lesserCard.getValue()>= nCard.getValue())
					return -1;
				return lesserCardI;
			}
		}
		
		
	}
	public CardClass getAndReplaceCard(CardClass nCard, int index)
	//gets ne card and index of the card that is being replaced and returns card thqt is being replaced
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
		else if(index == -1)
		{
			return nCard;
		}
		return null;
	}
	public CardClass getCard(int index)
	//receives indecx and returns card at that index
	{
		reassignThings();
		if(index== 1)
			return card1;
		else if(index == 2)
			return card2;
		else if(index==3)
			return card3;
		return null;
	}
	public int lesserCard()
	//determines which ofthe three cards it has is the least useful
	{
		reassignThings();
		CardClass card = new CardClass("yeet", "yeet");
		int lowestValue = 12;
		int index = -1;
		int i = 1;
		while(i<4)
		{

			if(i==1)
				card = card1;
			else if (i ==2)
				card = card2;
			else if(i == 3)
				card = card3;

			if(!card.getSuit().equalsIgnoreCase(primarySuit) && card.getValue()<lowestValue)
			{
				lowestValue = card.getValue();
				index = i;
			}
			i++;
		}
		if(index == -1)
		{
			lowestValue = 12;
			index = -1;
			i = 1;
			while(i<4)
			{

				if(i==0)
					card = card1;
				else if (i ==1)
					card = card2;
				else if(i == 3)
					card = card3;

				if(card.getValue()<lowestValue)
				{
					lowestValue = card.getValue();
					index = i;
				}
				i++;
			}
		}
		return index;
	}
	public boolean isScat()
	//checks if the comp has scat and return a boolean
	{
		reassignThings();
		if(points==31)
			return true;
		return false;
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
	//used constantly and makes sue the points and prim suit are correct
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