import java.util.*;

public class ATM 
{
  static int balance = 0;
  static String username;
  static String password;

  public static void main(String[] args) 
  {
    Scanner sc = new Scanner(System.in);

    while (true) 
    {
      // Menu for registration and login
      System.out.println("           Welcome to the ATM               ");
      System.out.println("------------------------------------------");
      System.out.println("1. Register");
      System.out.println("2. Login");
      System.out.println("3. Exit");
      System.out.println("-------------------------------------------");
      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      System.out.println();
      switch (choice) {
        case 1:
          register();
          break;
        case 2:
          if (login()) 
          {
            System.out.println();
            System.out.println("You have Successfully logged into your account");
            System.out.println();
            mainMenu();
          } 
          else 
          {
            System.out.println();
            System.out.println("Invalid username or password.");
            System.out.println("Please enter valid credentials");
            System.out.println();
          }
          break;
        case 3:
          System.out.println("Thank you for using the ATM");
          System.exit(0);
          break;
        default:
          System.out.println("Invalid choice.");
          break;
      }
    }

  }

  public static void register() 
  {
    Scanner sc = new Scanner(System.in);

    // Take input about user like username,password
    System.out.print("Enter your username: ");
    username = sc.nextLine();
    System.out.print("Enter your password: ");
    password = sc.nextLine();
    System.out.println();
    System.out.println("Registration successfully Completed");
    System.out.println();
  }

  public static boolean login() 
  {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter your username: ");
    String usernameInput = sc.nextLine();
    System.out.print("Enter your password: ");
    String passwordInput = sc.nextLine();

    if (usernameInput.equals(username) && passwordInput.equals(password)) {
      return true;
    } else {
      return false;
    }
  }

  public static void mainMenu() 
  {
    Scanner sc = new Scanner(System.in);
    Transactions tr = new Transactions();
    // This is our main menu
    while (true) {
      System.out.println();
      System.out.println();
      System.out.println("Main Menu");
      System.out.println("------------------------");
      System.out.println("1. Check balance");// To check balance
      System.out.println("2. Withdraw cash");// To withdraw money
      System.out.println("3. Deposit cash");// To deposit cash
      System.out.println("4. Transfer funds");// To transfer funds
      System.out.println("5. Mini-statement");// for displaying last 5 transactions
      System.out.println("6. Logout"); // for logging out of account
      System.out.println("------------------------");
      System.out.println();
      System.out.print("Enter your choice: ");

      int choice = sc.nextInt();
      System.out.println();

      switch (choice) {
        case 1:
          System.out.println("Your balance is " + balance);
          if (balance == 0) 
          {
            System.out.println();
            System.out
                .println("You don't have any money left in your bank please consider adding money into your account");
            System.out.println();
          }
          break;
        case 2:
          System.out.print("Enter the amount you want to withdraw: ");
          int withdrawAmount = sc.nextInt();

          if (withdrawAmount > balance) 
          { // if you try to withdraw money more than you have in your account
            System.out.println("Insufficient balance");
          } 
          else 
          {
            tr.addTransaction(withdrawAmount);
            balance -= withdrawAmount;
            System.out.println("Withdraw successful. Your balance is " + balance);
          }
          break;
        case 3:
          System.out.print("Enter the amount you want to deposit: ");
          int depositAmount = sc.nextInt();
          tr.addTransaction(depositAmount);
          balance += depositAmount;
          System.out.println("Deposit was successful");
          System.out.println("Your account balance is :: " + balance);
          break;
        case 4:
          System.out.print("Enter the recipient's username: ");
          String recipientUsername = sc.next();
          if (recipientUsername.equals(username)) 
          {
            System.out.println("You cannot transfer funds to yourself");
          } 
          else 
          {
            System.out.print("Enter the amount you want to transfer: ");
            int transferAmount = sc.nextInt();

            if (transferAmount > balance) 
            {
              System.out.println("Insufficient balance");
            } 
            else 
            {
              tr.addTransaction(transferAmount);
              balance -= transferAmount;
              System.out.println("Transfer was successful.");
              System.out.println("Your account balance is :: " + balance);
              System.out.println();
            }
          }
          break;
        case 5:
          System.out.println("Your last 5 transactions: ");
          // To display the last 5 transactions
          tr.displayLast5Transactions();
          break;
        case 6:
          System.out.println();
          System.out.println("You have been logged out Successfu;ly!");
          System.out.println();
          System.out.println("Thank you for visiting our ATM");
          System.out.println();
          main(null);
          break;
        default:
          System.out.println("Invalid choice.");
          break;
      }
    }
  }
}

class Transactions 
{
  private Queue<Integer> transactions = new LinkedList<>();

  // To maintain and add transctions of the account
  public void addTransaction(int amount) 
  {
    if (transactions.size() >= 5) 
    {
      transactions.remove();
    }
    transactions.add(amount);
  }

  // To display last 5 transactions
  public void displayLast5Transactions() 
  {
    if (transactions.size() == 0)
      System.out.println("No transaction history found");
    for (Integer transaction : transactions) 
    {
      System.out.println(transaction);
    }
  }
}
