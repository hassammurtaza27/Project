// Project by:
// Areeba Qadeer (SP20-BSE-013)
// Ashar Irfan (SP20-BSE-017)
// Hassam Murtaza (SP20-BSE-035)


import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner
{
    private static String mobileNumber;
    private static Register r;

    private static Verification v;

    static {
        try {
            v = new Verification();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ShowOffers offers;

    static {
        try {
            offers = new ShowOffers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Register> users = new ArrayList<>(); // stores every registered user of this app.


// This method checks whether user with given id is already present or not.
    private static boolean isUserFound(String id)
    {
        for (Register user : users)
        {
            if (user.getID().equals(id))
                return true;
        }
        return false;
    }


// This method is responsible for login.
    private static Register login() {
        Scanner input = new Scanner(System.in);
        String id, pass;
        String choice1;
        System.out.println("Enter your ID Card Number: ");
        id = input.next();
        System.out.println("Enter your password: ");
        pass = input.next();

        while (isPasswordCorrect(id, pass) == null)
        {
            System.out.println("Invalid id number or password");
            Menu.invalidLogin();
            choice1 = input.next();
            switch (choice1) {
                case "1" -> {
                    System.out.println("Enter your ID Card Number: ");
                    id = input.next();
                    System.out.println("Enter your password: ");
                    pass = input.next();
                }
                case "2" -> { return null; }
                case "3" -> System.exit(0);
                default -> System.out.println("Invalid input!");
            }
        }

        return isPasswordCorrect(id, pass);
    }


// This method is responsible for creating new account
    private static Register signup()
    {
        Scanner input = new Scanner(System.in);
        String id, pass, name, gender, color;
        String choice1;

        System.out.println("Enter your ID Card Number: ");
        id = input.next();
        while(isUserFound(id) || !Verification.verifyId(id))
        {
            Menu.invalidSignup("ID");
            choice1 = input.next();
            switch (choice1)
            {
                case "1":
                    System.out.println("Enter your ID Card Number: ");
                    id = input.next();
                    break;
                case "2":
                    return null;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }

        input.nextLine();
        System.out.println("Enter your name: ");
        name = input.nextLine();
        while(Verification.verifyNameColor(name))
        {
            Menu.invalidSignup("name");
            choice1 = input.next();
            switch (choice1)
            {
                case "1":
                    input.nextLine();
                    System.out.println("Enter your name: ");
                    name = input.nextLine();
                    break;
                case "2":
                    return null;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }

        System.out.println("Enter your password (more than 5 characters): ");
        pass = input.next();
        while(!Verification.verifyPassword(pass))
        {
            Menu.invalidSignup("Password");
            choice1 = input.next();
            switch (choice1)
            {
                case "1":
                    System.out.println("Enter your password (more than 5 characters): ");
                    pass = input.next();
                    break;
                case "2":
                    return null;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }

        input.nextLine();
        System.out.println("Enter your gender (Male, Female or Other): ");
        gender = input.next();
        while(!Verification.verifyGender(gender))
        {
            Menu.invalidSignup("Gender");
            choice1 = input.next();
            switch (choice1)
            {
                case "1":
                    System.out.println("Enter your gender: ");
                    gender = input.next();
                    break;
                case "2":
                    return null;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }

        System.out.println("Enter your favourite color. This question will be asked to recover your account if you forgot password: ");
        color = input.next();
        while(Verification.verifyNameColor(color))
        {
            Menu.invalidSignup("color");
            choice1 = input.next();
            switch (choice1)
            {
                case "1":
                    input.nextLine();
                    System.out.println("Enter your color: ");
                    color = input.nextLine();
                    break;
                case "2":
                    return null;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
        return new Register(id, name, gender, pass, color);
    }


// This method is used to recharge number
// If number is found then reference is returned else null is returned
    private static Network easyLoad()
    {

        Scanner input = new Scanner(System.in);
        String choice1;

        boolean exitPage = false;

        System.out.println("Enter mobile number: ");
        mobileNumber = input.next();

        while (!exitPage)
        {
            Menu.operatorPage();
            choice1 = input.next();

            switch (choice1) {
                case "1" -> {
                    return v.verifyJazzNumber(mobileNumber);
                }
                case "2" -> {
                    return v.verifyTelenorNumber(mobileNumber);
                }
                case "3" -> {
                    return v.verifyUfoneNumber(mobileNumber);
                }
                case "4" -> {
                    return v.verifyZongNumber(mobileNumber);
                }
                default -> {
                    System.out.println("Invalid input!\nSelect Correct Operator");
                    exitPage = true;
                }
            }
        }
        return null;
    }

    public static int changeForWeek(int str)
    {
        if (str == 1)
            return 5;
        else if (str == 2)
            return 6;
        else if (str == 3)
            return 7;
        else
            return 8;
    }

    public static int changeForMonth(int str)
    {
        if (str == 1)
            return 9;
        else if (str == 2)
            return 10;
        else if (str == 3)
            return 11;
        else
            return 12;
    }


// This method is used to subscribe daily offers of any network
    private static double subscribeOffers(int str, int i) throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter mobile number");
        Network n;
        String mobNum = input.next();
        if (i == 1)
        {
            n = v.verifyJazzNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Jazz)n).subscribeOffer(str, mobNum, v.jazzCustomers, r.getBalance());
            }
        }
        else if (i == 2)
        {
            n = v.verifyTelenorNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Telenor)n).subscribeOffer(str, mobNum, v.telenorCustomers, r.getBalance());
            }
        }

        else if (i == 3)
        {
            n = v.verifyUfoneNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Ufone)n).subscribeOffer(str, mobNum, v.ufoneCustomers, r.getBalance());
            }
        }

        else if (i == 4)
        {
            n = v.verifyZongNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Zong)n).subscribeOffer(str, mobNum, v.zongCustomers, r.getBalance());
            }
        }

        return 0;

    }


