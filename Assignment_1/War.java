// CS 0445 Spring 2013
// Assignment 1
// Created by Reggie Barnett
// rdb26@pitt.edu

public class War{

	private MultiDS<Card> player1,p1Discard;
	private MultiDS<Card> player2,p2Discard;
	private int rounds;
	private boolean gameOver;
	
	
//Main method, reads in number of rounds
	public static void main(String [] args){
	
	int numRounds = Integer.parseInt(args[0]);

		War war = new War(numRounds);	
	}

//This method creates the deck
	public MultiDS createDeck(){ 
		MultiDS deck = new MultiDS(52);
		for(Card.Suits s: Card.Suits.values()){
			for(Card.Ranks r: Card.Ranks.values()){
				deck.addItem(new Card(s,r));
			}
		}
		return deck;
	}
	
//This method deals the cards out to each player	
	public void dealCards(MultiDS<Card> deck){
		while(player2.size() != 26){
			player1.addItem(deck.removeItem());
			player2.addItem(deck.removeItem());
		}
	}
	
/*This method checks whether or not the players hand is empty. this was mainly for error check as I kept on 
getting issues with this throughout writing the program*/
	public void capacity(){
		if(player1.empty()){
			int size = p1Discard.size();
			for(int i = 0; i < size; i++){
				player1.addItem(p1Discard.removeItem());
			}				
			System.out.println("Getting and shuffling the pile for player 1");
			player1.shuffle();
		}
		if(player2.empty()){
			int size = p2Discard.size();
			for(int i = 0; i < size; i++){
				player2.addItem(p2Discard.removeItem());
			}
				
			System.out.println("Getting and shuffling the pile for player 2");
			player2.shuffle();
		}

	}
	
	//This method determines when the game is over and declares the winner
	public boolean endGame(int currentRound){
		if(currentRound > rounds)
		{
			System.out.println("After "+(currentRound-1)+" rounds here is the status:");
			System.out.println("	Player 1 has "+(player1.size()+p1Discard.size())+" cards");
			System.out.println("	Player 2 has "+(player2.size()+p2Discard.size())+" cards");
			if((player1.size()+p1Discard.size())>(player2.size()+p2Discard.size())){
				System.out.println("Player 1 Wins!");
			}
			else if((player1.size()+p1Discard.size())<(player2.size()+p2Discard.size())){
				System.out.println("Player 2 Wins!");
			}
			else
				System.out.println("It's a tie!");
			return true;
		}
		else if(player1.size() == 0 && p1Discard.size() == 0){
			System.out.println("	Player 1 is out of cards");
			System.out.println("	Player 2 wins!");
			return true;
		}
		else if(player2.size() == 0 && p2Discard.size() == 0){
			System.out.println("	Player 2 is out of cards");
			System.out.println("	Player 1 wins!");
			return true;
		}
		else
			return false;
	}
	
	//This method runs the game once the cards are dealt
	public void gameLoop(){
		int currentRound = 1;
		while(!gameOver){
			
			capacity();
			
			Card p1Card = player1.removeItem();
			Card p2Card = player2.removeItem();
			
			if(p1Card.compareTo(p2Card)> 0){
				p1Discard.addItem(p1Card);
				p1Discard.addItem(p2Card);
				System.out.println("Player 1 wins: "+p1Card.toString()+" beats "+p2Card.toString());
			}
			else if(p1Card.compareTo(p2Card)< 0){
				p2Discard.addItem(p1Card);
				p2Discard.addItem(p2Card);
				System.out.println("Player 2 wins: "+p1Card.toString()+" loses to "+p2Card.toString());
			}
			else{
				tie(p1Card, p2Card, currentRound);
			}
			if(!gameOver)
				gameOver = endGame(currentRound);
			currentRound++;
		}
	}
	
	//This method is used whenever a tie occurs in game
	public void tie(Card p1Card, Card p2Card, int currentRound){
		System.out.println("WAR: "+p1Card.toString()+" ties "+p2Card.toString());
		MultiDS<Card> pile = new MultiDS<Card>(52);
		pile.addItem(p1Card);
		pile.addItem(p2Card);
		capacity();
		p1Card = player1.removeItem();
		p2Card = player2.removeItem();
		System.out.println("		Player 1: "+p1Card.toString()+" and Player 2: "+p2Card.toString()+" are at risk!");
		pile.addItem(p1Card);
		pile.addItem(p2Card);
		
		//If a tie occurs during war
		do{
			capacity();
			p1Card = player1.removeItem();
			p2Card = player2.removeItem();
			pile.addItem(p1Card);
			pile.addItem(p2Card);
			if(p1Card.compareTo(p2Card) == 0)
				System.out.println("\t" +p1Card.toString()+" ties "+p2Card.toString());
			gameOver = endGame(currentRound);
		}while(!gameOver && p1Card.compareTo(p2Card) == 0);
		
		if(p1Card.compareTo(p2Card)> 0){
			int size = pile.size();
			for(int i = 0; i < size;i++){
				p1Discard.addItem(pile.removeItem());
			}
			System.out.println("Player 1 wins: "+p1Card.toString()+" beats "+p2Card.toString());
		}
		else{
			int size = pile.size();
			for(int i = 0; i < size;i++){
				p2Discard.addItem(pile.removeItem());
			}
			System.out.println("Player 2 wins: "+p1Card.toString()+" loses to "+p2Card.toString());
		}
	
	}
	
	
	//War method, game is ran here
	public War(int numRounds){
		rounds = numRounds;
		gameOver = false;
		MultiDS deck = createDeck();
		deck.shuffle();
		player1= new MultiDS(52);
		p1Discard= new MultiDS(52);
		player2= new MultiDS(52);
		p2Discard= new MultiDS(52);
		dealCards(deck);
		gameLoop();
	}

}