class Flat extends Salary {
    private float salary=0f;
    private float monthlyRate;

    public Flat(float monthlyRate) {
        this.monthlyRate=monthlyRate;
    }

    @Override
    float getSalary() {
        return salary;
    }

    @Override
    float getRate() {
        return monthlyRate;
    }

    @Override
    void setRate(float newRate) {
        monthlyRate=newRate;
    }

    @Override
    boolean addSalary(float amount) {
        salary+=amount;
        return true;
    }

    @Override
    boolean deductSalary(float amount) {
        if(salary>amount) {
            salary-=amount;
            return true;
        }
        else return false;
    }

    public boolean updateSalary(float rate) {
        monthlyRate = rate;
        return true;
    }

    public String criteriaName()
    {
        return "Flat";
    }

    @Override
    public String toString() {
        return "Flat{" +
                " Current Salary=" + salary +
                ", Monthly Salary=" + monthlyRate +
                '}';
    }
}
