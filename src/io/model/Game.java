package io.model;

import java.util.ArrayList;

/**
 * The class for getters and setters currently.
 * @author bech2002
 *
 */
public class Game
{

		private ArrayList<String> gameRules;
		private int funRanking;
		private String gameTitle;
		
		/**
		 * Creates a new array list and call things. It's the controller.
		 */
		public Game()
		{
			gameRules = new ArrayList<String>();
			funRanking = 0;
			gameTitle = "";
		}
		
		/**
		 * Creates an array list for gamerules, funranking, and gametitle.
		 * @param gameRules
		 * @param funRanking
		 * @param gameTitle
		 */
		public Game(ArrayList<String> gameRules, int funRanking, String gameTitle)
		{
			this.gameRules = gameRules;
			this.funRanking = funRanking;
			this.gameTitle = gameTitle;
		}
		/**
		 * Gets the gamerules.
		 * @return
		 */
		public ArrayList<String> getGameRules()
		{
			return gameRules;
		}
		/**
		 * gets the funranking.
		 * @return
		 */
		public int getFunRanking()
		{
			return funRanking;
		}
		/**
		 * gets the gametitle.
		 * @return
		 */
		public String getGameTitle()
		{
			return gameTitle;
		}
		/**
		 * sets the gamerules.
		 * @param gameRules
		 */
		public void setGameRules(ArrayList<String> gameRules)
		{
			this.gameRules = gameRules;
		}
		/**
		 * sets the funranking.
		 * @param funRanking
		 */
		public void setFunRanking(int funRanking)
		{
			this.funRanking = funRanking;
		}
		/**
		 * sets the Gametitle.
		 * @param gameTitle
		 */
		public void setGameTitle(String gameTitle)
		{
			this.gameTitle = gameTitle;
		}
}
