import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PowerClient {
	public static void main(String[] args) 	{
		int a = 2, b = 4;
		Registry r1;
		try {
			r1 = LocateRegistry.getRegistry("localhost",8001);
			inter i = (inter) r1.lookup("power");
			System.out.println("Answer: " + i.power(a, b));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
