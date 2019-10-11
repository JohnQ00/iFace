package p3;
import java.util.Scanner;
public class iFace {
    private static Scanner input = new Scanner(System.in);
    public static final int max = 500;

    //External definitions////////////////////////////////////////////////////////
    private static int[] users = new int[max];
    static int index = 0;
    static int user = 0;
    //////////////////////////////////////////////////////////////////////////////

    //Internal definitions////////////////////////////////////////////////////////
    private static String[] login = new String[max];
    private static String[] password = new String[max];
    private static String[] username = new String[max];

    //Profile/////////////////////////////////////////////////////////////////////
    private static int[] age = new int[max];
    private static int[] phone = new int[max];
    private static String[] email = new String[max];
    private static String[] fullname = new String[max];
    //////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        System.out.println("\n||||| Welcome to iFace |||||\n");
        viewOptions();
        int command = -1;

        while (command != 0){
            System.out.print("Write your option: ");
            command = input.nextInt();
            input.nextLine();

            if (command == 0) {
                System.out.println("||||| You closed the system. |||||");
                break;
            }
            else if (command == 1) {
                System.out.println("You choosed Create an account.");
                index++;
                System.out.print("Login: ");
                createLogin(index);
                System.out.print("Username: ");
                createUsername(index);
                System.out.print("Password: ");
                createPassword(index);
                System.out.println("\nNew account created.\n");
            }
            else if (command == 2) {
                System.out.println("You choosed Login");
                System.out.print("Insert your login: ");
                String loginTemp = input.nextLine();
                System.out.print("Insert your password: ");
                String passwordTemp = input.nextLine();
                user = signInTemp(loginTemp,passwordTemp);
                viewProfileOptions();
                int permission = input.nextInt();
                profileControl(permission);
            }
            else{
                System.out.println("\nYou choosed a wrong option.\n");
            }
            viewOptions();
        }
    }

    public static void viewOptions(){
        System.out.println("Choose an option:\n0 to Leave the page\n1 to Create an account\n2 to Login\n");
    }

    public static void viewProfileOptions() {
        System.out.println("What do you want to do ?\n0 to Logout\n1 to Create a profile\n2 to Edit a profile\n");
        System.out.print("As a logged user, write your option: ");
    }

    public static void viewEditingProfileOptions() {
        System.out.println("\nChoose the information you want to change: \n0 to none\n1 to age\n2 to full name\n3 to e-mail\n4 to phone number\n");
        System.out.print("Write your option: ");
    }

    public static String createLogin(int i){
        login[i] = input.nextLine();
        return login[i];
    }
    public static String createUsername(int i){
        username[i] = input.nextLine();
        return username[i];
    }
    public static String createPassword(int i){
        password[i] = input.nextLine();
        return password[i];
    }

    public static int signInTemp(String t0, String t1){
        for(int i = 0; i < max; i++){
            if (t0.equals(login[i]) && t1.equals(password[i])){
                System.out.println("\nLogin successful.\n");
                return i;
            }
        }
        System.out.println("\nLogin was not done.\n");
        return -1;
    }

    public static void profileControl(int permission) {
        while (true) {
            if (permission == 1) {
                createProfile(user);
                System.out.println("first " + age[user]);
            }
            else if (permission == 2) {
                editProfile(user);
                System.out.println("second " + age[user]);
            }
            else if (permission == 0){
                System.out.println("Exiting the profile managing.");
                break;
            }
            viewProfileOptions();
            permission = input.nextInt();
        }
    }

    public static void createProfile(int user) {
        System.out.print("\nInsert your Age: ");
        age[user] = input.nextInt();
        System.out.print("Insert your Full name: ");
        fullname[user] = input.next();
        System.out.print("Insert your E-mail: ");
        email[user] = input.next();
        System.out.print("Insert your Phone number: ");
        phone[user] = input.nextInt();

        System.out.println("\nProfile creation successful\n");
    }

    public static void editProfile(int user) {
        int choice;
        viewEditingProfileOptions();
        choice = input.nextInt();
        while(true){
            if (choice == 1) {
                System.out.print("\nInsert your new age: ");
                age[user] = input.nextInt();
                System.out.println("\nAge was successfully changed.");
            }
            else if (choice == 2) {
                System.out.print("\nInsert your new full name: ");
                fullname[user] = input.next();
                System.out.println("\nFullname was successfully changed.");
            }
            else if (choice == 3) {
                System.out.print("\nInsert your new e-mail: ");
                email[user] = input.next();
                System.out.println("\nEmail was successfully changed.");
            }
            else if (choice == 4) {
                System.out.print("\nInsert your new phone number: ");
                phone[user] = input.nextInt();
                System.out.println("\nPhone number was successfully changed.");
            }
            else if (choice == 0){
                System.out.println("\nExiting the edition mode.\n");
                break;
            }
            viewEditingProfileOptions();
            choice = input.nextInt();
        }
    }
}