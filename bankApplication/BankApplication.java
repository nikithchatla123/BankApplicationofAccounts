


class Account{
    int account_number;
    String name;
    String password;
    int balance;
    int count;

    public Account(int account_number,String name,String password,int balance)
    {
        this.account_number=account_number;
        this.name=name;
        this.password=password;
        this.balance=balance;
        this.count=0;
    }

    public int login(String password)
    {
       if(this.password==password)
       {
       System.out.println("Logged into your account : "+this.account_number);    
       return 1;
       }
       else
       {
       System.out.println("Entered Wrong Password");    
       return 0;
       }
    }

    public void check_balance(String password)
    {
      int check=login(password); 
       if(check==1)
       System.out.println("Account Balance is : "+this.balance);
    }

    public void credit_or_debit(int num,String password)
    {
        int check=login(this.password);
       if(check==1)
       {
        if(num<0)
        {
            num=num*-1;
            if(this.balance>num)
            {
            this.balance-=num;
            System.out.println("Money debited from your account");
            System.out.println("Balance after the transaction is : "+this.balance);
            this.count+=1;
            System.out.println("Number of credit/debit operations performed from the Account "+this.account_number+" are "+this.count);
            }
            else if(this.balance<num)
            {
                System.out.println("Insufficient amount in your account");
            }
        }
        else 
        {
            this.balance+=num;
            System.out.println("Money credited to your account");
            System.out.println("Balance after the transaction is : "+this.balance);
            this.count+=1;
        }
      }
    }

    public void transfer_between_accounts(Account obj,String password,int num)
    {
        int check=login(password);
        if(check==1)
        {
            if(num<=this.balance)
            {
                this.balance-=num;
                obj.balance+=num;
                this.count+=1;
                obj.count+=1;
                //Considering transfering to another account as crediting in that account and incrementing credit/debit counter in that account
                System.out.println("Transaction Successful");
                System.out.println("Amount of "+num+" Transferred to Account Number : "+obj.account_number);
                System.out.println("Balance after the Transaction : "+this.balance);
                System.out.println("Number of credit/debit operations performed from the Account "+this.account_number+" are "+this.count);
                System.out.println("Number of credit/debit operations performed from the Account "+obj.account_number+" are "+obj.count);
            }
            else
            {
               System.out.println("Insufficient balance in your account");
            }
        }
    }

}


public class BankApplication {
    public static Account arr[]= new Account[10];

    public static int find_account(int account_number)
    {
        int index=0;
        for(;index<5;index++)
        {
            if(arr[index].account_number==account_number)
            break;
            else if(arr[index].account_number!=account_number && index==4)
            {
            System.out.println("Account not found");
            return -1;
            }
        }
        return index;
    }

    public static void operations(int account_number,String password,int ope)
    {
     int index=find_account(account_number);
     if(index>=0)
     {
      if(ope==1)// For Checking Balance //
      {
      arr[index].check_balance(password);
      System.out.println("");
      }
      else if(ope==2) // For credit/debit operations //
      {
          int num=1000;// Hardcoding the input //
          //considering positive 
          arr[index].credit_or_debit(num, password);
          System.out.println("");
      }
      else if(ope==3)
      {
          int acn=1004;// Hardcoing the input //
          int indx=find_account(acn);
          arr[index].transfer_between_accounts(arr[indx],password,1000);// Transfering an amount of 1000 rupees
          System.out.println("");
      }
    }
    }
    public static void main(String[] args) {
        //creating 5 accounts____
        arr[0] = new Account(1001,"John","1234",2000);
        arr[1] = new Account(1002,"Rohith","1234",4000);
        arr[2] = new Account(1003,"Raju","1234",3000);
        arr[3] = new Account(1004,"Lalith","1234",5000);
        arr[4] = new Account(1005,"Karl","1234",1000);
        //_____
        operations(1001,"1234",1);
        operations(1002,"1234",2);
        operations(1003,"1234",3);
    }
}