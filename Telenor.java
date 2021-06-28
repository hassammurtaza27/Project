import java.io.*;
import java.util.ArrayList;

public class Telenor extends Network implements Subscribable
{
    Telenor(String mobileNum, Person person) throws IOException, ClassNotFoundException
    {
        super(mobileNum, person);
        super.networkName = "Telenor";
    }

    public double subscribeOffer(int offerNo, String mobNum, ArrayList<Network> network, double bal) throws IOException
    {
        super.appBalance = bal;
        if (subscribed[offerNo - 1])
        {
            System.out.println("This offer is already subscribed so it can not be subscribed again.");
            super.appBalance = 0;
            return -1;
        }

        Offer offer = readTelenorPrice(offerNo);

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
        super.updateFile("Telenor/TelenorCustomers.txt", network);
        super.appBalance = 0;
        return offer.getPrice();
    }



    private static void addTelenorOffer(Offer offer) throws IOException
    {
        addOffer(new Offer(offer), "Telenor/TelenorPackages.txt");
    }

    public String toString()
    {

        return super.person +
                "\nMobile Number: " + super.mobileNum;
    }

    private Offer readTelenorPrice(int offerNo)
    {
        return super.readPrice(offerNo, super.telenor);
    }


}



