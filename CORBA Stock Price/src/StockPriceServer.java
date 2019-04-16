import StockPriceApp.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

class StockPriceImpl extends StockPricePOA {

    ORB orb;

    public StockPriceImpl(ORB orb) {
        this.orb = orb;
    }

    @Override
    public double getPrice() {
        return Math.random();
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
}


public class StockPriceServer {
    public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);

        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootPOA.the_POAManager().activate();

        StockPriceImpl stockPriceImpl = new StockPriceImpl(orb);

        org.omg.CORBA.Object ref = rootPOA.servant_to_reference(stockPriceImpl);
        StockPrice sRef = StockPriceHelper.narrow(ref);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt nRef = NamingContextExtHelper.narrow(objRef);

        String name = "Stock_Price";
        NameComponent path[] = nRef.to_name(name);

        nRef.rebind(path, sRef);

        System.out.println("Stock Price Server ready and listening");

        orb.run();
    }
}
