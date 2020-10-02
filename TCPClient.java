//Adebayo Abayomi
//COSC 350
//09/09/2020
package TCP;
import java.net.*;
import java.io.*;
import java.lang.Object.*;
import java.nio.*;

public class TCPClient
{
    private Socket client;
    private String goat;
    
    public String web(String a)
    {
        return a;
    }
    
    public void startClient() throws IOException
    {
        //Create socket
        InetAddress ia=InetAddress.getLocalHost();
        String localhost=ia.getHostAddress();
        client=new Socket(localhost,6789);
        System.out.println(localhost);
        
        //Establish two-way communication
        OutputStream output=client.getOutputStream();
        InputStream input=client.getInputStream();
        PrintWriter toServer=new PrintWriter(new OutputStreamWriter(output));
        BufferedReader fromServer=new BufferedReader(new InputStreamReader((input)));
        
        while(true)
        {
            String response=fromServer.readLine();
            byte[] numberOfBytes;
            int byteNum=0;
            if(response.contains("www"))
            {
                //Establish GET Method
                System.out.println(response);
                Socket web=new Socket(response,80);
                System.out.println("Connected to Towson");
                URL url=new URL("https://"+response);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                
                //Read Text and print it
                BufferedReader fromWeb=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String webText;
                byte[] bytes=fromWeb.readLine().getBytes();
                //Wrap String in bytes
                byteNum=ByteBuffer.wrap(bytes).getInt();
                System.out.println(byteNum);
                while((webText=fromWeb.readLine())!=null)
                {
                    System.out.println(webText);
                }         
            }
            else if(response.equals("quit")) break;
            else
            {
                toServer.println("Client:" + response);
            } 
            System.out.println("Number of Bytes:" + byteNum);
            break;
        }
        //Close streams and Socket
        toServer.close();
        fromServer.close();
        client.close();
        
        
        
        
        
        
    }
    
    
}
