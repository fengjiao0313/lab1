package Client;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientChat {
	public static void main(String[] args) throws Exception {
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		BufferedReader inFromUser = null;
		Socket clientSocket=null;
		String  reply,dataStr=null;
		System.out.println("please input the message");
		inFromUser=new BufferedReader(new InputStreamReader(System.in));
		
		clientSocket = new Socket("localhost", 22226);
		outToServer=new DataOutputStream(clientSocket.getOutputStream());
		
		inFromServer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		/*
		 *  question: cannot receive new message of input after sending message.
		 */
		
			while((dataStr=inFromUser.readLine())!=null){
				outToServer.writeBytes(dataStr+'\n');
				
				while((reply=inFromServer.readLine())!=null){
					if(reply.equals("z")) {
						System.out.println("Please Input message");
						break;
					}
						System.out.println("From Server:"+reply);
						if(reply.equals("TEMINATE")){
							System.out.println("client connection close!");
								inFromServer.close();
								outToServer.close();
								clientSocket.close();
								break;
						}
					}
			}
		
	}
}
