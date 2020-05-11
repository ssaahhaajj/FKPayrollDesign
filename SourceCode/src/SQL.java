import java.sql.*;

public class SQL {

    public boolean deleteEmp(Employee emp) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("DELETE * FROM Employee WHERE EmpID="+emp.getEmpID()+";");
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }

    public boolean deleteUnion(Employee emp) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("DELETE * FROM Union WHERE MemberID="+emp.getEmpID()+";");
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }

    public Employee getEmpDetails(int empID) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();

            ResultSet rs;
            Employee emp=new Employee();

            rs=stmt.executeQuery("SELECT * FROM Employee WHERE EmpID="+empID+";");
            emp.setEmpID(rs.getInt(1));
            emp.setEmpName(rs.getString(2));
            emp.setEmpContactNo(rs.getString(3));
            emp.setEmpAddress(rs.getString(4));
            emp.setEmpEmail(rs.getString(5));
            emp.setEmpAccountNo(rs.getString(6));
            emp.setEmpBankName(rs.getString(7));
            emp.setLeaves(rs.getInt(8));

            con.close();
            return emp;

        }catch(Exception e)
        {
            return null;
        }
    }

    public void addSalary(Employee emp)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="INSERT INTO ";
            if(emp.getSalaryInstance() instanceof Hourly)
                sqlStat+="Hourly_Salary ";
            else sqlStat+="Flat_Salary ";
            sqlStat += "VALUES ( ";
            sqlStat += Integer.toString(emp.getEmpID());
            sqlStat += ", ";
            sqlStat += emp.getSalary();
            sqlStat += ", ";
            sqlStat += emp.getSalaryRate();
            sqlStat += ");";

            stmt.executeUpdate(sqlStat);
            con.close();
        }catch(Exception e)
        { ;}
    }

    public void addLogin(int empID, String role, String password) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="INSERT INTO Login VALUES (";

            sqlStat+=Integer.toString(empID);
            sqlStat+=", \"";
            sqlStat+=role;
            sqlStat+="\", \"";
            sqlStat+=password;
            sqlStat+="\");";

            stmt.executeUpdate(sqlStat);
            con.close();
        }catch(Exception e)
        { ;}
    }

    public boolean checkLogin(int inputID, String role, String inputPassword) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="SELECT * FROM Login WHERE EmpID=" + inputID + "Role=" + role + "Password="+inputPassword+";";
            ResultSet rs=stmt.executeQuery("SELECT * FROM Login WHERE EmpID="+inputID);
            if(rs.next())
            {
                con.close();
                return true;
            }
            else {
                con.close();
                return false;
            }
        }catch(Exception e)
        { return false; }
    }

    public Admin getAdmin(int adminID) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();

            ResultSet rs;
            Admin admin=new Admin();

            rs=stmt.executeQuery("SELECT * FROM Employee WHERE AdminID="+adminID);
            admin.setAdminID(rs.getInt(1));
            admin.setContactNo(rs.getString(2));
            admin.setEmail(rs.getString(3));

            con.close();
            return admin;

        }catch(Exception e)
        {
            return null;
        }
    }

    public boolean addEmployee(Employee newEmp) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="INSERT INTO Employee VALUES (";

            sqlStat+=newEmp.getEmpID();
            sqlStat+=", \"";
            sqlStat+=newEmp.getEmpName();
            sqlStat+="\", \"";
            sqlStat+=newEmp.getEmpContactNo();
            sqlStat+="\", \"";
            sqlStat+=newEmp.getEmpAddress();
            sqlStat+="\", \"";
            sqlStat+=newEmp.getEmpEmail();
            sqlStat+="\", \"";
            sqlStat+=newEmp.getAccountNo();
            sqlStat+="\", \"";
            sqlStat+=newEmp.getBankName();
            sqlStat+="\",";
            sqlStat+=newEmp.getLeaves();
            sqlStat+=")";

            stmt.executeUpdate(sqlStat);
            if(newEmp.getSalaryInstance()!=null)
            {
                addSalary(newEmp);
            }
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }

    public boolean updateEmpName(Employee emp) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE Employee SET Name=\""+emp.getEmpName()+"\" where id="+emp.getEmpID()+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }
    public boolean updateEmpContactNo(Employee emp) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE Employee SET ContactNo=\""+emp.getEmpContactNo()+"\" where id="+emp.getEmpID()+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }
    public boolean updateEmpAddress(Employee emp) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE Employee SET Address=\""+emp.getEmpAddress()+"\" where id="+emp.getEmpID()+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }
    public boolean updateEmpEmail(Employee emp) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE Employee SET Email=\""+emp.getEmpEmail()+"\" where id="+emp.getEmpID()+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }
    public boolean updateEmpAccountNo(Employee emp) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE Employee SET AccountNo=\""+emp.getAccountNo()+"\" where id="+emp.getEmpID()+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }

    public boolean updateEmpBankName(Employee emp) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE Employee SET BankName=\""+emp.getBankName()+"\" where id="+emp.getEmpID()+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }

    public boolean updateEmpLeaves(Employee emp) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE Employee SET Leaves=\""+emp.getLeaves()+"\" where id="+emp.getEmpID()+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }

    public boolean updateRate(Salary salaryInstance,int empID) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="UPDATE ";
            if(salaryInstance instanceof Hourly)
                sqlStat+=" Hourly_Salary SET HourlyRate=";
            else sqlStat+=" Flat_Salary SET MonthlySalary=";

            sqlStat+=salaryInstance.getRate()+" NetSalary="+salaryInstance.getSalary()+" WHERE id="+empID+";";
            int result=stmt.executeUpdate(sqlStat);
            con.close();
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }

    public void addUnionMember(int empID, int charges, String member) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="INSERT INTO Union VALUES ( ";
            sqlStat += Integer.toString(empID);
            sqlStat += ", ";
            sqlStat += charges;
            sqlStat += ", '";
            sqlStat += member;
            sqlStat += "');";

            stmt.executeUpdate(sqlStat);
            con.close();
        }catch(Exception e)
        { ;}
    }

    public void addCommission(int empID, int receiptID, Date date, int amount) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="INSERT INTO Commission_System VALUES ( ";
            sqlStat += Integer.toString(empID);
            sqlStat += ", ";
            sqlStat += Integer.toString(receiptID);
            sqlStat += ", ";
            sqlStat += date;
            sqlStat += ", ";
            sqlStat += Integer.toString(amount);
            sqlStat += ");";

            stmt.executeUpdate(sqlStat);
            con.close();
        }catch(Exception e)
        { ;}
    }

    public void addEmpTimeCard(int empID, java.util.Date date, float time) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            String sqlStat="INSERT INTO Attendance VALUES ( ";
            sqlStat += Integer.toString(empID);
            sqlStat += ", ";
            sqlStat += date;
            sqlStat += ", ";
            sqlStat += Float.toString(time);
            sqlStat += ");";

            stmt.executeUpdate(sqlStat);
            con.close();
        }catch(Exception e)
        { ;}
    }

    public void updateSalaryWeekly(Employee emp,Date date) {
        try{
            float rate=emp.getSalaryInstance().getRate();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FKPayrollDesign","root","root");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM Attendance WHERE empID= "+emp.getEmpID()+" AND Date= "+date+ ";");
            if(rs.next())
            {
                int hoursWorked=rs.getInt(3);
                emp.getSalaryInstance().addSalary(rate*hoursWorked);
            }
            String sqlStat="UPDATE Hourly_Salary SET HourlyRate=";
            sqlStat+=emp.getSalaryInstance().getRate()+" NetSalary="+emp.getSalaryInstance().getSalary()+" WHERE id="+emp.getEmpID()+";";

            int result=stmt.executeUpdate(sqlStat);
            con.close();

        }catch(Exception e)
        { ;}
    }

    public void processSalary(Employee emp) {
        emp.getSalaryInstance().deductSalary(emp.getSalary());
        updateRate(emp.getSalaryInstance(),emp.getEmpID());
    }

}
