package rmi_chat;

// IChatServer.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatServer extends Remote {
    void sendMessage(String name, String message) throws RemoteException;
    void registerClient(IChatClient client) throws RemoteException;
}
