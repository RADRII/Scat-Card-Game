import java.util.Scanner;

public class runComputerClass
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
		
		ComputerClass comp = new ComputerClass(card1,card2,card3);
		comp.reassignThings();
		System.out.println("the primary suit and points");
		System.out.println(comp.getPoints() + comp.getPrimarySuit());
		
		System.out.println("Checking to see what the lesser card is");
		int index = comp.lesserCard();
		comp.getCard(index).toPrint();
		
		System.out.println("Enter a nishno then a suit");
		String nNishno = sc.next();
		String nSuit = sc.next();
		CardClass nCard = new CardClass(nNishno,nSuit);
		
		System.out.println("Checking to see wether the lesser card found previously is better or worse than new card");
		int yay = comp.compareCard(nCard);
		System.out.println(yay);
		
		System.out.println("Should u take the new card " + comp.shouldTakeDiscard(nCard));
		
	
		
	}
	
	}