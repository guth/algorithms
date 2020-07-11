import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Euler
{
	public static void main(String[] args) throws Exception
	{
		Euler e = new Euler();
		e.go();
	}

	public void go() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 1000;

		int p1Wins = 0;
		int p2Wins = 0;

		while(T-->0)
		{
			String line = br.readLine();
			String[] parts = line.split(" ");

			String[] p1 = new String[]{parts[0], parts[1], parts[2], parts[3], parts[4]};
			String[] p2 = new String[]{parts[5], parts[6], parts[7], parts[8], parts[9]};

			PokerHand h1 = new PokerHand(p1);
			PokerHand h2 = new PokerHand(p2);

			if(h1.compareTo(h2) > 0)
			{
				p1Wins++;
				System.out.println((1001-T) + ": " + h1 + " beat " + h2);
			}
			else
			{
				p2Wins++;
				System.out.println((1001-T) + ": " + h2 + " beat " + h1);
			}

		}
		System.out.println("p1 won " + p1Wins + " games.");
		System.out.println("p2 won " + p2Wins + " games.");
	}

	// H, S, D, C (Hearts, Spades, Diamonds, Clubs)
	// 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A
	// 8C
	public class PokerHand implements Comparable<PokerHand>
	{
		int NUM_CARDS = 13;
		int TWO = 0;
		int THREE = 1;
		int FOUR = 2;
		int FIVE = 3;
		int SIX = 4;
		int SEVEN = 5;
		int EIGHT = 6;
		int NINE = 7;
		int TEN = 8;
		int JACK = 9;
		int QUEEN = 10;
		int KING = 11;
		int ACE = 12;
		int[] all_cards = new int[]{TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
									JACK, QUEEN, KING, ACE};

		int NUM_SUITS = 4;
		int HEARTS = 0;
		int SPADES = 1;
		int DIAMONDS = 2;
		int CLUBS = 3;
		int[] all_suits = new int[]{HEARTS, SPADES, DIAMONDS, CLUBS};

		public int[] cards = new int[NUM_CARDS];
		int[] suits = new int[NUM_SUITS];
		String orig;
		public PokerHand(String[] hand)
		{
			orig = hand[0] + " " + hand[1] + " " + hand[2] + " " + hand[3] + " " + hand[4];

			for(String card : hand)
			{
				switch(card.charAt(0))
				{
					case 'T': cards[TEN]++; break;
					case 'J': cards[JACK]++; break;
					case 'Q': cards[QUEEN]++; break;
					case 'K': cards[KING]++; break;
					case 'A': cards[ACE]++; break;
					default: cards[card.charAt(0)-'2']++; break;
				}
				switch(card.charAt(1))
				{
					case 'H': suits[HEARTS]++; break;
					case 'S': suits[SPADES]++; break;
					case 'D': suits[DIAMONDS]++; break;
					case 'C': suits[CLUBS]++; break;
				}
			}
		}

		// High Card: Highest value card.
		public int highCard()
		{
			for(int i=cards.length-1; i>=0; i--)
			{
				if(cards[i] != 0)
					return i;
			}
			return -1;
		}

		// One Pair: Two cards of the same value.
		public boolean onePair()
		{
			for(int c : all_cards)
			{
				if(cards[c]==2)
					return true;
			}
			return false;
		}

		public int onePairValue()
		{
			for(int c : all_cards)
			{
				if(cards[c]==2)
					return c;
			}
			return 0;
		}

		// Two Pairs: Two different pairs.
		public boolean twoPairs()
		{
			for(int i=0;i<cards.length; i++)
			{
				for(int j=i+1; j<cards.length; j++)
				{
					if(cards[i]==2 && cards[j]==2)
						return true;
				}
			}
			return false;
		}

		public int twoPairsValue()
		{
			for(int i=0;i<cards.length; i++)
			{
				for(int j=i+1; j<cards.length; j++)
				{
					if(cards[i]==2 && cards[j]==2)
						return Math.max(i, j);
				}
			}
			return 0;
		}

		// Three of a Kind: Three cards of the same value.
		public boolean threeOfAKind()
		{
			for(int c : all_cards)
			{
				if(cards[c] == 3)
					return true;
			}
			return false;
		}

		public int threeOfAKindValue()
		{
			for(int c : all_cards)
			{
				if(cards[c] == 3)
					return c;
			}
			return 0;
		}

		// Straight: All cards are consecutive values.
		public boolean straight()
		{
			for(int i=0; i<cards.length-4; i++)
			{
				if(cards[i]==1 && cards[i+1]==1 && cards[i+2]==1 &&
					cards[i+3]==1 && cards[i+4]==1)
					return true;
			}
			return false;
		}

		// Flush: All cards of the same suit.
		public boolean flush()
		{
			for(int s : all_suits)
			{
				if(suits[s] == 5)
					return true;
			}
			return false;
		}

		// Full House: Three of a kind and a pair.
		public boolean fullHouse()
		{
			return threeOfAKind() && onePair();
		}

		public int fullHouseValue()
		{
			int val = 0;
			for(int c : all_cards)
			{
				if(cards[c] == 3)
				{
					val += c*100;
				}
				else if(cards[c]==2)
				{
					val += c*10;
				}
			}
			return val;
		}

		// Four of a Kind: Four cards of the same value.
		public boolean fourOfAKind()
		{
			for(int c : all_cards)
			{
				if(cards[c] == 4)
					return true;
			}
			return false;
		}

		public int fourOfAKindValue()
		{
			for(int c : all_cards)
			{
				if(cards[c] == 4)
					return c;
			}
			return 0;
		}

		// Straight Flush: All cards are consecutive values of same suit.
		public boolean straightFlush()
		{
			return straight() && flush();
		}

		// Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
		public boolean royalFlush()
		{
			if(cards[ACE]==1 && cards[KING]==1 && cards[QUEEN]==1 && cards[JACK]==1 && cards[TEN]==1)
			{
				for(int s : all_suits)
				{
					if(suits[s]==5)
						return true;
				}
			}
			return false;
		}

		public int handValue()
		{
			if(royalFlush())
				return 9000;
			else if(straightFlush())
				return 8000 + highCard();
			else if(fourOfAKind())
				return 7000 + fourOfAKindValue();
			else if(fullHouse())
				return 6000 + fullHouseValue();
			else if(flush())
				return 5000 + highCard();
			else if(straight())
				return 4000 + highCard();
			else if(threeOfAKind())
				return 3000 + threeOfAKindValue();
			else if(twoPairs())
				return 2000 + twoPairsValue();
			else if(onePair())
				return 1000 + onePairValue();
			else
				return highCard();
		}

		public int compareTo(PokerHand other)
		{
			int diff = this.handValue() - other.handValue();
			if(diff == 0)
				return compareToHighCards(other);
			return diff;
		}

		public int compareToHighCards(PokerHand other)
		{
			for(int i=cards.length-1; i>=0; i--)
			{
				int diff = this.cards[i] - other.cards[i];
				if(diff != 0)
					return diff;
			}
			return 0;
		}

		public String toString()
		{
			return orig;
		}
	}

	
}
