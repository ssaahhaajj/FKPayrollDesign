import java.util.Scanner;
import java.util.Date;
public class Employee {
    // Internal Variables
    private static int ID;
    private boolean permission=false;

    // Employee Variables
    private int empID;
    private String password="";
    private String empName="";
    private String empContactNo="";
    private String empEmail="";
    private Salary empSal=null;
    private ModeOfPayment mode=null;
    private String address="";
    private int leaves=14;
    private String accountNo=null;
    private String bankName=null;
    private Union union=null;
    private int commissionRate=0;

    //////////////////////////////// Constructors
    public Employee()
    {
    }
    public Employee(String password)
    {
        ID++;
        this.empID=ID;
        SQL sql=new SQL();
        sql.addLogin(empID,"Employee",password);
    }

    //////////////////////////////// Account Login
    public boolean login(int inputID, String password){
        SQL sql=new SQL();
        if(sql.checkLogin(inputID,"Employee",password))
        {
            Employee other=sql.getEmpDetails(inputID);

            this.empID=other.ID;
            this.empName = other.getEmpName();
            this.empContactNo = other.getEmpContactNo();
            this.empEmail = other.getEmpEmail();
            this.empSal = other.getSalaryInstance();
            this.accountNo=other.getAccountNo();
            this.bankName=other.getBankName();
            this.address=other.getEmpAddress();
            this.permission=true;
            this.mode =other.getModeInstance();
            this.permission=true;
            this.password=password;

            return true;
        }
        else return false;
    }

    public boolean logout()
    {
        if(this.permission == true)
        {
            this.permission=false;
            return true;
        }
        return false;
    }

    public boolean resetPassword()
    {
        if(this.permission == true) {

            System.out.print("A OTP has just been sent to your registered mail id!\nPlease enter the OTP here: ");
            Scanner scan = new Scanner(System.in);

            int num = scan.nextInt();     // temporarily it will verify any OTP
            int attempt=3;
            while(attempt>0){

                String pass, passVerify;
                System.out.println("Please Enter the new Password: ");
                pass = scan.next();
                System.out.println("Please Enter the Password again: ");
                passVerify = scan.next();

                if (pass.equals(passVerify)) {
                    System.out.println("Password has been successfully changed!");
                    this.password = pass;
                    return true;
                } else {
                    System.out.println("Your password doesnt match! Please renter password");
                }
                attempt--;
            }
            System.out.println("Attempts exceeded!!");
            return false;
        }
        return false;
    }

    //////////////////////////////// Getters
    public boolean getPermission()
    {
        return this.permission;
    }

    public int getEmpID() {
        return empID;
    }

    public String getEmpName() {
            return empName;
    }

    public String getEmpContactNo() {
            return empContactNo;
    }


    public String getEmpEmail() {
            return empEmail;
    }

    public String getEmpAddress() {
            return address;
    }

    public int getLeaves() {
            return leaves;

    }

    public int getCommissionRate() {
        return commissionRate;
    }

    public String getAccountNo() {
            return accountNo;
    }

    public String getBankName() {
            return bankName;
    }

    //////////////////////////////// Setters
    public boolean setLeaves(int leaves) {
        if(this.permission) {
            this.leaves = leaves;
            SQL sql=new SQL();
            return sql.updateEmpLeaves(this);
        }
        else return false;
    }

    public boolean setEmpName(String empName) {
        if(this.permission == true) {
            this.empName = empName;
            SQL sql=new SQL();
            return sql.updateEmpName(this);
        }
        else return false;
    }

    public boolean setCommissionRate(int commissionRate) {
        if(this.permission == true) {
            this.commissionRate = commissionRate;
            SQL sql=new SQL();
            return true;
        }
        return false;
    }

    public boolean setEmpContactNo(String empContactNo) {
        if(this.permission == true) {
            this.empContactNo = empContactNo;
            SQL sql=new SQL();
            return sql.updateEmpContactNo(this);
        }
        else return false;
    }

    public boolean setEmpAddress(String address) {
        if(this.permission == true) {
            this.address = address;
            SQL sql=new SQL();
            return sql.updateEmpAddress(this);
        }
        else return false;
    }

    public boolean setEmpBankName(String bankName) {
        if(this.permission == true) {
            this.bankName = bankName;
            SQL sql=new SQL();
            return sql.updateEmpBankName(this);
        }
        else return false;
    }

