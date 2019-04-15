import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

class Reader implements Runnable {

	DataInputStream dis;
	
	public Reader(DataInputStream dis) {
		this.dis = dis;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				int senderID = dis.readInt();
				String message = dis.readUTF();
				
				System.out.println("Client " + senderID + " says: \n" + message);
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}

class Writer implements Runnable {
	DataOutputStream dos;
	public Writer(DataOutputStream dos) {
		this.dos = dos;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter receiver id");
			int id = sc.nextInt();
			System.out.println("Enter message");
			String msg = sc.nextLine();
			
			try {
				dos.writeInt(id);
				dos.writeUTF(msg);
			}
			catch (Exception e) {
			}
		}
	}
}

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8001);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			new Thread(new Writer(dos)).start();
			new Thread(new Reader(dis)).start();
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
