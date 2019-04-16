import StockPriceApp.StockPrice;
import StockPriceApp.StockPriceHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class StockPriceClient {

     static StockPrice stockPriceImpl;

    public static void main(String[] args) throws Exception{
        ORB orb = ORB.init(args, null);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        String name = "Stock_Price";
        stockPriceImpl = StockPriceHelper.narrow(ncRef.resolve_str(name));

        while (true) {
            System.out.println("Stock Price: " + stockPriceImpl.getPrice());
            Thread.sleep(1000);
        }

    }

}
