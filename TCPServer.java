import java.io.*;
import java.net.*;

/**
* Modified from Computer Networking: A top-down approach
*
* @Author Jim Kurose
* @Author Ira Goldstein
* @Version Spring 2023
*/

class TCPServer {
	public static void main(String argv[]) throws Exception {
		
		String clientSentence = "";
		String capitalizedSentence;
		
		// create a TCP socket to listen on
		ServerSocket welcomeSocket = new ServerSocket (6789);
		
		// run until the process is terminated
		while(true) {
			// accept new connections
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connected to: " + connectionSocket.getInetAddress().getHostAddress());
			
			// create the streams
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(
				connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
				connectionSocket.getOutputStream());
		  	clientSentence = inFromClient.readLine();
		    while(!clientSentence.equals("Exit")) {				
				// get the user's data
				System.out.println("Received: " + clientSentence);
				
				// transform the text to UPPER CASE and send the result to the client
				capitalizedSentence = clientSentence.toUpperCase() + '\n';
				outToClient.writeBytes(capitalizedSentence);
				clientSentence = inFromClient.readLine();
			}
			outToClient.writeBytes("Goodbye" +'\n');
			clientSentence = inFromClient.readLine();
			connectionSocket.close();
		}
	}
} 