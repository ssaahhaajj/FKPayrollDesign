import java.util.*;

public class Admin {
    // Internal Variables
    private static int ID;
    private boolean permission=false;

    // Admin Variables
    private int adminID;
    private String password="";
    private String contactNo="";
    private String email="";

    public Admin()
    {

    }
    public Admin(String password) {
        ID++;
        this.adminID=ID;
        SQL sql=new SQL();
        sql.addLogin(adminID,"Admin",password);
    }

    public boolean getPermission()
    {
        return this.permission;
    }

    public boolean login(int inputID, String inputPassword) {
        SQL sql=new SQL();
        if(sql.checkLogin(adminID,"Admin",inputPassword))
        {
            Admin other=sql.getAdmin(inputID);
            this.permission=true;
            return true;
        }
        else return false;
    }

    public boolean logout()
    {
        if(this.permission == true) {
            permission = false;
            return true;
        }
        else return false;
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

    public boolean setContactNo(String contactNo) {
        if(this.permission == true) {
            this.contactNo = contactNo;
            return true;
        }
        else return false;
    }

    public boolean setAdminID(int adminID) {
        if(this.permission == true) {
            this.adminID = adminID;
            return true;
        }
        else return false;
    }

    public boolean setEmail(String email) {
        if(this.permission == true) {
            this.email = email;
            return true;
        }
        else return false;
    }

    public String getContactNo() {
        if(this.permission == true) {
            return contactNo;
        }
        else return "$Denied";
    }

    public String getEmail() {
        if(this.permission == true) {
            return email;
        }
        else return "$Denied";
    }

    public int getAdminID() {
        if(this.permission == true) {
            return adminID;
        }
        else return -1;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "Admin ID = " + adminID +
                ", Contact No = '" + contactNo + '\'' +
                ", Email ID = '" + email + '\'' +
                '}';
    }

    private String passwordGenerator()
    {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 16) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String tempPassword = salt.toString();
        return tempPassword;
    }


    public boolean addEmployee()
    {
        if(this.permission)
        {
            Scanner scan = new Scanner(System.in);
            boolean check;
            Employee newEmp = new Employee(passwordGenerator());

            System.out.print("Enter the Employee Name(Optional): ");
            check=newEmp.setEmpName(scan.next());
            if(check)
                System.out.println("Added Successfully!");
            else System.out.println("Unable to add!");

            System.out.print("Enter the Employee Contact No(Optional): ");
            check=newEmp.setEmpContactNo(scan.next());
            if(check)
                System.out.println("Added Successfully!");
            else System.out.println("Unable to add!");

            System.out.print("Enter the Employee Email ID(Optional): ");
            check=newEmp.setEmpEmail(scan.next());
            if(check)
                System.out.println("Added Successfully!");
            else System.out.println("Unable to add!");

            System.out.print("Enter the Employee Address(Optional): ");
            check=newEmp.setEmpAddress(scan.next());
            if(check)
                System.out.println("Added Successfully!");
            else System.out.println("Unable to add!");

            System.out.print("Enter the Employee Salary Criteria (Flat/Hourly) (Optional): ");
            String criteria=scan.next();
            check=true;
            if(criteria!="" && criteria!=null)
            {
                if(criteria.equalsIgnoreCase("Flat"))
                System.out.print("Enter the Monthly Rate: ");
                else System.out.println("Enter the Hourly Rate: ");

                float rate=scan.nextFloat();
                check=newEmp.setSalaryCriteria(criteria, rate);
            }

            if(check)
                System.out.println("Added Successfully!");
            else System.out.println("Unable to add!");

            System.out.print("Enter the Employee Bank Name(Optional): ");
            check=newEmp.setEmpBankName(scan.next());
            if(check)
                System.out.println("Added Successfully!");
            else System.out.println("Unable to add!");

            System.out.print("Enter the Employee Account No(Optional): ");
            check=newEmp.setEmpAccountNo(scan.next());
            if(check)
                System.out.println("Added Successfully!");
            else System.out.println("Unable to add!");

            SQL sql=new SQL();
            boolean addFlag=sql.addEmployee(newEmp);
            boolean successful=newEmp.logout();

            if(addFlag && successful)
                return true;
            else return false;
        }
        return false;
    }

