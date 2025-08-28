package rmi_chat;

// ChatClient.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements IChatClient {
    private final String name;
    private final IChatServer server;

    protected ChatClient(String name, IChatServer server) throws Exception {
        super();
        this.name = name;
        this.server = server;
        server.registerClient(this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(message);
    }

    public void start() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.nextLine();
            server.sendMessage(name, msg);
        }
    }

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("192.168.1.226", 1099); // IP server
        IChatServer server = (IChatServer) registry.lookup("ChatServer");

        ChatClient client = new ChatClient("ClientB", server);
        client.start();
    }
}
//C:\D\VscodePC\Java\TuHocJV\Module3_JVRK_SpringMVC\OntapSS3\RMI_DemoPC>dir /s /b src\*.java > sources.txt
//
//C:\D\VscodePC\Java\TuHocJV\Module3_JVRK_SpringMVC\OntapSS3\RMI_DemoPC>javac -d out @sources.txt
//
//C:\D\VscodePC\Java\TuHocJV\Module3_JVRK_SpringMVC\OntapSS3\RMI_DemoPC>
//C:\D\VscodePC\Java\TuHocJV\Module3_JVRK_SpringMVC\OntapSS3\RMI_DemoPC>java -cp out rmi_chat.ChatClient