import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class MessageClient
{
  private int username;

  MessageStorage ms;

  public void init()
  {
    try
    {
      Registry registry = LocateRegistry.getRegistry();
      username = ms.GetNewUsername();
    }
    catch (Exception e)
    {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
  }

  public int Write(String msg)
  {
    try
    {
      return ms.StoreMessage(username,msg);
    }
    catch (Exception e)
    {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
    return -1;
  }

  public String Read(int idx)
  {
    try
    {
      return ms.ReadMessage(username, idx);
    }
    catch (Exception e)
    {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
    return null;
  }


}
