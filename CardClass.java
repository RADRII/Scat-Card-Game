import java.lang.String;
public class CardClass
{
	private String suit;
	private String nishno;
	private int value;
	
	public CardClass(String n, String s)
	{
		nishno = n;
		suit = s;
		value = findValue(n);

	} // MyPhoneBook constructor
	public boolean isSame(CardClass other)
	{
		//checks if the card received is the same as what this card is and returns true/false depending
		int samePoints = 0;
		if(nishno.equals(other.getNishno()))
			samePoints++;
		if(suit.equals(other.getSuit()))
			samePoints++;
		if(samePoints==2)
			return true;
		return false;
		
	}
	public int getValue()
	//ya get the value
	{
		return value;
	}
	public void toPrint()
	//ya print the card in proper format
	{
		System.out.println(nishno + " of " + suit);
	}
	public String getNishno()
	//ya get the nishno
	{
		return nishno;
	}
	public String getSuit()
	//ya get the suit
	{
		return suit;
	}
	public int findValue(String nishno)
	//Finds value of the card and returns it
	{
		if(nishno.equalsIgnoreCase("1"))
			return 1;
		else if(nishno.equalsIgnoreCase("2"))
			return 2;
		else if(nishno.equalsIgnoreCase("3"))
			return 3;
		else if(nishno.equalsIgnoreCase("4"))
			return 4;
		else if(nishno.equalsIgnoreCase("5"))
			return 5;
		else if(nishno.equalsIgnoreCase("6"))
			return 6;
		else if(nishno.equalsIgnoreCase("7"))
			return 7;
		else if(nishno.equalsIgnoreCase("8"))
			return 8;
		else if(nishno.equalsIgnoreCase("9"))
			return 9;
		else if(nishno.equalsIgnoreCase("10"))
			return 10;
		else if(nishno.equalsIgnoreCase("jack"))
			return 10;
		else if(nishno.equalsIgnoreCase("queen"))
			return 10;
		else if(nishno.equalsIgnoreCase("king"))
			return 10;
		else if(nishno.equalsIgnoreCase("ace"))
			return 11;
		return 0;
	}
}