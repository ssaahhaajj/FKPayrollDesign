public abstract class Salary<T extends Employee> {
    abstract float getSalary();
    abstract float getRate();
    abstract void setRate(float newRate);
    abstract boolean addSalary(float arg);
    abstract boolean deductSalary(float amount);
    abstract String criteriaName();
}
