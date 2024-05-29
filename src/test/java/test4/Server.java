package d94j;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket s = new Socket();
    static DataInputStream in;
    static DataOutputStream out;

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(1234);
            while(true){
                Socket s = socket.accept();
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
                while(true){
                    try {
                        double a = in.readDouble();
                        double b = in.readDouble();
                        out.writeDouble(a+b);
                    }catch(Exception e){
                        s.close();
                    }
                }
            }
        }catch(Exception e){

        }
    }
}
