package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import entities.User;
import entities.UserRecord;
import model.data.Level;

//if we not running a server but we want to run on CLI and no the GUI
public class MyView extends Observable implements View {
	
	private BufferedReader reader;
	private PrintWriter writer;
	private boolean stop = false;

	//printing a String function
	@Override
	public void displayMessage(String string)
	{
		System.out.println(string);
	}

	//displaying a level to the user 
	@Override
	public void displayLevel(Level l,Display d)
	{
		System.out.println(d.display(l));	
	}

	//running as a thread
	@Override
	public void start() 
	{
		reader=new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(System.out);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String commandLine = "";
				do {
					writer.print("Enter command: " );
					writer.flush();
					try {
						commandLine = reader.readLine();
						String[] arr = commandLine.split(" ");
						LinkedList<String> params = new LinkedList<String>();
						for (String param : arr) {
							params.add(param);
						}
						setChanged();
						notifyObservers(params);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (!stop);				
			}
		}).start();	
		
		
	}

	
	//Getters and Setters
	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	@Override
	public void stop() {
		this.stop = true;
		
	}

	@Override
	public void setcontrols(String up, String down, String left, String right) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayersList(List<User> p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLevelScores(List<UserRecord> p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserScores(List<UserRecord> p) {
		// TODO Auto-generated method stub
		
	}
}