// This method is used to subscribe weekly offers of any network
    private static double subscribeWeeklyOffers(int str, int i) throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter mobile number");
        String mobNum = input.next();
        Network n;
        if (i == 1)
        {
            n = v.verifyJazzNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return((Jazz)n).subscribeOffer(str, mobNum, v.jazzCustomers, r.getBalance());
            }
        }
        else if (i == 2)
        {
            n = v.verifyTelenorNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Telenor)n).subscribeOffer(str, mobNum, v.telenorCustomers, r.getBalance());
            }
        }

        else if (i == 3)
        {
            n = v.verifyUfoneNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Ufone)n).subscribeOffer(str, mobNum, v.ufoneCustomers, r.getBalance());
            }
        }

        else if (i == 4)
        {
            n = v.verifyZongNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Zong)n).subscribeOffer(str, mobNum, v.zongCustomers, r.getBalance());
            }
        }

        return 0;

    }


// This method is used to subscribe monthly offer of any network
    private static double subscribeMonthlyOffers(int str, int i) throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter mobile number");
        String mobNum = input.next();
        Network n;
        if (i == 1)
        {
            n = v.verifyJazzNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Jazz)n).subscribeOffer(str, mobNum, v.jazzCustomers, r.getBalance());
            }
        }
        else if (i == 2)
        {
            n = v.verifyTelenorNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Telenor)n).subscribeOffer(str, mobNum, v.telenorCustomers, r.getBalance());
            }
        }

        else if (i == 3)
        {
            n = v.verifyUfoneNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Ufone)n).subscribeOffer(str, mobNum, v.ufoneCustomers, r.getBalance());
            }
        }

        else if (i == 4)
        {
            n = v.verifyZongNumber(mobNum);
            if (n == null)
            {
                return 0;
            }
            else
            {
                return ((Zong)n).subscribeOffer(str, mobNum, v.zongCustomers, r.getBalance());
            }
        }

        return 0;

    }


