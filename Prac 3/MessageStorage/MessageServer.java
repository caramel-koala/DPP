import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MessageServer implements MessageStorage
{
  int idcount = -1;

  ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();

  //Store a message
  public int StoreMessage (int username, String message)
  {
    ArrayList<String> msg  = messages.get(username);
    msg.add(message);
    return msg.size() -1;
  }

  //Read a message
  public String ReadMessage (int username, int messageID)
  {
    return messages.get(username).get(messageID);

  }

  //Get new username
  public int GetNewUsername()
  {
    idcount++;
    messages.add(new ArrayList<String>());
    return idcount-1;
  }

  public static void main (String args[])
  { try
    { MessageServer server = new MessageServer();
	    MessageStorage stub = (MessageStorage)UnicastRemoteObject.exportObject(server, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("MessageStorage", stub);

	    System.err.println("Message Storage Server ready");
    }
    catch (Exception e)
    { System.err.println("Server Exception: " + e.toString());
      e.printStackTrace();
    }

  } // main
}
