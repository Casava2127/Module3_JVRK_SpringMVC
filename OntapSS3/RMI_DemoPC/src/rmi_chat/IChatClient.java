package rmi_chat;

// IChatClient.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatClient extends Remote {
    void receiveMessage(String message) throws RemoteException;
}