// This method is used to transfer money to another user of this app.
// If user is found then its reference is returned else null is returned.
    private static Register transferMoney()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the id of the user: ");
        String id = input.next();
        for (Register user : users) {
            if (id.equals(user.getID()))
                return user;
        }
        return null;
    }


// This method is used to check whether correct password for given id is used during login
    private static Register isPasswordCorrect(String id, String password)
    {
        for (Register user : users)
        {
            if (user.getID().equals(id) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }


// This method is used to read users from users.txt file.
    private static void readFile() throws IOException, ClassNotFoundException
    {

        ObjectInputStream f1 = new ObjectInputStream(new FileInputStream("Users.txt"));

        boolean eof = false;

        while(!eof)
        {
            try
            {
                Register s = (Register) f1.readObject();
                users.add(s);
            }
            catch (EOFException e)
            {
                eof = true;
            }
        }
        f1.close();

    }


// main method
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        readFile();

        Scanner input = new Scanner(System.in);
        String choice;

        System.out.println("Network Based Recharge and Payment Processing System.\n");
        boolean exit = false;

        // Login, Signup
        while(!exit)
        {
            Menu.loginPage();
            choice = input.next();

            switch (choice)
            {
                case "1" -> {
                    r = login();
                    if (r != null) {
                        exit = true;
                    }
                }
                case "2" -> {
                    r = signup();
                    if (r != null) {
                        exit = true;
                        users.add(r);
                    }
                }
                case "3" -> System.exit(0);
                default -> System.out.println("Invalid input!");
            }

        }

        exit = false;

        // Homepage
        while(!exit)
        {
            Menu.homePage();
            choice = input.next();

            switch (choice) {
                // recharge
                case "1" -> {
                    Network network = easyLoad();
                    if (network == null)
                        System.out.println("Unregistered or invalid number!");
                    else {
                        double bal;
                        while (true)
                        {
                            try
                            {
                                input.nextLine();
                                System.out.println("Enter the balance amount: ");
                                bal = input.nextDouble();
                                break;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Enter amount in correct format");
                            }
                        }

                        if (bal <= r.getBalance())
                        {
                            if (network instanceof Jazz)
                                network.recharge(bal, mobileNumber, v.jazzCustomers, "Jazz/JazzCustomers.txt");
                            else if (network instanceof Telenor)
                                network.recharge(bal, mobileNumber, v.telenorCustomers, "Telenor/TelenorCustomers.txt");
                            else if (network instanceof Ufone)
                                network.recharge(bal, mobileNumber, v.ufoneCustomers, "Ufone/UfoneCustomers.txt");
                            else if (network instanceof Zong)
                                network.recharge(bal, mobileNumber, v.zongCustomers, "Zong/ZongCustomers.txt");
                            r.setBalance(-bal);
                        }
                        else
                        {
                            System.out.println("You do not have enough balance!");
                        }
                    }
                }
                case "2" -> {
                    // subscribe offer
                    String choice2;
                    String choice3;

                    int i = 0;
                    Menu.durationPage();
                    String choice1 = input.next();
                    switch (choice1) {
                        case "1" -> {
                            Menu.operatorPage();
                            choice2 = input.next();
                            switch (choice2) {
                                case "1" -> {
                                    offers.readJazzOffers(1);
                                    i = 1;
                                }
                                case "2" -> {
                                    offers.readTelenorOffers(1);
                                    i = 2;
                                }
                                case "3" -> {
                                    offers.readUfoneOffers(1);
                                    i = 3;
                                }
                                case "4" -> {
                                    offers.readZongOffers(1);
                                    i = 4;
                                }
                                default -> System.out.println("Invalid input!");
                            }

                            choice3 = input.next();
                            if (choice3.equals("1") || choice3.equals("2") || choice3.equals("3") || choice3.equals("4")) {
                                double price = subscribeOffers(Integer.parseInt(choice3), i);
                                if (price == 0) {
                                    System.out.println("Unregistered or invalid number");
                                } else if (price == -1) {
                                } else {
                                    r.setBalance(-price);
                                }
                            } else
                                System.out.println("Invalid input!");

                        }
                        case "2" -> {
                            Menu.operatorPage();
                            i = 0;
                            choice2 = input.next();
                            switch (choice2) {
                                case "1" -> {
                                    offers.readJazzOffers(7);
                                    i = 1;
                                }
                                case "2" -> {
                                    offers.readTelenorOffers(7);
                                    i = 2;
                                }
                                case "3" -> {
                                    offers.readUfoneOffers(7);
                                    i = 3;
                                }
                                case "4" -> {
                                    offers.readZongOffers(7);
                                    i = 4;
                                }
                                default -> System.out.println("Invalid input!");
                            }

                            choice3 = input.next();
                            if (choice3.equals("1") || choice3.equals("2") || choice3.equals("3") || choice3.equals("4")) {
                                double price = subscribeWeeklyOffers(changeForWeek(Integer.parseInt(choice3)), i);
                                if (price == 0) {
                                    System.out.println("Unregistered or invalid number");
                                } else if (price == -1) {
                                } else {
                                    r.setBalance(-price);
                                }
                            } else
                                System.out.println("Invalid input!");
                        }
                        case "3" -> {
                            Menu.operatorPage();
                            choice2 = input.next();
                            switch (choice2) {
                                case "1" -> {
                                    offers.readJazzOffers(30);
                                    i = 1;
                                }
                                case "2" -> {
                                    offers.readTelenorOffers(30);
                                    i = 2;
                                }
                                case "3" -> {
                                    offers.readUfoneOffers(30);
                                    i = 3;
                                }
                                case "4" -> {
                                    offers.readZongOffers(30);
                                    i = 4;
                                }
                                default -> System.out.println("Invalid input!");
                            }

                            choice3 = input.next();
                            if (choice3.equals("1") || choice3.equals("2") || choice3.equals("3") || choice3.equals("4")) {
                                double price = subscribeMonthlyOffers(changeForMonth(Integer.parseInt(choice3)), i);
                                if (price == 0) {
                                    System.out.println("Unregistered or invalid number");
                                } else if (price == -1) {
                                } else {
                                    r.setBalance(-price);
                                }
                            } else
                                System.out.println("Invalid input!");
                        }


                        default -> System.out.println("Invalid input!");
                    }
                }
                case "3" -> {
                    // transfer money
                    Register r1 = transferMoney();
                    if (r1 == null)
                        System.out.println("Unregistered user!");
                    else {
                        double amount;
                        while (true)
                        {
                            try
                            {
                                input.nextLine();
                                System.out.println("Enter the amount you want to transfer: ");
                                amount = input.nextDouble();
                                break;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Enter amount in correct format");
                            }
                        }
                        if (amount > r.getBalance())
                            System.out.println("You do not have enough balance!");
                        else {
                            r1.setBalance(amount);
                            for (int j = 0; j < users.size(); j++) {
                                if (users.get(j).getID().equals(r1.getID())) {
                                    users.set(j, r1);
                                }
                            }

                        }

                    }
                }
                case "4" -> {
                    // deposit money
                    double price;
                    while (true)
                    {
                        try
                        {
                            input.nextLine();
                            System.out.println("Enter the amount you want to deposit: ");
                            price = input.nextDouble();
                            break;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("You entered wrong format");
                        }
                    }

                    r.setBalance(price);
                }
                case "5" -> System.out.println("Your current balance is: " + r.getBalance()); // view current balance
                case "6" -> exit = true;  // exit
                default -> System.out.println("Invalid input!");
            }
            v = new Verification();

        }
        PrintWriter pw = new PrintWriter("Users.txt"); // update users file if new user is added by signup
        pw.write("");
        pw.close();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Users.txt"));
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getID().equals(r.getID()))
                users.set(i, r);
            oos.writeObject(users.get(i));
        }
        oos.close();

    }

}
