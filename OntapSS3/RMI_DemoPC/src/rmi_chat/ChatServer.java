package rmi_chat;

// ChatServer.java
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements IChatServer {
    private final List<IChatClient> clients = new ArrayList<>();

    protected ChatServer() throws Exception {
        super();
    }

    @Override
    public synchronized void sendMessage(String name, String message) throws RemoteException {
        String fullMsg = name + ": " + message;
        System.out.println(fullMsg);
        for (IChatClient client : clients) {
            client.receiveMessage(fullMsg);
        }
    }

    @Override
    public synchronized void registerClient(IChatClient client) throws RemoteException {
        clients.add(client);
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("java.rmi.server.hostname", "192.168.1.226"); // IP máy server
        ChatServer server = new ChatServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("ChatServer", server);
        System.out.println("Chat Server running...");
    }
}
