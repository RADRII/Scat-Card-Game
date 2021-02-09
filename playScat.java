import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class playScat
{
	static Scanner sc = new Scanner(System.in);
	static Random  rand = new Random ( );   
	public static void main (String[] args)
	{
		System.out.println("Welcome to scat! What is your name?");
		String name = sc.next();

		int gamesPlayed = 0;
		int gamesWon = 0;

		int l = 0; //pointless
		while(l == 0)
		{
			String choice = getOptions(name);
			if(choice.equalsIgnoreCase("G"))
				printRules(name);
			else if(choice.equalsIgnoreCase("P"))
			{
				String whoWin = prepareToPlay(name);
				gamesPlayed++;
				if(whoWin.equalsIgnoreCase("P"))
					gamesWon++;
			}
			else if(choice.equals("q"))
				break;
			else
				System.out.println("Not a valid option");

		}
		goodbye(name, gamesPlayed, gamesWon);



	}

	public static ComputerClass dealCards(DeckClass deck)
	//deals out cards 4 the computers
	{
		CardClass yeet = deck.getAndRemove(0);
		CardClass yeet2 = deck.getAndRemove(0);
		CardClass yeet3 = deck.getAndRemove(0);

		ComputerClass player = new ComputerClass(yeet,yeet2,yeet3);
		return player;
	}
	public static PlayerClass dealCardsP(DeckClass deck)
	//deals out cards 4 player
	{
		CardClass yeet = deck.getAndRemove(0);
		CardClass yeet2 = deck.getAndRemove(0);
		CardClass yeet3 = deck.getAndRemove(0);

		PlayerClass player = new PlayerClass(yeet,yeet2,yeet3);
		return player;
	}
	public static void printCompCards(ComputerClass comp, DeckClass deck)
	//prints out the comp cards and discard card for debugger
	{
		System.out.println("Here are the computer's cards");
		System.out.println("Card 1");
		comp.getCard(1).toPrint();
		System.out.println("Card 2");
		comp.getCard(2).toPrint();
		System.out.println("Card 3");
		comp.getCard(3).toPrint();
		System.out.println("");
		System.out.print("Discard:");
		deck.printDiscard();
		System.out.println(" ");

		return;

	}
	public static void playerTurn(String name, PlayerClass player, DeckClass deck, String[] pIG, ArrayList<Boolean> feats, int debug)
	//MEthod contains everything needed for the player to take their turn, returns void
	{
		int plsno = 1;
		System.out.println(name + ", it's your turn!");
		while(plsno == 1)
		{
			String choice = printCardAndOptions(player, deck, feats, debug);
			if(choice.equalsIgnoreCase("D"))
			{
				System.out.println("You drew from the discard.");
				CardClass picked = deck.removeDiscard();
				discardPlayer(deck,player,picked);
				plsno = 2;
			}
			else if(choice.equalsIgnoreCase("G"))
			{
				System.out.println("You drew from the deck.");
				CardClass picked = deck.getAndRemove(0);
				discardPlayer(deck,player,picked);
				plsno = 10;
			}
			else if(choice.equalsIgnoreCase("C") && debug == 1)
			{
				pickDeckCard(player, deck);
				plsno = 420;

			}
			else if(choice.equalsIgnoreCase("K") && feats.get(0) == false)
			{
				System.out.println("You have knocked.");

				if(feats.isEmpty() == false)
					feats.remove(0);
				feats.add(true);
				plsno = 828;
			}
			else
				System.out.println("Not a valid option");
		}

	}
	public static int printCards(PlayerClass player, CardClass picked)
	//prints out the card options the player has, returns what they chose
	{
		System.out.println("Here are your cards");
		System.out.println("Card 1");
		player.getCard(1).toPrint();
		System.out.println("Card 2");
		player.getCard(2).toPrint();
		System.out.println("Card 3");
		player.getCard(3).toPrint();
		System.out.println("Card 4");
		picked.toPrint();

		System.out.println("Which would you like to discard(1-4)?");
		int choice = sc.nextInt();
		return choice;
	}
	public static void discardPlayer(DeckClass deck, PlayerClass player, CardClass picked)
	//receives int of card they want to discard and the card they picked and switches the two.
	{

		int toRemove = printCards(player, picked);
		if(toRemove == 0)
			deck.addDiscard(picked);
		else if(toRemove == 1)
		{
			CardClass toDiscard = player.getAndReplaceCard(picked, 1);
			deck.addDiscard(toDiscard);
		}
		else if(toRemove == 2)
		{
			CardClass toDiscard = player.getAndReplaceCard(picked, 2);
			deck.addDiscard(toDiscard);
		}
		else if(toRemove == 3)
		{
			CardClass toDiscard = player.getAndReplaceCard(picked, 3);
			deck.addDiscard(toDiscard);
		}
		return;
	}
	public static String  printCardAndOptions(PlayerClass player, DeckClass deck, ArrayList<Boolean> feats, int debug)
	//prints the options the player has for their turn
	{
		System.out.println("Your cards are: ");
		player.printCards();
		System.out.println("");
		System.out.println("Discard Pile:");
		deck.printDiscard();



		System.out.println("Would you like to: ");
		System.out.println("");
		System.out.print("D. Pick up the card in the discard pile, which is the ");
		deck.printDiscard();
		System.out.println("G. Get a card from the top of the deck");
		if(debug == 1)
			System.out.println("C: Chooose any card from the deck");
		if(feats.get(0) == false)
			System.out.println("K. Knock");
		String choice = sc.next();
		return choice;

	}
	public static void compTurn(ComputerClass comp, DeckClass deck, ArrayList<Boolean> feats, int amountTurns, int debug)
	//controls what the comp will do for its turn, receives everything needed
	{	
		if(debug==1)
		{
			printCompCards(comp,deck);
		}
		//System.out.println(comp.shouldKnock(amountTurns));
		//System.out.println(amountTurns);

		if(comp.shouldKnock(amountTurns) == true && feats.get(0) == false)
		{
		
			System.out.println("The computer has knocked");
			Boolean ppp = true;
			if(feats.isEmpty() == false)
				feats.remove(0);
			feats.add(ppp);
		}
		else if(comp.shouldTakeDiscard(deck.getDiscard()) == true)
		{
			System.out.println("The computer took the discard card");
			CardClass nyeh = deck.removeDiscard();
			int toD = comp.compareCard(nyeh);
			CardClass toDiscard = comp.getAndReplaceCard(nyeh, toD);
			deck.addDiscard(toDiscard);
			//System.out.println("");
			System.out.print("The Computer discarded the " );
			toDiscard.toPrint();
		}
		else
		{
			System.out.println("The computer took from the deck");
			CardClass deckCard = deck.getAndRemove(0);
			if(debug == 1)
			{
				System.out.println("The computer picked up the ");
				deckCard.toPrint();
			}
			int least = comp.compareCard(deckCard);
			if(least == -1)
			{
				deck.addDiscard(deckCard);
				//System.out.println("");
				System.out.print("The Computer discarded the " );
				deckCard.toPrint();
			}
			else if(least == 1)
			{

				CardClass yeet = comp.getAndReplaceCard(deckCard, 1);
				//System.out.println("");
				System.out.print("The Computer discarded the " );
				deck.addDiscard(yeet);
				yeet.toPrint();
			}
			else if(least == 2)
			{
				CardClass yeet = comp.getAndReplaceCard(deckCard, 2);
				//System.out.println("");
				System.out.print("The Computer discarded the " );
				deck.addDiscard(yeet);
				yeet.toPrint();
			}
			else if(least == 3)
			{	//System.out.println("");
				CardClass yeet = comp.getAndReplaceCard(deckCard, 3);
				System.out.print("The Computer discarded the " );
				deck.addDiscard(yeet);
				yeet.toPrint();
			}
		}
		return;
	}
	public static void playGame(int debug, GameClass game, String name, String[] peopleInGame, PlayerClass player, ComputerClass comp1, ComputerClass comp2, ComputerClass comp3)
	//receives everything needed to play a game, controls the dealing, the turns, and the score counting
	{
		DeckClass deck = new DeckClass();
		System.out.println("Dealing out cards");
		
		if(peopleInGame[0].equalsIgnoreCase("PLAYER"))
			player = dealCardsP(deck);
		if(peopleInGame[1].equalsIgnoreCase("Comp1"))
			comp1 = dealCards(deck);
		if(peopleInGame[2].equalsIgnoreCase("Comp2"))
			comp2 = dealCards(deck);
		if(peopleInGame[3].equalsIgnoreCase("Comp3"))
			comp3 = dealCards(deck);
		deck.createDiscard();


		ArrayList<Boolean> feats = new ArrayList<Boolean>(); //1st = knocked 
		int roundsLeft = 4;
		feats.add(false);

		int whoScat = -1;

		int counting = 0;
		int amountTurns = 0;
		int whoTurn = rand.nextInt(4);
		System.out.println("Scores of those still in the game.");
		printScore(game, peopleInGame, name);


		while(roundsLeft!=0)
		{


			if(whoTurn ==0)
			{

				if(peopleInGame[0].equalsIgnoreCase("PLAYER"))
				{
					playerTurn(name, player, deck, peopleInGame, feats,  debug);
					if(player.isScat() == true)
					{
						System.out.println("You got scat!");
						whoScat = 0;
						break;
					}
					

					System.out.println(" ");
					System.out.println(" ");
				}
				if(feats.get(0) == true)
					roundsLeft--;
				whoTurn++;


			}
			else if(whoTurn == 1)
			{
				if(peopleInGame[1].equalsIgnoreCase("Comp1"))
				{
					System.out.println("It's Computer 1's turn!");
					compTurn(comp1, deck, feats, amountTurns, debug);

					if(comp1.isScat()==true)
					{
						System.out.println("Computer 1 got scat!)");
						whoScat =1;
						break;
					}
					
				
					System.out.println(" ");
					System.out.println(" ");
					
				}
				if(feats.get(0) == true)
					roundsLeft--;
				whoTurn++;


			}
			else if(whoTurn == 2)
			{
				if(peopleInGame[2].equalsIgnoreCase("comp2"))
				{
					System.out.println("It's Computer 2's turn!");
					compTurn(comp2, deck, feats, amountTurns, debug);
					if(comp2.isScat()==true)
					{
						System.out.println("Computer 2 got scat!)");
						whoScat =2;
						break;
					}
					System.out.println(" ");
					System.out.println(" ");
				}
				if(feats.get(0) == true)
					roundsLeft--;
				whoTurn++;

			}
			else if(whoTurn == 3)
			{
				if(peopleInGame[3].equalsIgnoreCase("comp3"))
				{
					System.out.println("It's Computers 3's turn!");
					compTurn(comp3, deck, feats, amountTurns, debug);
					if(comp3.isScat()==true)
					{
						System.out.println("Computer 3 got scat!)");
						whoScat =3;
						break;
					}
					System.out.println(" ");
					System.out.println(" ");
				}
				if(feats.get(0) == true)
					roundsLeft--;
				whoTurn =0;

			}
			counting++;
			if((counting % 4) == 0)
				amountTurns++;
			
		}
		//HERE WWE COUNT THE POINTS

		if(peopleInGame[0].equalsIgnoreCase("Player"))
			System.out.println("Player: " + player.getPoints());
		if(peopleInGame[1].equalsIgnoreCase("Comp1"))
			System.out.println("Computer 1: " + comp1.getPoints());
		if(peopleInGame[2].equalsIgnoreCase("Comp2"))
			System.out.println("Computer 2: " + comp2.getPoints());
		if(peopleInGame[3].equalsIgnoreCase("Comp3"))
			System.out.println("Computer 3: " + comp3.getPoints());
		if(whoScat!=-1)
		{
			accountScat(game, whoScat);
		}
		else
		{
		
			normalDownPoints(game, player, comp1, comp2, comp3, peopleInGame);

		}

		
		
		System.out.println("Ready to continue? Press enter, may need to do it twice.");
		sc.nextLine();
		sc.nextLine();
		return;
	}
	static public CardClass makeCard()
	//makes a card and returns it
	{
		System.out.println("Enter a nishno");
		String nishno = sc.next();
		System.out.println("Enter a suit");
		String suit = sc.next();
		CardClass wow = new CardClass (nishno,suit);
		return wow;
	}
	static public void pickDeckCard(PlayerClass player, DeckClass deck)
	//for debugger, lets the player choose any card from the deck and shows hthem the entire deck if their card isnt in there
	{int we = -1;
	while(we==-1)
	{
		CardClass wow = makeCard();
		int index = deck.findCard(wow);

		if(index == -1)
		{
			System.out.println("Card not found, would you like to see what's in the deck before choosing a new card (y/n)?");
			String choice = sc.next();
			if(choice.equalsIgnoreCase("y"))
			{
				int i = 0;
				while(i<deck.size())
				{
					deck.getCard(i).toPrint();
					i++;
				}
			}
		}
		else
		{
			System.out.println("You are choosing this card!");

			discardPlayer(deck,player,wow);
			break;

		}


	}
	return;
	}
	static public void printArray(String numbers[])
	//check the name
	{
		int size=numbers.length;
		for (int i=0;i<size;i++)
			System.out.print(numbers[i] + " "); 

	}
	public static void printScore(GameClass game, String[] pIG, String name)
	//prints out the score, not the score for the round but how many lives they have
	{
		if(pIG[0].equalsIgnoreCase("Player"))
			System.out.println(name + " : " + game.getPPoints());
		if(pIG[1].equalsIgnoreCase("comp1"))
			System.out.println("Computer 1 : " + game.getC1());
		if(pIG[2].equalsIgnoreCase("comp2"))
			System.out.println("Computer 2 : " + game.getC2());
		if(pIG[3].equalsIgnoreCase("comp3"))
			System.out.println("Computer 3 : " + game.getC3());
		System.out.println("   ");
		return;
	}
	public static void normalDownPoints(GameClass game, PlayerClass player, ComputerClass comp1, ComputerClass comp2, ComputerClass comp3, String[] pIG)
	//if no one got scat it comes here, and it prints out the rounds score and finds who hasthe lowes and brngs their score down
	{
		int pP= 0;
		int cP1= 0;
		int cP2=0;
		int cP3=0;
		if(pIG[0].equalsIgnoreCase("PLayer"))
			pP = player.getPoints();
		if(pIG[1].equalsIgnoreCase("comp1"))
			cP1 = comp1.getPoints();
		if(pIG[2].equalsIgnoreCase("comp2"))
			cP2 = comp2.getPoints();
		if(pIG[3].equalsIgnoreCase("comp3"))
			cP3 = comp3.getPoints();


		ArrayList<String> yeey = new ArrayList<String>();
		int lowest = 1000;


		if(pP<lowest && pIG[0].equalsIgnoreCase("player"))
		{
			lowest = pP;
			yeey = new ArrayList<String>();
			yeey.add("Player");
		}
		else if(pP == lowest&& pIG[0].equalsIgnoreCase("player"))
			yeey.add("Player");

		if(cP1<lowest && pIG[1].equalsIgnoreCase("Comp1"))
		{
			lowest = cP1;
			yeey = new ArrayList<String>();
			yeey.add("Computer 1");
		}
		else if(cP1 == lowest&& pIG[1].equalsIgnoreCase("Comp1"))
			yeey.add("Computer 1");

		if(cP2<lowest&& pIG[2].equalsIgnoreCase("Comp2"))
		{
			lowest = cP2;
			yeey = new ArrayList<String>();
			yeey.add("Computer 2");
		}
		else if(cP2 == lowest&& pIG[2].equalsIgnoreCase("Comp2"))
			yeey.add("Computer 2");

		if(cP3<lowest&& pIG[3].equalsIgnoreCase("Comp3"))
		{
			lowest = cP3;
			yeey = new ArrayList<String>();
			yeey.add("Computer 3");
		}
		else if(cP3 == lowest&& pIG[3].equalsIgnoreCase("Comp3"))
			yeey.add("Computer 3");

		//now we know whose lost,  and so we move to actually taking away points

		if(yeey.indexOf("Player") != -1)
			game.downPoints(0);
		if(yeey.indexOf("Computer 1") != -1)
			game.downPoints(1);
		if(yeey.indexOf("Computer 2") != -1)
			game.downPoints(2);
		if(yeey.indexOf("Computer 3") != -1)
			game.downPoints(3);

		return;
	}
	public static void accountScat(GameClass game, int whoScat)
	//receives the int of who gt scat and brings everyone elses points down
	{

		if(whoScat==0)
		{
			game.downPoints(1);
			game.downPoints(2);
			game.downPoints(3);
		}
		else if(whoScat ==1)
		{
			game.downPoints(0);
			game.downPoints(2);
			game.downPoints(3);
		}
		else if(whoScat==2)
		{
			game.downPoints(0);
			game.downPoints(1);
			game.downPoints(3);

		}
		else if(whoScat==3)
		{
			game.downPoints(0);
			game.downPoints(1);
			game.downPoints(2);

		}
		return;
	}
	public static String prepareToPlay(String name)
	//asks the user how they want to play, how many points + debug or no. Creates the while loop of all the round in a game
	{
		int lol = 0;
		int points=0;
		while(lol==0)
		{
			System.out.println(name + ", how many points do you want people to begin with?");
			points = sc.nextInt();
			if(points>=-1)
				lol=777;
			else
				System.out.println("You cannot start out with points below -1");
		}

		GameClass game = new GameClass(points);

		lol = 0;
		int debug = 0;
		while(lol==0)
		{
			System.out.println("Would you like to run in debug mode (1=yes, 2=no)?");
			debug = sc.nextInt();
			if(debug ==1 || debug ==2)
				lol=777;
			else
				System.out.println("Please answer with either 1 or 2");
		}



		int yay = -100;

		String peopleInGame[] = new String [4];


		PlayerClass player = new PlayerClass(null,null,null);
		ComputerClass comp1 = new ComputerClass(null,null, null);
		ComputerClass comp2=new ComputerClass(null,null, null);
		ComputerClass comp3=new ComputerClass(null,null, null);


		//creating the array of string bc i cant do one of player class and comp class

		peopleInGame[0] = "Player";
		peopleInGame[1] = "Comp1";
		peopleInGame[2] = "Comp2";
		peopleInGame[3] = "Comp3";

		while(game.someoneWon(peopleInGame,yay) == false)
		{

			playGame(debug,game, name, peopleInGame, player, comp1, comp2, comp3);
			game.checkAndGetOut(peopleInGame);

			yay = 1;
		}
		printScore(game, peopleInGame, name);
		String boi = game.whoWon(peopleInGame);
		if(boi!= null)
		System.out.println("The winner is " + boi);
		else
			System.out.println("No one won!");

		if(game.didPWin(peopleInGame)==true)
			return "P";
		return "N";
	}
	public static void printRules(String name)
	{
		System.out.println("Scat is a card game played with four people");
		System.out.println("Each person is dealt three cards and each turn you can either pick a card from the discard pile, pick a card from the deck,call scat,  or knock");
		System.out.println("After each turn the person much then choose to discard a card");
		System.out.println("The aim of the game is to not have the least amount of points");
		System.out.println("Each card has a point value");
		System.out.println("2=2 pts, 3=3pts, ... 10=10 points, Jack, Queen or King = 10 points, ace = 11 points. The value of a hand is the sum of cards OF THE SAME SUIT. The hand's value will be the highest possible total (choose the suit which has the highest card total).");
		System.out.println("Once you think you have the least amount of points out of everyone you can knock");
		System.out.println("Knocking causes the game to end after everyone else has gone one more time and then everyones points are totalled and the person with the least points loses a game-points");
		System.out.println("The game ends once someone is out of game points and the person with the most game points left wins");
		System.out.println("If you get scat (a total of 31 points) the game ends right there and everyone loses a game-point except for you.");


	}
	public static void goodbye(String name, int gamesPlayed, int gamesWon)
	{
		System.out.println(name + ", you played " + gamesPlayed + " games and won " + gamesWon + " times.");
		System.out.println("Bye.");


	}
	public static String getOptions(String name)
	{
		System.out.println(name + ", would you like to:");
		System.out.println("G. Get Rules");
		System.out.println("P. Play the game");
		System.out.println("Q. Quit");
		String choice = sc.next();
		return choice;
	}
}