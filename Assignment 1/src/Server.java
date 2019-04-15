import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			ServerSocket mServerSocket = new ServerSocket(8001);
			while(true) {
				Socket mSocket = mServerSocket.accept();
				
				System.out.println("Server Listening at 8001");
				
				DataOutputStream dos = new DataOutputStream(mSocket.getOutputStream());
				DataInputStream dis = new DataInputStream(mSocket.getInputStream());
				
				int num1, num2, ch;
				
				num1 = dis.readInt();
				num2 = dis.readInt();
				ch = dis.readInt();
				
				System.out.println("Received Numbers");
				System.out.println("Started Calculation");
				
				int answer;
				
				switch(ch) {
				case 1: {
					answer = num1 + num2;
					break;
				} 
				case 2: {
					answer = num1 - num2;
					break;
				}
				case 3: {
					answer = num1 * num2;
					break;
				}
				default: {
					answer = num1 + num2;
				}
				}
				
				System.out.println("Sending Answer");
				
				dos.writeInt(answer);
			}
			
//			dos.flush();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
