 import java.util.Scanner;
class BankAccount
{
    String name;
    String userid;
    String passcode;
    String accountNo;
    float current_balance = 55000f;
    int transactions = 0;
    String transactionHist = "";
    public void Registration()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name - ");
        this.name = sc.nextLine();
        System.out.print("\nEnter Your Userid - ");
        this.userid = sc.nextLine();
        System.out.print("\nEnter Your Passcode - ");
        this.passcode = sc.nextLine();
        System.out.print("\nEnter Your Account Number - ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration process is completed...now you can login");
    }
    public boolean login()
    {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while ( !isLogin )
        {
            System.out.print("\nEnter Your Userid - ");
            String Userid = sc.nextLine();
            if ( Userid.equals(userid) )
            {
                while ( !isLogin )
                {
                    System.out.print("\nEnter Your Pin- ");
                    String Pin = sc.nextLine();
                    if ( Pin.equals(passcode) )
                    {
                        System.out.print("\nLogged in successfully!!");
                        isLogin = true;
                    }
                    else
                    {
                        System.out.println("\nIncorrect Passcode");
                    }
                }
            }
            else
            {
                System.out.println("\nUserid not found");
            }
        }
        return isLogin;
    }
    public void WithdrawMoney()
    {
        System.out.print("\nEnter amount to withdraw - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try
        {
            if ( current_balance >= amount )
            {
                transactions++;
                current_balance -= amount;
                System.out.println("\nWithdraw Successfully");
                String str = amount + " Rs Withdrawed\n";
                transactionHist = transactionHist.concat(str);

            }
            else
            {
                System.out.println("\nInsufficient Balance");
            }
        }
        catch ( Exception e)
        {
        }
    }
    public void DepositMoney()
    {
        System.out.print("\nEnter amount to deposit - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try
        {
            if ( amount <= 100000f )
            {
                transactions++;
                current_balance += amount;
                System.out.println("\nSuccessfully Deposited");
                String str = amount + " Rs deposited\n";
                transactionHist = transactionHist.concat(str);
            }
            else
            {
                System.out.println("\nSorry...Limit is 55000.00");
            }
        }
        catch ( Exception e)
        {
        }
    }
    public void Moneytransfer()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Receipent's Name - ");
        String receipent = sc.nextLine();
        System.out.print("\nEnter amount to transfer - ");
        float amount = sc.nextFloat();
        try
        {
            if ( current_balance >= amount )
            {
                if ( amount <= 50000f )
                {
                    transactions++;
                    current_balance -= amount;
                    System.out.println("\nSuccessfully Transfered to " + receipent);
                    String str = amount + " Rs transfered to " + receipent + "\n";
                    transactionHist = transactionHist.concat(str);
                }
                else
                {
                    System.out.println("\nSorry...Limit is 50000.00");
                }
            }
            else
            {
                System.out.println("\nInsufficient Balance");
            }
        }
        catch ( Exception e) {
        }
    }

    public void BalanceInquiry() {
        System.out.println("\n" + current_balance + " Rs");
    }

    public void TransHistory() {
        if ( transactions == 0 ) {
            System.out.println("\nEmpty");
        }
        else {
            System.out.println("\n" + transactionHist);
        }
    }
}
class AtmInterface
{
    public static int takeIntegerInput(int limit)
    {
        int input = 0;
        boolean flag = false;

        while ( !flag ) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if ( flag && input > limit || input < 1 ) {
                    System.out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            }
            catch ( Exception e ) {
                System.out.println("Enter only integer value");
                flag = false;
            }
        };
        return input;
    }
    public static void main(String[] args) {

        System.out.println("\n            ~ATM INTERFACE~           \n");
        System.out.println("1.Get started with our banking services \n2.Exit");
        System.out.print("Enter Your Choice - ");
        int choice = takeIntegerInput(2);

        if ( choice == 1 ) {
            BankAccount b = new BankAccount();
            b.Registration();
            while(true) {
                System.out.println("\n1.Log in to your account \n2.Exit");
                System.out.print("Enter Your Choice - ");
                int ch = takeIntegerInput(2);
                if ( ch == 1 ) {
                    if (b.login()) {
                        System.out.println("\n\n WELCOME BACK " + b.name );
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1.Withdraw Money \n2.Deposit Money \n3.Money Transfer \n4.Balance Inquiry \n5.Transaction History \n6.Exit");
                            System.out.print("\nEnter Your Choice - ");
                            int c = takeIntegerInput(6);
                            switch(c) {
                                case 1:
                                    b.WithdrawMoney();
                                    break;
                                case 2:
                                    b.DepositMoney();
                                    break;
                                case 3:
                                    b.Moneytransfer();
                                    break;
                                case 4:
                                    b.BalanceInquiry();
                                    break;
                                case 5:
                                    b.TransHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                }
                else {
                    System.exit(0);
                }
            }
        }
        else {
            System.exit(0);
        }
    }
}
