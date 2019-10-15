package p3;
import java.util.Scanner;
public class iFace {
    private static Scanner input = new Scanner(System.in);
    public static final int max = 503;
    public static final int notFound = 501;
    public static final int removed = 502;

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
    private static long[] phone = new long[max];
    private static String[] email = new String[max];
    private static String[] fullname = new String[max];
    //////////////////////////////////////////////////////////////////////////////

    //Friendlist//////////////////////////////////////////////////////////////////
    private static int[][] friendlist = new int[max][max];
    private static int[][] solicitations = new int[max][max];
    //////////////////////////////////////////////////////////////////////////////

    //Community//////////////////////////////////////////////////////////////////
    private static int[] communityLeader = new int[max];
    private static int[][] communityMembers = new int[max][max];
    private static String[] communityName = new String[max];
    private static String[] communityDescription = new String[max];
    //////////////////////////////////////////////////////////////////////////////

    //Message/////////////////////////////////////////////////////////////////////
    private static String[][][] messageBox = new String[max][max][max + 1];
    private static int[] messageSent= new int[max];
    //////////////////////////////////////////////////////////////////////////////


    public static void main(String[] args) {
        System.out.println("\n||||| Welcome to iFace |||||\n");
        viewOptions();
        int command = -1;

        while (command != 0) {
            System.out.print("Write your option: ");
            command = input.nextInt();
            input.nextLine();

            if (command == 0) {
                System.out.println("\n||||| You closed the system. |||||\n");
                break;
            } else if (command == 1) {
                index++;
                System.out.print("Login: ");
                createLogin(index);
                System.out.print("Username: ");
                createUsername(index);
                System.out.print("Password: ");
                createPassword(index);
                System.out.println("\nNew account created.\n");
            } else if (command == 2) {
                System.out.print("Insert your login: ");
                String loginTemp = input.nextLine();
                System.out.print("Insert your password: ");
                String passwordTemp = input.nextLine();
                user = signInTemp(loginTemp, passwordTemp);
                if (user != -1) {
                    System.out.println("Your username: " + username[user] + ".\n");
                    checkRequests();
                    checkMessageBox();
                    viewProfileOptions();
                    int permission = input.nextInt();
                    profileControl(permission);
                }
            } else {
                System.out.println("\nYou chose a wrong option.\n");
            }
            viewOptions();
        }
    }

    public static void viewOptions() {
        System.out.println("Choose an option:\n0 to Close the program\n1 to Create an account\n2 to Login\n");
    }

    public static void viewProfileOptions() {
        System.out.println("What do you want to do ?\n0 to Logout\n1 to Create a profile\n2 to Edit a profile\n3 to Add a friend\n4 to Create a community\n5 to Enter a community\n6 to Send a message\n7 to Review your info\n8 to Delete your account\n");
        if (communityLeader[user] == 1) {
            System.out.println("10 to Manage your community\n");
        }
        System.out.print("As a logged user, write your option: ");
    }

    public static void viewEditingProfileOptions() {
        System.out.println("\nChoose the information you want to change: \n0 to none\n1 to age\n2 to full name\n3 to e-mail\n4 to phone number\n5 to login\n6 to username\n7 to password\n");
        System.out.print("Write your option: ");
    }

    public static String createLogin(int i) {
        login[i] = input.nextLine();
        return login[i];
    }

    public static String createUsername(int i) {
        username[i] = input.nextLine();
        return username[i];
    }

    public static String createPassword(int i) {
        password[i] = input.nextLine();
        return password[i];
    }

    public static int signInTemp(String t0, String t1) {
        for (int i = 0; i < max; i++) {
            if (t0.equals(login[i]) && t1.equals(password[i])) {
                System.out.println("\nLogin successful.\n");
                return i;
            }
        }
        System.out.println("\nLogin was not done.\n");
        return -1;
    }

