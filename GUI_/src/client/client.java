package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	
	private Socket theserver;
	private ObjectInputStream serverInput;
	private DataOutputStream outToServer ;
	
	
	public client() {
		// TODO Auto-generated constructor stub
	}
	
	public client(String ip,int port) throws UnknownHostException, IOException
	{

			this.theserver = new Socket(ip ,port);
			
			try 
			{
				serverInput = new ObjectInputStream(theserver.getInputStream());
				outToServer= new DataOutputStream(theserver.getOutputStream());
			//	outToServer.writeBytes("itay");
			}
				
				
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr){
	
			String line;
			try {
				while(true){
					
					line=in.readLine();
					if(line==null)
					{
						break;
					}
					out.println(line);
					out.flush();
					if (line != null) {
						if (line.equals(exitStr)) {
						return;
					}
					}
					
		
		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	private Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		Thread t=new Thread(new Runnable() {
		public void run() { readInputsAndSend(in, out, exitStr); }
		});
		t.start();
		return t;
	}
	
	public void start(String ip, int port){
		
		Socket theServer;
		try {
			theServer = new Socket(ip, port);

			System.out.println("connected to server");
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader serverInput=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());
			PrintWriter outToScreen = new PrintWriter(System.out);
			Thread t1= aSyncReadInputsAndSend(userInput,outToServer,"exit"); // different thread
			Thread t2= aSyncReadInputsAndSend(serverInput,outToScreen,"bye"); // different thread
			t1.join(); t2.join(); // wait for threads to end
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	public DataOutputStream getOutToServer() {
		return outToServer;
	}

	public void setOutToServer(DataOutputStream outToServer) {
		this.outToServer = outToServer;
	}

	public ObjectInputStream getServerInput() {
		return serverInput;
	}

	public void setServerInput(ObjectInputStream serverInput) {
		this.serverInput = serverInput;
	}

	public Socket getTheserver() {
		return theserver;
	}

	public void setTheserver(Socket theserver) {
		this.theserver = theserver;
	}








}
