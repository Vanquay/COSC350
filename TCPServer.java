//Adebayo Abayomi
//COSC 350
//09/09/2020
package TCP;
import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer
{
    private ServerSocket socket;
    private Socket server;
    
    public void startServer() throws IOException
    {
        Scanner keyboard=new Scanner(System.in);
        socket=new ServerSocket(6789);
        System.out.println("Server is live\n");
        server=socket.accept();
        System.out.println("Client found.");
        
        System.out.println("Enter Server name:");
        String W=keyboard.next();
        
        //Establish two-way communication
        OutputStream output=server.getOutputStream();
        InputStream input=server.getInputStream();
        PrintWriter toServer=new PrintWriter(new OutputStreamWriter(output));
        BufferedReader fromServer=new BufferedReader(new InputStreamReader((input)));
        
        toServer.println(W);
        toServer.flush();
        
        //Close Server and Streams
        toServer.close();
        fromServer.close();
        server.close();
    }
    
}
