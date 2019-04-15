import java.net.ServerSocket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

interface inter extends Remote {
	int power(int a, int b) throws RemoteException;
}
public class PowerServer extends UnicastRemoteObject implements inter {

	protected PowerServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("Initializing Server\nServer Ready\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			PowerServer server = new PowerServer();
			LocateRegistry.createRegistry(8001).rebind("power", server);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int power(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return (int) Math.pow(a, b);
	}

}
