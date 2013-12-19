package textServer;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Bijil Abraham Philip
 * 
 */

public class Server
{
	ArrayList clientStreams;
	ArrayList<String> userList=new ArrayList<String>();
	
	public class ClientThread implements Runnable	
	{
		
		BufferedReader reader;
		Socket socket;
        PrintWriter client;
                

		public ClientThread(Socket clientSocket, PrintWriter user) 
		{
            client = user;
			try 
			{
				socket = clientSocket;
				InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
				reader = new BufferedReader(isReader);
			} 
			catch (Exception ex) 
			{
				System.out.println("Stream Reader failed to intialise");
			} 

		} 

		public void run() 
		{
			
			String message;
            String[] data;
            String connect = "Connect";
            String disconnect = "Disconnect";
            String chat = "Chat";

			try 
			{
					while ((message = reader.readLine()) != null) 
					{

						System.out.println("Received: " + message);
						data = message.split("\21");
                        for (String token:data) 
                        {
                                            
                             System.out.println(token);
                                        
                        }
                                        
                        if (data[2].equals(connect)) 
                        {

                            broadcastFrame((data[0] + "\21" + data[1] + "\21" + chat));
                            userAdd(data[0]);

                        } 
                       else if (data[2].equals(disconnect))
                       {

                           broadcastFrame((data[0] + "\21has logged off." + "\21" + chat));
                           userRemove(data[0]);

                       } 
                       else if (data[2].equals(chat)) 
                       {

                    	   broadcastFrame(message);

                       } 
                       else 
                       {
                           System.out.println("Invalid Frame!");
                                        
                       }


			     } 
			} 
			catch (Exception ex) 
			{
				System.out.println("Connect was lost");
                clientStreams.remove(client);
			} 
		} 
	} 

	public static void main (String[] args) 
	{
		new Server().go();
	}

	public void go() 
	{
		clientStreams = new ArrayList();

		try {
			ServerSocket serverSock = new ServerSocket(7000);

			while (true) {
				/* set up the server writer function and then begin at the same
			  	 the listener using the Runnable and Thread */
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientStreams.add(writer);

				// use a Runnable to start a 'second main method that will run
				// the listener
				Thread listener = new Thread(new ClientThread(clientSock, writer));
				listener.start();
				System.out.println("got a connection");
			} // end while
		} // end try
		catch (Exception ex)
		{
			System.out.println("error making a connection");
			ex.printStackTrace();
		} // end catch

	} // end go()


	public void userAdd (String data) {
                String message;
                String add = "\21 \21Connect", done = "Server\21 \21Done";
                userList.add(data);
                String[] tempList = new String[(userList.size())];
                userList.toArray(tempList);
                
                for (String token:tempList) {
                    
                    message = (token + add);
                    broadcastFrame(message);
                }
                broadcastFrame(done);
	}

	public void userRemove (String data) 
	{
                String message;
                String add = "\21 \21Connect", done = "Server\21 \21Done";
                userList.remove(data);
                String[] tempList = new String[(userList.size())];
                userList.toArray(tempList);

                for (String token:tempList) {

                    message = (token + add);
                    broadcastFrame(message);
                }
                broadcastFrame(done);
	}

        public void broadcastFrame(String message) {
        // sends message to all the clients connected to server
		Iterator it = clientStreams.iterator();

		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				System.out.println("Sending" + message);
                                writer.flush();
			} 
			catch (Exception ex) {
				System.out.println("error telling everyone");
			} 
		} 
	} 
	
}