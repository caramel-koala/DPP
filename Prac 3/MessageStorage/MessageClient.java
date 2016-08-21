import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;

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

  public static void main (String[] args)
  {
    Scanner reader = new Scanner(System.in);  // Reading from System.in
    MessageClient user = new MessageClient();
    while (true)
    {
      System.out.println("(1=NewMessage,2=Readmessage,0=Exit): ");
      int n = reader.nextInt();
      reader.nextLine();
      switch (n)
      {
        case 0: System.exit(0);
        case 1: System.out.println("Enter Message: ");
                int code = user.Write(reader.nextLine());
                System.out.println("Message #" + code + " recorded.");
                reader.nextLine();
                break;
        case 2: System.out.println("Enter Message Code: ");
                int num = reader.nextInt();
                String msg = user.Read(num);
                reader.nextLine();
                System.out.println("Message #" + num + ": " + msg);
                reader.nextLine();
                break;
      }

    }
  }
}