    public boolean setEmpID(int empID) {
        if(this.permission == true) {
            this.empID = empID;
            SQL sql=new SQL();
            return sql.updateEmpBankName(this);
        }
        else return false;

    }

    public boolean setEmpAccountNo(String accountNo) {
        if(this.permission == true) {
            this.accountNo = accountNo;
            SQL sql=new SQL();
            return sql.updateEmpAccountNo(this);
        }
        else return false;
    }

    public boolean setEmpEmail(String empEmail) {
        if(this.permission == true) {
            this.empEmail = empEmail;
            SQL sql=new SQL();
            return sql.updateEmpEmail(this);
        }
        else return false;
    }

    @Override
    public String toString() {
        return "Employee{" +
                " Employee ID = " + empID +
                ", Name = '" + empName + '\'' +
                ", Contact No = '" + empContactNo + '\'' +
                ", Email = '" + empEmail + '\'' +
                ", Salary = " + empSal +
                ", Address = '" + address + '\'' +
                ", Leaves = " + leaves +
                ", Account No = '" + accountNo + '\'' +
                ", Bank Name = '" + bankName + '\'' +
                '}';
    }

    ///////////////////////////// Salary Structure
    public float getSalary()
    {
        return empSal.getSalary();
    }
    public Salary getSalaryInstance()
    {
            return empSal;
    }

    public boolean setSalaryCriteria(String criteria,float rate)
    {
        if(empSal!=null && criteria.equalsIgnoreCase(empSal.criteriaName()))
            return false;
        else {
            if(criteria.equalsIgnoreCase("Flat"))
                empSal=new Flat(rate);
            else empSal=new Hourly(rate);
            return true;
        }
    }

    public float getSalaryRate()
    {
        return empSal.getRate();
    }

    public void setRate(float newRate)
    {
        empSal.setRate(newRate);
        SQL sql=new SQL();
        sql.updateRate(empSal,empID);
    }

    public String getSalaryCriteria()
    {
        return empSal.criteriaName();
    }

    ///////////////////////////// Mode of Payment
    public String getEmpMode()
    {
        return this.mode.getMode();
    }

    public ModeOfPayment getModeInstance()
    {
        return this.mode;
    }
    public String getEmpModeDetails()
    {
        return mode.modeDetails();
    }

    public boolean changeMode(int choice)
    {
//        Scanner scan =new Scanner(System.in);

//        System.out.println("Please select which Mode of payment do you want to select: ");
//        System.out.println("1. By Posting your paycheck");
//        System.out.println("2. By collecting paycheck from Paymaster");
//        System.out.println("3. By Paychecks deposited into your bank account");
//        int choice=scan.nextInt();

        switch(choice)
        {
            case 1 : {
                        if(mode instanceof ByPost)
                        {
                            return false;
                        }
                        mode=new ByPost();
                        break;
                    }
            case 2 : {
                        if(mode instanceof ByPaymaster)
                        {
                            return false;
                        }
                        mode=new ByPaymaster();
                        break;
                    }
            case 3 : {
                        if(mode instanceof ByAccount)
                        {
                            return false;
                        }
                        mode=new ByAccount();
                        break;
                    }
            default:
                System.out.println("Wrong choice attempted!");
        }

        return true;
    }

    /////////////////////////////// Union Member

    public boolean isUnionMember()
    {
        return union==null;
    }

    public boolean unionRegistration()
    {
        if(union!=null)
            return false;
        else {
            union=new Union(this);
            empSal.deductSalary(Union.registration);
            SQL sql=new SQL();
            sql.updateRate(empSal,empID);
            sql.addUnionMember(empID,union.getCharges(),"Member");
            return true;
        }
    }

    public boolean unionWithdraw()
    {
        if(union == null)
            return false;
        // settle bills
        SQL sql=new SQL();
        empSal.deductSalary(union.getCharges());
        sql.updateRate(empSal,empID);
        sql.deleteUnion(this);

        union=null;
        return true;
    }

    public boolean unionChangeRole(String newRole)
    {
        if(union==null)
            return false;
        return union.changeRole(newRole);
    }
    ///////////////////////////////
    public boolean addTimeCard(Date date, float time)
    {
        SQL sql=new SQL();
        sql.addEmpTimeCard(empID,date,time);
        return true;
    }

    public void apply_commission(int receiptID,int amount, Date date)
    {
        SQL sql=new SQL();
        sql.addCommission(empID,receiptID, (java.sql.Date) date,amount);
        empSal.addSalary(commissionRate*amount);
        sql.updateRate(empSal,empID);
    }
}
