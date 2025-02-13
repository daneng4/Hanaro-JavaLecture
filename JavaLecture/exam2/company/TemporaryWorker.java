package JavaLecture.exam2.company;

public class TemporaryWorker extends Worker {

    private int payPerHour;
    private int workTime;

    public TemporaryWorker(String name, int payPerHour, int workTime) {
        super(name);
        this.payPerHour = payPerHour;
        this.workTime = workTime;
    }

    @Override
    public int getPay() {
        return payPerHour * workTime;
    }

    @Override
    public void showSalaryInfo(String name) {
        if (this.name.equals(name)) {
            System.out.printf("사원 %s의 근무시간은 %d시간, 시간 수당은 %,d원 급여는 %,d원\n", this.name, this.workTime,
                this.payPerHour, this.getPay());
        }
    }
}
