import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Verification
{
    public ArrayList<Network> telenorCustomers = new ArrayList<>();
    public ArrayList<Network> jazzCustomers = new ArrayList<>();
    public ArrayList<Network> zongCustomers = new ArrayList<>();
    public ArrayList<Network> ufoneCustomers = new ArrayList<>();

    public Verification() throws IOException, ClassNotFoundException
    {
        readFile("Zong/ZongCustomers.txt", zongCustomers);
        readFile("Ufone/UfoneCustomers.txt", ufoneCustomers);
        readFile("Jazz/JazzCustomers.txt", jazzCustomers);
        readFile("Telenor/TelenorCustomers.txt", telenorCustomers);
    }


    private void readFile(String filePath, ArrayList<Network> operator) throws IOException, ClassNotFoundException
    {

        ObjectInputStream f1 = new ObjectInputStream(new FileInputStream(filePath));

        boolean eof = false;

        while(!eof)
        {
            try
            {
                Network s = (Network) f1.readObject();
                operator.add(s);
            }
            catch (EOFException e)
            {
                eof = true;
            }
        }
        f1.close();

    }

    public Telenor verifyTelenorNumber(String mobNum)
    {
        for (Network telenorCustomer : telenorCustomers) {
            if (telenorCustomer.mobileNum.equals(mobNum))
                return (Telenor) telenorCustomer;
        }

        return null;

    }

    public Network verifyZongNumber(String mobNum)
    {
        for (Network zongCustomer : zongCustomers) {
            if (zongCustomer.mobileNum.equals(mobNum))
                return zongCustomer;
        }

        return null;

    }

    public Ufone verifyUfoneNumber(String mobNum)
    {
        for (Network ufoneCustomer : ufoneCustomers) {
            if (ufoneCustomer.mobileNum.equals(mobNum))
                return (Ufone) ufoneCustomer;
        }

        return null;

    }

    public Jazz verifyJazzNumber(String mobNum)
    {
        for (Network jazzCustomer : jazzCustomers) {
            if (jazzCustomer.mobileNum.equals(mobNum))
                return (Jazz) jazzCustomer;
        }

        return null;

    }

    public static boolean verifyId(String id)
    {
        if (id.length() < 13)
            return false;
        for (int i = 0; i < 13; i++)
        {
            if (!(Character.isDigit(id.charAt(i))))
                return false;
        }

        return true;
    }

    public static boolean verifyPassword(String password)
    {
        return password.length() >= 6;
    }

    public static boolean verifyNameColor(String str)
    {
        if (str.length() < 3)
            return true;
        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isDigit(str.charAt(i)))
                return true;
        }

        return false;
    }

    public static boolean verifyGender(String str)
    {
        return str.equalsIgnoreCase("Male") || str.equalsIgnoreCase("Female") || str.equalsIgnoreCase("Other");
    }
}
