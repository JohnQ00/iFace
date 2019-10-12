package p3;
import java.util.Scanner;
public class iFace {
    private static Scanner input = new Scanner(System.in);
    public static final int max = 502;

    //External definitions////////////////////////////////////////////////////////
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

    //Friendlist//////////////////////////////////////////////////////////////////
    private static int[][] friendlist = new int[max][max];
    private static int[][] solicitations = new int [max][max];
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
                System.out.println("\n||||| You closed the system. |||||\n");
                break;
            }
            else if (command == 1) {
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
                System.out.print("Insert your login: ");
                String loginTemp = input.nextLine();
                System.out.print("Insert your password: ");
                String passwordTemp = input.nextLine();
                user = signInTemp(loginTemp,passwordTemp);
                if(user != -1) {
                    checkRequests();
                    viewProfileOptions();
                    int permission = input.nextInt();
                    profileControl(permission);
                }
            }
            else{
                System.out.println("\nYou chose a wrong option.\n");
            }
            viewOptions();
        }
    }

    public static void viewOptions(){
        System.out.println("Choose an option:\n0 to Close the program\n1 to Create an account\n2 to Login\n");
    }

    public static void viewProfileOptions() {
        System.out.println("What do you want to do ?\n0 to Logout\n1 to Create a profile\n2 to Edit a profile\n3 to Add a friend\n4 to Create a community\n");
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

    public static int searchUsername(String t0){
        for(int i = 0; i < max; i++){
            if (t0.equals(username[i])){
                return i;
            }
        }
        return 501;
    }

    public static void profileControl(int permission) {
        while (true) {
            if (permission == 1) {
                createProfile(user);
            }
            else if (permission == 2) {
                editProfile(user);
            }
            else if (permission == 3) {
                friendInteraction();
            }
            else if (permission == 4) {
                communityCreation();
            }
            else if (permission == 0){
                System.out.println("\nExiting the profile manage.\n");
                break;
            }
            viewProfileOptions();
            permission = input.nextInt();
        }
    }

    public static void createProfile(int user) {
        System.out.print("\nInsert your Age: ");
        age[user] = input.nextInt();
        input.nextLine();
        System.out.print("Insert your Full name: ");
        fullname[user] = input.nextLine();
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

    public static void friendInteraction() {
        System.out.println("\n||| Friend zone |||\n");
        System.out.print("Insert a username: ");
        String searchedUser = input.next();
        int id = searchUsername(searchedUser);
        if(id == 501) {
            System.out.println("\nThe user doesn't exist.\n");
            return;
        }
        else {
            System.out.println("Search result: " + username[id] + "\n");
            System.out.print("Do you want to add " + username[id] + " this user as a friend ? [Yes/No] ");
            String friendRequest = input.next();
            System.out.println("Friend request was sent.\n");
            if (friendRequest.equalsIgnoreCase("Yes")) {
                solicitations[id][user] = user;
            } else if (friendRequest.equalsIgnoreCase("No")) {
                return;
            }
        }
    }

    public static void checkRequests() {
        for (int i = 0; i < max; i++){
            if (solicitations[user][i] != 0) {
                System.out.println(username[solicitations[user][i]] + " wants to be your friend. Do you want to accept ? [Yes/No]");
                String acceptance = input.next();
                if (acceptance.equalsIgnoreCase("Yes")) {
                    friendlist[user][solicitations[user][i]] = 1;
                    System.out.println("\nNow, you and " + username[solicitations[user][i]] + " are friends.\n");
                    solicitations[user][i] = 0;
                }
                else {
                    System.out.println("\nYou refused the request.\n");
                    solicitations[user][i] = 0;
                }
            }
            if (i == max)
                return;
        }
    }

    public static void communityCreation() {
        System.out.println("\ncococococococococococ\n");
    }
}
