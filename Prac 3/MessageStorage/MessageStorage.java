import java.rmi.Remote;
import java.rmi.RemoteException;

/** This interface specifies the remote object
  * methods
  */
public interface MessageStorage extends Remote
  {
    //Store a message
    public int StoreMessage (int username, String message) throws RemoteException;

    //Read a message
    public String ReadMessage (int username, int messageID) throws RemoteException;

    //Get new username
    public int GetNewUsername() throws RemoteException;
  } // interface MessageStorage