    public static int searchUsername(String t0) {
        for (int i = 0; i < max; i++) {
            if (t0.equals(username[i])) {
                return i;
            }
        }
        return notFound;
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
            else if (permission == 5) {
                enteringCommunity();
            }
            else if (permission == 6) {
                messageInteraction();
            }
            else if (permission == 7) {
                reviewAccount();
            }
            else if (permission == 8) {
                deleteAccount();
                return;
            }
            else if (permission == 10) {
                manageCommunity();
            }
            else if (permission == 0) {
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
        while (true) {
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
            else if (choice == 5) {
                System.out.print("\nInsert your new login: ");
                login[user] = input.next();
                System.out.println("\nLogin was successfully changed.");
            }
            else if (choice == 6) {
                System.out.print("\nInsert your new username: ");
                username[user] = input.next();
                System.out.println("\nUsername was successfully changed.");
            }
            else if (choice == 7) {
                System.out.print("\nInsert your new password: ");
                password[user] = input.next();
                System.out.println("\nPassword was successfully changed.");
            }
            else if (choice == 0) {
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
        if (id == 501) {
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
            }
            else if (friendRequest.equalsIgnoreCase("No")) {
                return;
            }
        }
    }

    public static void checkRequests() {
        for (int i = 0; i < max; i++) {
            if (solicitations[user][i] > 0) {
                System.out.println(username[solicitations[user][i]] + " wants to be your friend. Do you want to accept ? [Yes/No]");
                String acceptance = input.next();

                if (acceptance.equalsIgnoreCase("Yes")) {
                    friendlist[user][solicitations[user][i]] = user;
                    System.out.println("\nNow, you and " + username[solicitations[user][i]] + " are friends.\n");
                    solicitations[user][i] = 0;
                }
                else {
                    System.out.println("\nYou refused the request.\n");
                    solicitations[user][i] = 0;
                }
            }
            else if (i == notFound) {
                System.out.println("You have no friend requests.\n");
                return;
            }
        }
    }

    public static void communityCreation() {
        System.out.println("\nDo you want to create a community ? [Yes/No] \n");
        String communityDecision = input.next();

        if (communityDecision.equalsIgnoreCase("Yes")) {
            communityLeader[user] = 1;
            communityMembers[user][user] = 1;
            input.nextLine();

            System.out.print("\nWrite the name of your community: ");
            communityName[user] = input.nextLine();

            System.out.print("Write a description for your community: ");
            communityDescription[user] = input.nextLine();

            System.out.println("\nCommunity " + communityName[user] + " created.\n");
            System.out.println("You are the leader of " + communityName[user] + ".\n");
        }
        else {
            System.out.println("\nCreation cancelled.\n");
            return;
        }
    }

    public static void enteringCommunity() {
        System.out.print("\nEnter the name of the community you want to enter: ");
        input.nextLine();
        String communitySearched = input.nextLine();

        int communityId = communitySearch(communitySearched);

        if (communityId != notFound) {
            System.out.println("\nCommunity " + communityName[communityId] + " found.");
            System.out.println("Community description: " + communityDescription[communityId] + "\n");
            System.out.println("Do you want to enter this community? [Yes/No] ");
            String decision = input.next();

            if (decision.equalsIgnoreCase("Yes")) {
                System.out.println("You entered in " + communityName[communityId] + " community.\n");
                communityMembers[communityId][user] = user;
            } else {
                System.out.println("\nYou refused to enter in a community.\n");
            }
        } else {
            System.out.println("\nThis community doesn't exist.\n");
        }
    }

    public static int communitySearch(String communitySearched) {
        for (int i = 0; i < max; i++) {
            if (communitySearched.equals(communityName[i])) {
                return i;
            }
        }
        return notFound;
    }

    public static void manageCommunity() {
        System.out.println("\nCommunity managing options: \n0 to Leave the page\n1 to Add members\n2 to Remove members\n");
        System.out.print("Write your option: ");
        int communityDecision = input.nextInt();
        if (communityDecision == 1) {
            addMembers();
        } else if (communityDecision == 2) {
            removeMembers();
        } else
            System.out.println("You are leaving the community managing page.");
    }

    public static void addMembers() {
        System.out.print("Search a username to add to your community: ");
        input.nextLine();
        String member = input.nextLine();
        int id = searchUsername(member);
        if (id != notFound) {
            System.out.print("\nDo you want to add " + username[id] + " in your community? [Yes/No] ");
            String decision = input.next();
            if (decision.equalsIgnoreCase("Yes")) {
                communityMembers[id][user] = id;
                System.out.println("\nMember added to your community.\n");
            }
        } else {
            System.out.println("\nUser doesn't exist.\n");
        }
    }

    public static void removeMembers() {
        System.out.print("\nSearch a username to remove from your community: ");
        input.nextLine();
        String member = input.nextLine();
        int id = searchUsername(member);
        if (id == notFound) {
            System.out.println("\nThe user does not exist.\n");
            return;
        }
        for (int i = 0; i < max; i++) {
            if (username[communityMembers[id][i]] != null) {
                if ((username[communityMembers[id][i]]).equals(member)) {

                    System.out.println("The user is in your community.\n");

                    if (id != notFound) {
                        System.out.print("Do you want to remove " + username[id] + " ? [Yes/No] ");
                        String decision = input.next();
                        if (decision.equalsIgnoreCase("Yes")) {
                            communityMembers[id][user] = removed;
                            System.out.println("\nYou removed the user from your community.\n");
                            return;
                        } else {
                            System.out.println("\nYou refused to remove the user from your community.\n");
                        }
                    }
                }
            }
            else if (communityMembers[id][i] == removed)
                System.out.println("\nThe user is not in your community.\n");
        }
    }

    public static void messageInteraction() {
        int i = 0;
        System.out.print("\nWrite the username you want to send the message: ");
        input.nextLine();
        String destiny = input.nextLine();
        int id = searchUsername(destiny);

        if (id != notFound) {
            while (true) {
                if (messageBox[id][user][i] == null) {
                    break;
                }
                i++;
            }
            System.out.print("\nWrite your message: ");
            messageBox[id][user][i] = input.nextLine();
            messageSent[id] = user;
            System.out.println("\nThe message was sent.\n");

        }
        else
            System.out.println("\nThe user does not exist.\n");
    }

    public static void checkMessageBox() {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if (messageBox[user][i][j] != null){
                    System.out.println("Message sent by " + username[i] + ": ");
                    System.out.println(messageBox[user][i][j] + "\n");
                }

            }
        }
    }

