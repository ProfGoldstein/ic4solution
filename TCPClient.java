import java.io.*;
import java.net.*;

/**
* Modified from Computer Networking: A top-down approach
*
* @Author Jim Kurose
* @Author Ira Goldstein
* @Version Spring 2023
*/

class TCPClient {
	public static void main(String argv[]) throws Exception {
		String sentence = " ";
		String modifiedSentence;
		String response;
		Boolean hasMore = true;
		
		// set up the user's keyboardinput
		BufferedReader inFromUser = new BufferedReader(	new InputStreamReader(System.in));
		
		// create the socket and the streams
		// why is it 127.0.0.1?
		Socket clientSocket = new Socket("127.0.0.1", 6789);
		DataOutputStream outToServer = new DataOutputStream(
		clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		// sentence = inFromUser.readLine();
		while (hasMore){	
		// get the user's data and send it to the server
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		
		// get the server's reply
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		
		// get the user's response
		// this is not very robust.  Could use more checking.
		System.out.print(" More? (Y/n)  ");
		response = inFromUser.readLine().toLowerCase();
		if (response.equals("n") ){
			hasMore = false;
		}
	}
		// Send "Exit" to indicate that we are done
		outToServer.writeBytes("Exit\n");
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);

		// close the connection
		clientSocket.close();
	}
}