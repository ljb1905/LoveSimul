package code;


import java.io.*;

import java.net.*;

import gui.LoveFrame;
import javazoom.jl.player.Player;




public class Music extends Thread{

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop)
	{
		try{
			this.isLoop = isLoop;
			file = new File(LoveFrame.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public void close()
	{
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	
	@Override
	public void run()
	{
		try
		{
			do
			{
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		}catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}