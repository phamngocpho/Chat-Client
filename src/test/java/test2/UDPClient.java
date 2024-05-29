import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        final String serverIP = "192.168.208.193";
        final int serverPort = 1234;

        try {
            InetAddress serverAddress = InetAddress.getByName(serverIP);
            DatagramSocket socket = new DatagramSocket();
            String message = "23IT212-Phạm Ngọc Phổ";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server response: " + response);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}