package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import model.Model;
import view.View;

public class MyController extends Controller {

	//Data members
	private Model m;
	private View v;
	private HashMap<String, Command> com_choise;//hash map that mapping string that represent a name of a command to the appropriate command object 
	
	
	//constructor with values
	public MyController(Model m, View v)
	{
		this.m = m;
		this.v = v;	
		this.queue = new LinkedBlockingQueue<Command>();
		com_choise = new HashMap<String,Command>();
		com_choise.put("display",new CommandDisplay());
		com_choise.put("exit",new CommandExit());
		com_choise.put("move",new CommandMove());
		com_choise.put("loadfile",new CommandLoadFileName());
		com_choise.put("savefile",new CommandSaveFileName());
		com_choise.put("cleanlevel", new CommandClearLevel());
		com_choise.put("setcontrols", new CommandSetControls());

		Command c =com_choise.get("setcontrols");
		   
		    LinkedList<Object> params = new LinkedList<Object>() ;
		    params.add(v);
		    LinkedList<String> controls =new LinkedList<String>();
		    try {
				controls = readxml();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    params.add(controls.removeFirst());
		    params.add(controls.removeFirst());
		    params.add(controls.removeFirst());
		    params.add(controls.removeFirst());
		    
		    c.setParams(params);
		    queue.add(c);
		this.Start();		
	}
	
	//server constructor
	public MyController(Model m)
	{
		this.m = m;	
		this.queue = new LinkedBlockingQueue<Command>();
		com_choise = new HashMap<String,Command>();
		com_choise.put("display",new CommandDisplay());
		com_choise.put("exit",new CommandExit());
		com_choise.put("move",new CommandMove());
		com_choise.put("loadfile",new CommandLoadFileName());
		com_choise.put("savefile",new CommandSaveFileName());

		this.Start();		
	}
	
	//update function from Observer interface
	@Override
	public void update(Observable o, Object arg) 
	{
		@SuppressWarnings("unchecked")
		LinkedList<Object> params = (LinkedList<Object>) arg;
		String commandKey = params.removeFirst().toString();

		Command command = com_choise.get(commandKey);//getting the appropriate command from the commands hash map
		
		//if the command that the user inserted doesn't exist
		if (command == null) {
			v.displayMessage("Command not found");
			return;
		}	
		
		//if the notify come from the Model we need to proceed to the View
		else if(o instanceof Model)
		{
			params.addFirst(v);//sending the view because we coming from the Model 
			params.addFirst(m);	
		}		
		

		//if the notify come from the View we need to proceed to the Model
		else if(o instanceof View)
		{		
			params.addFirst(m);
			if(commandKey.equals("exit"))
			{
				params.addLast(this);
				
			}
			
			
			if(commandKey.equals("GetAllUsersDB")|| commandKey.equals("GetAllScoresDB")||commandKey.equals("GetChosenPlayerScores")||commandKey.equals("UserScoreSortSteps")||commandKey.equals("UserScoreSortTime")||commandKey.equals("UserScoreSortLexical"))
			{
				System.out.println("777");
				params.addLast(v);
			}
		}
		
		
		command.setParams(params);
		this.queue.add(command);		//adding the command to the blocking queue	
	}
	
	
	//function that read the keyboard keys from the XML file
	public LinkedList<String> readxml() throws ParserConfigurationException, SAXException, IOException
	{
		LinkedList<String> params =new LinkedList<String>();
		
		
		File fXmlFile = new File("./resources/keyboardsokoban.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("staff");

		//System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			//System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

			//	System.out.println("Staff id : " + eElement.getAttribute("id"));
				 params.add(eElement.getElementsByTagName("up").item(0).getTextContent());
				 params.add(eElement.getElementsByTagName("down").item(0).getTextContent());
				params.add(eElement.getElementsByTagName("left").item(0).getTextContent());
				params.add(eElement.getElementsByTagName("right").item(0).getTextContent());

			}
		}
		return params;
	}

	//Getters and Setters
	public Model getM() {
		return m;
	}
	
	public void setM(Model m) {
		this.m = m;
	}

	public View getV() {
		return v;
	}

	public void setV(View v) {
		this.v = v;
	}
	
}