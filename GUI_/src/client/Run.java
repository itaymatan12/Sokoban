package client;


public class Run {

	public static void main(String[] args) {
			
		String ip=args[0];
		int port= Integer.parseInt(args[1]);
		client client=new client();
		client.start(ip, port);
		
		
	}

}
