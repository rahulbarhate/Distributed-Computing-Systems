import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	try {
			
			ServerSocket mServerSocket = new ServerSocket(8001);
			while(true) {
				Socket mSocket = mServerSocket.accept();
				
				System.out.println("Server Listening at 8001");
				
				DataOutputStream dos = new DataOutputStream(mSocket.getOutputStream());
				DataInputStream dis = new DataInputStream(mSocket.getInputStream());
				
				String string = dis.readUTF();
				
				System.out.println("Echoing string " + string);
				
				dos.writeUTF(string);
			}
			
//			dos.flush();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
