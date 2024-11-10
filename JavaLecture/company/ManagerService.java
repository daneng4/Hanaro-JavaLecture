package JavaLecture.company;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {

    private List<Worker> workers;

    public ManagerService() {
        workers = new ArrayList<>();
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public void showAllSalaryInfo() {
        for (Worker worker : workers) {
            worker.showSalaryInfo(worker.name);
        }
    }

    public void showSalaryInfo(String name) {
        for (Worker worker : workers) {
            if (worker.name.equals(name)) {
                worker.showSalaryInfo(name);
            }
        }
    }

    public void showTotalSalary() {
        int total = 0;
        for (Worker worker : workers) {
            total += worker.getPay();
        }
        System.out.printf("모든 사원의 급여 총액은 : %,d원\n", total);
    }
}
