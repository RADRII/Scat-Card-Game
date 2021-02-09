import java.lang.String;
import java.util.Random;
import java.util.ArrayList;

public class DeckClass
{
	static Random  rand = new Random ( );     
	private ArrayList<CardClass> deck;
	private ArrayList<CardClass> discardPile;


	public DeckClass()
	{
		deck = new ArrayList<CardClass>();
		discardPile = new ArrayList<CardClass>();
		String suit = "hearts";
		addASuitToDeck(deck, suit);
		suit = "clubs";
		addASuitToDeck(deck, suit);
		suit = "diamonds";
		addASuitToDeck(deck, suit);
		suit = "spades";
		addASuitToDeck(deck, suit);
		
		deck = shuffleDeck(deck);
		//discardPile.add(null);
		
		
	}
	public int size()
	{
		return deck.size();
	}
	public int findCard(CardClass toFind)
	{
		int i = 0;
		int index = -1;
		while(i<deck.size())
		{
			CardClass yeet = getCard(i);
			if(yeet.isSame(toFind) == true)
				index = i;
			i++;
		}
		return index;
	}
	public CardClass getDiscard()
	{
		return discardPile.get(0);
	}
	public CardClass getCard(int index)
	{
		return deck.get(index);
	}
	public CardClass getAndRemove(int index)
	//this method is for dealing out cards mostly, unless in debugger wwhere ur able to pick but most of the time it will just be at point 0
	{
		CardClass card = deck.get(index);
		deck.remove(index);
		return card;
	}
	public void addDiscard(CardClass toAdd)
	{
		discardPile.add(0,toAdd);
	}
	public CardClass removeDiscard()
	{
		CardClass thing = discardPile.get(0);
		discardPile.remove(0);
		return thing;
		
	}
	public void printDiscard()
	{
		(discardPile.get(0)).toPrint();
		//System.out.println("yes:");
	}
	public void createDiscard()
	{
		CardClass thing = deck.get(0);
		deck.remove(0);
		//System.out.println("size of discard is ");
		discardPile.add(thing);
		
		
	}
	public ArrayList<CardClass> shuffleDeck(ArrayList<CardClass> deck)
	{
		
		ArrayList<CardClass> newDeck = new ArrayList<CardClass>( );  
		
	
		int size = deck.size();
		boolean what = false;
		while(what == false)
		{
			int random = rand.nextInt(size);
			CardClass a = deck.get(random);
			deck.remove(random);
			size = deck.size();


			newDeck.add(a);

			what = deck.isEmpty();
			
		}
		return newDeck;
		
		
	}
	private void addASuitToDeck(ArrayList<CardClass> deck, String suit)
	{
		int i = 2;
		while(i<=14)
		{
			String nishno = "";
			if(i==2)
				nishno = "2";
			else if(i==3)
				nishno = "3";
			else if(i==4)
				nishno = "4";
			else if(i==5)
				nishno = "5";
			else if(i==6)
				nishno = "6";
			else if(i==7)
				nishno = "7";
			else if(i==8)
				nishno = "8";
			else if(i==9)
				nishno = "9";
			else if(i==10)
				nishno = "10";
			else if(i==11)
				nishno = "jack";
			else if(i==12)
				nishno = "queen";
			else if(i==13)
				nishno = "king";
			else if(i==14)
				nishno = "ace";
			
			
			CardClass card = new CardClass(nishno, suit);
			deck.add(card);
			i++;
		}
		return;
			
		}
		
	}

