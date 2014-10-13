package server;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class exeServer {

	private ServerSocket serverSocket;
	private int port;
	private int maxThread=10;
	
	public exeServer(int port) throws IOException {
		this.port=port;
		serverSocket=new ServerSocket(port);
		System.out.println("Server running");
	}
	
	public void dealService(){
		
		ExecutorService es=Executors.newFixedThreadPool(maxThread);
		Socket welcomeSocket=null;
		while(true){
		try {
			welcomeSocket=serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.execute(new ServerHandler(welcomeSocket));
		
		
		}
		
	}
	

	public static void main(String[] args) throws Exception{
		exeServer es=null;
		if (args.length != 1){
			 throw new IllegalArgumentException("Parameter(s):<Port>");
			}
		else{
		int servPort = Integer.parseInt(args[0]);
		es=new exeServer(servPort);
		}
		es.dealService();

	}

}
