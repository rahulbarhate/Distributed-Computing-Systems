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
			
			int num1, num2, ch;
			
			// String string;
			
			int cont = 1;
			
			while(cont == 1) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Number 1");
				num1 = scanner.nextInt();
				System.out.println("Enter Num ber 2");
				num2 = scanner.nextInt();
				
				
				System.out.println("Enter 1 for Addition, 2 for Subtraction");
				ch = scanner.nextInt();
				
				dos.writeInt(num1);
				dos.writeInt(num2);
				dos.writeInt(ch);
				
				// dos.write(string);
//				
//				dos.flush();
				
				int answer = dis.readInt();
				
				System.out.println("The Answer is " + answer);
				
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
