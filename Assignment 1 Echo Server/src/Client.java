import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("localhost", 8001);
			
			DataOutputStream dos =  new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			
			String string;
			
			int cont = 1;
			
			while(cont == 1) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter your string");
				string = scanner.nextLine();
				
			
				dos.writeUTF(string);
				// dos.write(string);
//				
//				dos.flush();
				
				String returnValString = dis.readUTF();
				System.out.println("Echo server says " + returnValString);
				
				System.out.println("Do you want to repeat");
				cont = scanner.nextInt();
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
