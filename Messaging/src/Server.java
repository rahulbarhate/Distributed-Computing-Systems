import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

class Handler implements Runnable {
	
	Socket socket;
	int id;
	DataInputStream dis;
	DataOutputStream dos;
	
	public Handler(Socket socket, int id, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.id = id;
		this.dis = dis;
		this.dos = dos;
	}
	
	@Override
	public void run() {
		try {
			int senderID = dis.readInt();
			String message = dis.readUTF();
			
			for (Handler h : Server.ar) {
				if (h.id == senderID) {
					h.dos.writeInt(id);
					h.dos.writeUTF(message);
					break;
				}
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

public class Server {
	static int i = 0;
	static Vector<Handler> ar;
	
	public static void main(String[] args) {
		ServerSocket mServerSocket = null;
		try {
			mServerSocket = new ServerSocket(8001);
			while (true) {
				Socket mSocket = mServerSocket.accept();
				System.out.println("Listening for Client " + i);
				ar = new Vector<Handler>();
				
				DataInputStream dis = new DataInputStream(mSocket.getInputStream());
				DataOutputStream dos = new DataOutputStream(mSocket.getOutputStream());
				Handler temp = new Handler(mSocket, i, dis, dos);
				Thread thread = new Thread(temp);
				thread.start();
				ar.add(temp);
				
				i++;
			}

			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}
