package io.controller;

/**
 * The Runner class for the IO project
 * @author Berry Echols
 * @version 1.0 13/12/2013 Basic Runner structure.
 */
public class IORunner
{
	public static void main(String[] args)
	{
		IOController appController = new IOController();
		appController.start();
	}

}
