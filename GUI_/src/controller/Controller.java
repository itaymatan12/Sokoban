package controller;

import java.util.Observer;
import java.util.concurrent.BlockingQueue;

/**
 *abstract class that it's a pattern to a controller in the MVC pattern that have a queue of commands
 *and run's those command in an order and connect the Model and View layers of thr MVC pattern
 *
 */

public abstract class Controller implements Observer
{
	protected BlockingQueue<Command> queue;
	protected boolean stop = false;
	
	//running the controller in a thread
	public void Start()
	{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!stop) {
					try 
					{
						Command cmd = queue.take();
						if (cmd != null)
						{	
							if(cmd instanceof CommandExit)
							{
								Stop();
							}
							
							cmd.execute();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		thread.start();		
		
	}
	
	//stoping the thread
	public void Stop()
	{
		this.stop = true;
	}
}	