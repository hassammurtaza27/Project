import java.io.*;
import java.util.ArrayList;

public class Jazz extends Network implements Subscribable
{
    public Jazz(String mobileNum, Person person) throws IOException, ClassNotFoundException
    {
        super(mobileNum, person);
        super.networkName = "Jazz";
    }

    public double subscribeOffer(int offerNo, String mobNum, ArrayList<Network> network, double bal) throws IOException
    {
        super.appBalance = bal;
        if (super.subscribed[offerNo - 1])
        {
            System.out.println("This offer is already subscribed so it can not be subscribed again.");
            super.appBalance = 0;
            return -1;
        }

        Offer offer = readJazzPrice(offerNo);

        if (offer.getPrice() > super.appBalance)
        {
            System.out.println("You do not have enough balance to subscribe this offer!");
            super.appBalance = 0;
            return -1;
        }
        super.offersSubscribed.add(offer);
        System.out.println("This offer has been subscribed successfully");

        for (Network value : network) {
            if (value.mobileNum.equals(mobNum)) {
                value.subscribed[offerNo - 1] = true;
            }
        }
        super.updateFile("Jazz/JazzCustomers.txt", network);
        super.appBalance = 0;
        return offer.getPrice();

    }

    private static void addJazzOffer(Offer offer) throws IOException
    {
        addOffer(new Offer(offer), "Jazz/JazzPackages.txt");
    }

    public String toString()
    {

        return super.person +
                "\nMobile Number: " + super.mobileNum;
    }

    private Offer readJazzPrice(int offerNo)
    {
        return super.readPrice(offerNo, super.jazz);
    }

}



