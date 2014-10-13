package server;

import java.io.*;
import java.net.*;

public class ServerHandler implements Runnable{
	private Socket consocket=null;
	private String string1="HELO text";
	private String string2="KILL_SERVICE";
	private String infromClient=null;
	private SocketAddress IPaddress;
	private int port;
	private String studentID;
	private String state="true";
	
	

	public SocketAddress getIPaddress() {
		return IPaddress;
	}

	public int getPort() {
		return port;
	}

	public String getStudentID() {
		return studentID;
	}

	public ServerHandler(Socket consocket) {
		super();
		this.consocket = consocket;
		this.port=consocket.getLocalPort();
		this.IPaddress=
consocket.getRemoteSocketAddress();
		
		this.studentID="14300624";
		
	}

	@Override
	public void run() {
		BufferedReader fromClient=null;
		try {
			fromClient=new BufferedReader(new InputStreamReader(consocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			infromClient=fromClient.readLine();
			System.out.print(infromClient);
		} catch (IOException e) {
			System.out.println("input stream error");
			e.printStackTrace();
		}
		if(infromClient.equals(string1)){
			System.out.println("RECEIVE HELO text");
			hello();
		}else if(infromClient.equals(string2)){
			//System.out.println("RECEIVE KILL_SERVICE");
			killService();
		}else{
			String message3="FAULT INPUT!DO NOT PROCESS OTHER MESSAGE.";
			writeback(message3+'\n');
			
		}
		
	}

	private void killService(){
		String message2="TEMINATE";
		writeback(message2+'\n');
		System.out.println("Server connection close!");
		try {
			consocket.close();
		} catch (IOException e) {
			System.out.println("Server CLOSE ERROR!");
			e.printStackTrace();
		}
			
			
	}
	

	private void writeback(String message) {
		DataOutputStream outToClient=null;
		try {
			outToClient=new DataOutputStream(consocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	try {
		
		outToClient.writeBytes(message);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	private void hello() {
		String message1= String.format("HELO text IP:[%s] port:[%d]Student ID:[%s]", IPaddress,port,studentID);
		//String message1="String formatesdasd";
		writeback(message1+'\n');
		
	}

}
