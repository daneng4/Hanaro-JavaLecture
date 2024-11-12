package JavaLecture.exam2.company;

public class PermanentWorker extends Worker {

    private int salary;

    public PermanentWorker(String name, int salary) {
        super(name);
        this.salary = salary;

    }

    @Override
    public int getPay() {
        return this.salary;
    }

    @Override
    public void showSalaryInfo(String name) {
        if (this.name.equals(name)) {
            System.out.printf("사원 %s의 급여는 %,d원\n", this.name, this.getPay());
        }
    }
}
