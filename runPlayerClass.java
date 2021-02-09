import java.util.Scanner;

public class runPlayerClass
{
	static Scanner sc = new Scanner(System.in);
	public static void main (String[] args)
	{
		System.out.println("Enter a nishno then a suit three times");
		String nishno1 = sc.next();
		String suit1 = sc.next();
		String nishno2 = sc.next();
		String suit2 = sc.next();
		String nishno3 = sc.next();
		String suit3 = sc.next();
		
		CardClass card1 = new CardClass(nishno1, suit1);
		CardClass card2 = new CardClass(nishno2, suit2);
		CardClass card3 = new CardClass(nishno3, suit3);
		
		PlayerClass player = new PlayerClass(card1, card2, card3);
		System.out.println("Check to see if isScat works 1 or 2");
		int fff = sc.nextInt();
		if(fff== 1)
		{
			System.out.println(player.getPoints());
			boolean uuu = player.isScat();
			System.out.println(uuu);
		}
		player.reassignThings();
		System.out.println("checking to see if the primary suit and the points are right");
		System.out.println("NIshno: " + player.getPoints());
		System.out.println("suit: " + player.getPrimarySuit());
		
		System.out.println("Enter a nishno and suit 2 replace the first card");
		String nishno = sc.next();
		String suit = sc.next();
		CardClass eh = new CardClass(nishno,suit);
		
		CardClass yeet = player.getAndReplaceCard(eh, 1);
		System.out.println("hot rid of");
		yeet.toPrint();
		player.reassignThings();
		System.out.println("checking to see if the primary suit and the points are right");
		System.out.println("NIshno: " + player.getPoints());
		System.out.println("suit: " + player.getPrimarySuit());
		
		
	}
	
	}