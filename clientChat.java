package Client;


import java.io.*;
import java.io.InputStreamReader;
import java.net.Socket;

public class clientChat {
	public static void main(String[] args) throws Exception {
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		Socket clientSocket=null;
		String  reply;
		System.out.print("running");
		if ((args.length < 2) || (args.length > 3))
			throw new IllegalArgumentException("Parameters:<Server><Word>[<Port>]]");
			String server = args[0];
			byte[] data = args[1].getBytes();
			int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 22222;
		clientSocket = new Socket(server, servPort);
		outToServer=new DataOutputStream(clientSocket.getOutputStream());
		
		inFromServer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String dataStr=new String(data);
		outToServer.writeBytes(dataStr+'\n');
		reply=inFromServer.readLine();
		System.out.println("From Server:"+reply);
		if(reply.equals("TEMINATE")){
			System.out.println("client connection close!");
		inFromServer.close();
		outToServer.close();
		clientSocket.close();
		}
	}
}
