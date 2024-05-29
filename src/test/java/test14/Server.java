package Test1;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.net.ServerSocket;
/*
 * A chat server that delivers public and private messages and files.
 */
public class Server {
    // The server socket.
    private static ServerSocket serverSocket = null;
    public static ArrayList<clientThread> clients = new ArrayList<>();
    public static void main(String[] args) {
        // The default port number.
        int portNumber = 1234;
        if (args.length < 1) {
            System.out.println("No port specified by user.\nServer is running using default port number=" + portNumber);
        } else {
            portNumber = Integer.parseInt(args[0]);
            System.out.println("Server is running using specified port number=" + portNumber);
        }
        /*
         * Open a server socket on the portNumber (default 1234).
         */
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println("Server Socket cannot be created");
        }
        /*
         * Create a client socket for each connection and pass it to a new client
         * thread.
         */
        int clientNum = 1;
        while (true) {
            try {
                // The client socket.
                Socket clientSocket = serverSocket.accept();
                clientThread curr_client =  new clientThread(clientSocket, clients);
                clients.add(curr_client);
                curr_client.start();
                System.out.println("Client "  + clientNum + " is connected!");
                clientNum++;
            } catch (IOException e) {
                System.out.println("Client could not be connected");
            }
        }
    }
}
/*
 * This client thread class handles individual clients in their respective threads
 * by opening a separate input and output streams.
 */
class clientThread extends Thread {
    private String clientName = null;
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private final Socket clientSocket;
    private final ArrayList<clientThread> clients;

    public clientThread(Socket clientSocket, ArrayList<clientThread> clients) {
        this.clientSocket = clientSocket;
        this.clients = clients;
    }

    public void run() {
        try {
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());
            clientName = handleClientRegistration();

            broadcast(clientName + " has joined the chat!");

            handleClientMessages();

            clients.remove(this);
            broadcast(clientName + " has left the chat.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client disconnected unexpectedly.");
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleClientRegistration() throws IOException, ClassNotFoundException {
        os.writeObject("Please enter your name:");
        os.flush();
        String name = (String) is.readObject();
        name = name.trim();
        while (name.contains("@") || name.contains("!") || isClientNameTaken(name)) {
            os.writeObject("Invalid name. Please enter a name without '@' or '!' and ensure it is unique:");
            os.flush();
            name = ((String) is.readObject()).trim();
        }

        clientName = "@" + name;
        return clientName;
    }

    private boolean isClientNameTaken(String name) {
        synchronized (clients) {
            for (clientThread client : clients) {
                if (client != null && client.clientName != null && client.clientName.equals("@" + name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void broadcast(String message) throws IOException {
        synchronized (clients) {
            for (clientThread client : clients) {
                if (client != null && client.os != null && client != this) {
                    client.os.writeObject(message);
                    client.os.flush();
                }
            }
        }
    }

    private void handleClientMessages() throws IOException, ClassNotFoundException {
        while (true) {
            String line = (String) is.readObject();

            if (line.trim().equalsIgnoreCase("/quit")) {
                break;
            }

            if (line.startsWith("@")) {
                unicast(line);
            } else {
                broadcast(clientName + ": " + line);
            }
        }
    }

    private void unicast(String line) throws IOException, ClassNotFoundException {
        String[] parts = line.split(":", 2);
        String recipient = parts[0]; // Tên người nhận
        String message = parts[1].trim();

        synchronized (clients) {
            for (clientThread client : clients) {
                if (client != null && client.clientName.equals(recipient)) {
                    client.os.writeObject(clientName + " (private): " + message);
                    client.os.flush();
                    break;
                }
            }
        }
    }
}