    public boolean ModifyEmpDetails()
    {
        if(this.permission == true)
        {
            Scanner scan = new Scanner(System.in);
            int empID;
            System.out.print("Enter the Employee ID: ");
            empID=scan.nextInt();

            SQL sql=new SQL();
            Employee emp = sql.getEmpDetails(empID);

            if(emp==null)
                return false;

            boolean loop=true,sqlFlag=true;

            while(loop && sqlFlag) {
                System.out.println("Current Details: " + emp.toString());
                System.out.println("Please Enter the Choice Number for editing details:");
                System.out.println("1. Employee Name: ");
                System.out.println("2. Employee Contact No: ");
                System.out.println("3. Employee Email ID: ");
                System.out.println("4. Employee Address: ");
                System.out.println("5. Employee Bank Name: ");
                System.out.println("6. Employee Account No: ");
                System.out.println("7. Employee Salary: ");
                System.out.println("8. Exit ");

                int choice=scan.nextInt();

                switch(choice)
                {
                    case 1 : {
                                System.out.print("Please Enter a new name: ");
                                emp.setEmpName(scan.next());
                                sqlFlag=sql.updateEmpName(emp);
                                break;
                                }
                    case 2 : {
                                System.out.print("Please Enter a new Contact no: ");
                                emp.setEmpContactNo(scan.next());
                                sqlFlag=sql.updateEmpContactNo(emp);
                                break;
                            }
                    case 3 : {
                                System.out.print("Please Enter a new Email ID: ");
                                emp.setEmpEmail(scan.next());
                                sqlFlag=sql.updateEmpEmail(emp);
                                break;
                            }
                    case 4 : {
                                System.out.print("Please Enter a new Address: ");
                                emp.setEmpAddress(scan.next());
                                sqlFlag=sql.updateEmpAddress(emp);
                                break;
                            }
                    case 5 : {
                                System.out.print("Please Enter a new Bank Name: ");
                                emp.setEmpBankName(scan.next());
                                sqlFlag=sql.updateEmpBankName(emp);
                                break;
                            }
                    case 6 : {
                                System.out.print("Please Enter a new Account No: ");
                                emp.setEmpAccountNo(scan.next());
                                sqlFlag=sql.updateEmpAccountNo(emp);
                                break;
                            }
                    case 7 : {
                                System.out.println("Your current Salary Criteria is "+ emp.getSalaryCriteria());
                                System.out.print("Please Enter a new Salary Rate: ");
                                emp.setRate(scan.nextFloat());
                                sqlFlag=sql.updateRate(emp.getSalaryInstance(),emp.getEmpID());
                                break;
                            }
                    case 8 : {
                                loop=false;
                                break;
                            }

                    default : System.out.println("You entered a wrong choice please renter it!!");
                }
            }

            boolean successful=emp.logout();

            if(successful && sqlFlag)
                return true;
            else return false;
        }
        return false;
    }

    public boolean deleteEmployee()
    {
        if(this.permission == true) {
            Scanner scan = new Scanner(System.in);
            int empID;
            System.out.print("Enter the Employee ID: ");
            empID=scan.nextInt();

            SQL sql=new SQL();
            Employee emp = sql.getEmpDetails(empID);

            boolean sqlflag = sql.deleteEmp(emp);
            boolean successful=emp.logout();

            if(successful && sqlflag)
                return true;
            else return false;
        }
        else return false;
    }

    public <T extends Employee>
    void calculatePayroll(T emp)
    {
        Salary sal=emp.getSalaryInstance();
        if(sal == null)
            return;
        if(sal instanceof Hourly)
        {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

            Date date = calendar.getTime();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            SQL sql=new SQL();
            sql.updateSalaryWeekly(emp, (java.sql.Date) date);
            if(dayOfWeek==5)
            {
                sql.processSalary(emp);
                System.out.println("Money has been successfully transferred to the Employee");
            }
        }
        else{
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            SQL sql=new SQL();
            if(dayOfMonth==30)
            {
                sql.processSalary(emp);
                System.out.println("Money has been successfully transferred to the Employee");
            }
        }
    }
}
