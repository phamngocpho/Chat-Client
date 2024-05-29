import java.net.*;
public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket(1234)){
            byte[] rData = new byte[1024];
            do {
                DatagramPacket rPacket = new DatagramPacket(rData, rData.length);
                datagramSocket.receive(rPacket);
                String s = new String(rPacket.getData(), 0, rPacket.getLength());
                System.out.println(s);
            } while (true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}