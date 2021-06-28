import java.io.IOException;
import java.util.ArrayList;

public interface Subscribable
{
    public double subscribeOffer(int offerNo, String number, ArrayList<Network> network, double bal) throws IOException, ClassNotFoundException;
}
