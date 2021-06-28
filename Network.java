import java.io.*;
import java.util.ArrayList;

public abstract class Network implements Serializable
{
    protected String networkName;
    protected String mobileNum;
    protected double networkBalance;
    protected boolean[] subscribed = new boolean[12]; // to hold which offer from given offer is subscribed
    protected double appBalance;
    protected Person person; // person or user of network

    protected ArrayList<Offer> offersSubscribed = new ArrayList<>(); // holds subscribed offers for user

    // holds offers for respective networks
    protected ArrayList<Offer> jazz = new ArrayList<>();
    protected ArrayList<Offer> zong = new ArrayList<>();
    protected ArrayList<Offer> ufone = new ArrayList<>();
    protected ArrayList<Offer> telenor = new ArrayList<>();

    public Network() throws IOException, ClassNotFoundException
    {
        readFile("Zong/ZongPackages.txt", zong);
        readFile("Jazz/JazzPackages.txt", jazz);
        readFile("Telenor/TelenorPackages.txt", telenor);
        readFile("Ufone/UfonePackages.txt", ufone);
    }


    public Network(String mobile,Person person) throws IOException, ClassNotFoundException
    {
        this();
        this.mobileNum = mobile;
        this.networkBalance =  this.appBalance = 0;
        this.person = new Person(person);
        for (int i = 0; i < 12; i++)
        {
            subscribed[i] = false;
        }
    }

    public void updateFile(String filePath, ArrayList<Network> network) throws IOException {
        PrintWriter f = new PrintWriter(filePath);
        f.write("");
        f.close();

        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filePath));

        for (Network value : network) {
            file.writeObject(value);
        }
        file.close();
    }


    public String getNetworkName()
    {
        return networkName;
    }

    public double getBalance() {
        return networkBalance;
    }

    public String getMobileNum()
    {
        return mobileNum;
    }

    protected void addAppBalance(double balance)
    {
        appBalance += balance;
    }

    protected void recharge(double balance, String mobNum, ArrayList<Network> network, String fileName) throws IOException {
        networkBalance += balance;
        for (Network value : network) {
            if (value.mobileNum.equals(mobNum)) {
                value.networkBalance += balance;
            }
        }
        updateFile(fileName, network);
    }

    protected static void addOffer(Offer offer, String filePath) throws IOException
    {
        ObjectOutputStream f;
        File file = new File(filePath);

        if (file.exists())
        {
            f = new ObjectOutputStream(new FileOutputStream(filePath, true)) {@Override public void writeStreamHeader(){}};
        }
        else
        {
            f = new ObjectOutputStream(new FileOutputStream(filePath));
        }
        f.writeObject(offer);
        f.close();
    }

    protected Offer readPrice(int offerNo, ArrayList<Offer> offer) // read price of offer selected
    {
        for (Offer value : offer) {
            if (value.getId() == offerNo) {
                return new Offer(value);
            }
        }

        return null;

    }

    // read given file and store offers in given arraylist
    // this method was used to read offers of every network and store them in arraylist
    protected void readFile(String filePath, ArrayList<Offer> offer) throws IOException, ClassNotFoundException
    {

        ObjectInputStream f1 = new ObjectInputStream(new FileInputStream(filePath));

        boolean eof = false;

        while(!eof)
        {
            try
            {
                Offer s = (Offer)f1.readObject();
                offer.add(s);
            }
            catch (EOFException e)
            {
                eof = true;
            }
        }
        f1.close();

    }


    // during program execution offers are read using this method from arraylist provided in argument.
    protected void readOffers(int duration, ArrayList<Offer> offer)
    {
        for (int i = 0, j = 1; i < offer.size(); i++)
        {
            if (offer.get(i).getDuration() == duration)
            {
                System.out.print(j + "- ");
                System.out.print(offer.get(i) + "\n");
                j++;
            }
        }
    }

}
