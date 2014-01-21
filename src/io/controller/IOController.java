package io.controller;

import io.model.Game;
import io.view.IOFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Controller class for the IO project
 * 
 * @author Berry Echols
 * 
 */
public class IOController
{
	/**
	 * Reference to the GUI Frame for the project.
	 */
	private IOFrame appFrame;

	/**
	 * All games that can be retrieved from the save file.
	 */
	private ArrayList<Game> projectGames;

	/**
	 * Constructor for the Controller.
	 */
	public IOController()
	{
		projectGames = new ArrayList<Game>();
	}

	/**
	 * Getter for the ProjectGames.
	 * 
	 * @return
	 */
	public ArrayList<Game> getProjectGames()
	{
		return projectGames;
	}

	/**
	 * Start method for the app.
	 */
	public void start()
	{
		appFrame = new IOFrame(this);
	}

	/**
	 * Reads a text file to set the text in the text areas.
	 * 
	 * @return
	 */
	public Game readGameInformation()
	{
		String fileName = "save file.txt"; // Without a path it will look to the
											// directory of the project!
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		Game currentGame = null;
		int gameRanking = 0;
		String gameTitle = "";
		ArrayList<String> gameRules = new ArrayList<String>();

		try
		// Will look to the directory of the project!
		{
			fileReader = new Scanner(currentSaveFile);
			gameTitle = fileReader.nextLine();
			gameRanking = fileReader.nextInt();
			while (fileReader.hasNext())
			{
				gameRules.add(fileReader.nextLine());
			}

			currentGame = new Game(gameRules, gameRanking, gameTitle);
			fileReader.close();
		}
		catch (FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}

		return currentGame;
	}

	/**
	 * Reads the information of all games.
	 * 
	 * @return
	 */
	private String readAllGameInformation()
	{
		String fileContents = "";
		String fileName = "save file.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;

		try
		{
			fileReader = new Scanner(currentSaveFile);
			while (fileReader.hasNext())
			{
				fileContents += fileReader.nextLine();
			}
			fileReader.close();
		}
		catch (FileNotFoundException fileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, fileDoesNotExist.getMessage());
		}

		return fileContents;
	}

	/**
	 * Converts the collected text from the file and converts it to games.
	 * 
	 * @param currentInfo
	 */
	private void convertTextToGames(String currentInfo)
	{
		String[] gameChunks = currentInfo.split(";");

		for (String currentBlock : gameChunks)
		{
			int currentIndex = currentBlock.indexOf("\n");
			String title = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String ranking = currentBlock.substring(currentIndex + 1, nextIndex);
			String rules = currentBlock.substring(nextIndex + 1);
			Game currentGame = makeGameFromInput(title, ranking, rules);
			projectGames.add(currentGame);
		}
	}

	/**
	 * Picks a random game from the save file.
	 * 
	 * @return
	 */
	public Game pickRandomGameFromSaveFile()
	{
		Game currentGame = null;

		String allInfo = readAllGameInformation();
		convertTextToGames(allInfo);
		int randomIndex = (int) (Math.random() * (double) projectGames.size());
		currentGame = projectGames.get(randomIndex);

		return currentGame;
	}

	/**
	 * Makes a game based on the read data.
	 * 
	 * @param gameTitle
	 * @param gameRanking
	 * @param gameRules
	 * @return
	 */
	public Game makeGameFromInput(String gameTitle, String gameRanking, String gameRules)
	{
		Game currentGame = new Game();
		currentGame.setGameTitle(gameTitle);

		if (checkNumberFormat(gameRanking))
		{
			currentGame.setFunRanking(Integer.parseInt(gameRanking));
		}
		else
		{
			return null;
		}

		String[] temp = gameRules.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();

		for (String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		currentGame.setGameRules(tempRules);

		return currentGame;
	}

	/**
	 * Test wether or not the string is a number.
	 * 
	 * @param toBeParsed
	 * @return
	 */
	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;

		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame, "Please try again with an actual number for the ranking.");
		}
		return isNumber;
	}

	/**
	 * Saves a Game object to the drive and separates each Game object with a
	 * semicolon.
	 * 
	 * @param currentGame
	 */
	public void saveGameInformation(Game currentGame)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";
		projectGames.add(currentGame);

		try
		{
			gameWriter = new PrintWriter(saveFile); // Creates the save file.

			gameWriter.println(currentGame.getGameTitle());
			gameWriter.println(currentGame.getFunRanking());
			for (int count = 0; count < currentGame.getGameRules().size(); count++)
			{
				gameWriter.println(currentGame.getGameRules().get(count));
			}

			gameWriter.println(";"); // Used to delineate each individual game
										// in the save file.

			gameWriter.close(); // Required to prevent corruption of data and
								// maintain security of the file.
		}
		catch (FileNotFoundException noFileExists)
		{
			JOptionPane.showMessageDialog(appFrame, "Could not create the save file. :(");
			JOptionPane.showMessageDialog(appFrame, noFileExists.getMessage());
		}
	}
}