    public static void deleteAccount() {
        System.out.println("\nAre you sure ? [Yes/No]");
        String del = input.next();

        if (del.equalsIgnoreCase("Yes")) {
                accountDeletion();
                return;
        }
        else {
            System.out.println("Deletion cancelled.");
        }
    }

    public static void accountDeletion() {

        age[user] = removed;
        phone[user] = removed;
        email[user] = "deleted";
        fullname[user] = "deleted";

        for (int i = 1; i < max; i++) {
            if ((username[i].equals(username[user]))) {
                friendlist[i][user] = removed;
                solicitations[i][user] = removed;
                break;
            }
        }

        communityLeader[user] = removed;
        for (int l = 0; l < max; l++) {
            communityMembers[user][l] = removed;
        }
        communityName[user] = "deleted";
        communityDescription[user] = "deleted";

        for (int j = 0; j < max; j++) {
            for (int k = 0; k < max; k++) {
                messageBox[j][user][k] = "deleted";
                messageSent[user] = removed;
            }
        }

        username[user] = "deleted";
        login[user]= "deleted";
        password[user] = "deleted";
        System.out.println("Account deleted.");
    }

    public static void reviewAccount() {
        System.out.println("Login: " + login[user]);
        System.out.println("Username: " + username[user]);
        System.out.println("Password: " + password[user]);

        System.out.println("\nAge: " + age[user]);
        System.out.println("Full name: " + fullname[user]);
        System.out.println("Phone number: " + phone[user]);
        System.out.println("E-mail: " + email[user]);

        System.out.println("Friend List: ");
        for (int i = 0; i < max; i++) {
            if (username[friendlist[i][user]] != null)
                System.out.println(username[friendlist[i][user]]);
        }

        if (communityLeader[user] == 1) {
            System.out.println("\nYou are a community leader.");
            for (int j = 0; j < max; j++) {
                if (username[communityMembers[j][user]] != null) {
                    System.out.println("Members: " + username[communityMembers[j][user]]);
                }
                 printAllCommunities(j);
            }
        }
        else {
            System.out.println("\nYou are not a community leader.");
            for (int k = 0; k < max; k++) {
                printAllCommunities(k);
            }
        }
        printMessages();
    }

    public static void printAllCommunities(int j) {
        if (username[communityMembers[j][user]] != null) {
            if ((username[communityMembers[j][user]].equals(username[user]))) {
                System.out.println("Community name: " + communityName[j]);
                System.out.println("Community description: " + communityDescription[j]);
            }
        }
    }

    public static void printMessages() {
        System.out.println("Messages: ");
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if (username[i] != null && messageBox[i][user][j] != null)
                    System.out.println("Message sent by" + username[i] + ": " + messageBox[i][user][j]);
            }
        }
    }

}
