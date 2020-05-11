public class Union<T extends Employee> {
    private int memberID;
    static public int registration=500;
    static private int charges;
    private String role;

    public Union(T emp)
    {
        memberID=emp.getEmpID();
        role="Member";
    }

    public boolean changeRole(String role)
    {
        if(role.equals("Member") || role.equals("Leader"))
        {
            this.role=role;
            return true;
        }
        return false;
    }

    public int getCharges()
    {
        return charges;
    }
}
