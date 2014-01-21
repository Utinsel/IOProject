package io.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.controller.IOController;
import io.model.Game;

import javax.swing.*;
/**
 * The Panel controlling the app.
 * @author bech2002
 *
 */
public class IOPanel extends JPanel
{
	
	private IOController baseController;
	private JButton saveButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	private JButton loadButton;
	private JLabel gameCountLabel;
	
	/**
	 * The Conroller.
	 * @param baseController
	 */
	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;
		
		saveButton = new JButton("save the game stuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game Title:");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking:");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Game Rules:");
		baseLayout = new SpringLayout();
		loadButton = new JButton("load the game stuff");
		gameCountLabel = new JLabel("Current game count:");
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * Sets up the Panel and adds elements. 
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.MAGENTA);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);
		this.add(gameCountLabel);
	}
	
	/**
	 * Places everything in the window.
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.EAST, titleLabel, -328, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 6, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, -3, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 6, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, 19, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 22, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 128, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 6, SpringLayout.SOUTH, titleField);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 0, SpringLayout.WEST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, rulesLabel, -6, SpringLayout.NORTH, rulesArea);
		baseLayout.putConstraint(SpringLayout.SOUTH, rulesArea, -21, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, -30, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, 0, SpringLayout.EAST, rankingField);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 24, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.NORTH, gameCountLabel, 6, SpringLayout.SOUTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, gameCountLabel, 0, SpringLayout.WEST, rankingLabel);
	}
	
	/**
	 * Creates listeners for the application.
	 */
	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInformation(tempGame);
					gameCountLabel.setText("Current game count: " + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Try again with a valid number");
				}
			}
		});
	
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if( tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for(String currentRule : tempGame.getGameRules() )
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);
			
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it is in order.");
				}
			}
		});
	}
}
