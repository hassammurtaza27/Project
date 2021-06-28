import java.io.*;
import java.util.ArrayList;

public class Zong extends Network implements Subscribable
{
    Zong(String mobileNum, Person person) throws IOException, ClassNotFoundException
    {
        super(mobileNum, person);
        super.networkName = "Zong";
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

        Offer offer = readZongPrice(offerNo);

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
        super.updateFile("Zong/ZongCustomers.txt", network);
        super.appBalance = 0;
        return offer.getPrice();
    }


    private static void addZongOffer(Offer offer)
            throws IOException
    {
        addOffer(new Offer(offer), "Zong/ZongPackages.txt");
    }

    public String toString() {

        return super.person +
                "\nMobile Number: " + super.mobileNum;
    }

    private Offer readZongPrice(int offerNo)
    {
        return super.readPrice(offerNo, super.zong);
    }

}



