class Hourly extends Salary{
    private float salary=0f;
    private float hourlyRate;

    public Hourly(float rate)
    {
        this.hourlyRate=rate;
    }

    @Override
    float getSalary() {
        return salary;
    }

    @Override
    float getRate() {
        return hourlyRate;
    }

    @Override
    void setRate(float newRate) {
        hourlyRate=newRate;
    }

    @Override
    boolean addSalary(float hours) {
        if(hours>8)
        {
            salary += (8 * hourlyRate) + (hours - 8) * 1.5 * hourlyRate;
        }
        else salary+=(8*hours);
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
        hourlyRate=rate;
        return true;
    }

    public String criteriaName()
    {
        return "Hourly";
    }

    @Override
    public String toString() {
        return "Hourly{" +
                " Current Salary=" + salary +
                ", Hourly Rate=" + hourlyRate +
                '}';
    }
}
