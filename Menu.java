public class Menu
{
    private static final String line = "Enter 1, 2, 3 etc to select below options: ";

    public static void loginPage()
    {
        System.out.println(line);
        System.out.println("1- Login" +
                "\n2- Signup" +
                "\n3- Exit");
    }

    public static void invalidLogin()
    {
        System.out.println("1- Login again" +
                "\n2- Back" +
                "\n3- Exit");
    }

    public static void invalidSignup(String str)
    {
        System.out.println("Invalid " + str +
                "\n1- Re-enter " + str +
                "\n2- Back" +
                "\n3- Exit");
    }

    public static void homePage()
    {
        System.out.println(line);
        System.out.println("1- EasyLoad" +
                "\n2- Mobile Packages" +
                "\n3- Transfer Money" +
                "\n4- Deposit Money" +
                "\n5- View Current Balance" +
                "\n6- Exit");
    }

    public static void operatorPage()
    {
        System.out.println("Select Operator: \n");
        System.out.println("1- Jazz" +
                "\n2- Telenor" +
                "\n3- Ufone" +
                "\n4- Zong");
    }

    public static void durationPage()
    {
        System.out.println("1- Daily Offers" +
                "\n2- Weekly Offers" +
                "\n3- Monthly Offers");
    }
}
