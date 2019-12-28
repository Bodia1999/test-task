package botscrew;

import botscrew.entity.Degree;
import botscrew.entity.Department;
import botscrew.entity.Lector;
import botscrew.service.DepartmentService;
import botscrew.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
@EntityScan(basePackages = "botscrew.entity")
public class BotscrewApplication {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    LectorService lectorService;

    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        SpringApplication.run(BotscrewApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        createLectorsAndDepartments();

        System.out.println("Commands examples:");
        System.out.println("1.Who is head of department {department_name}");
        System.out.println("2.Show {department_name} statistic");
        System.out.println("3.Show the average salary for department {department_name}");
        System.out.println("4.Show count of employee for {department_name}");
        System.out.println("5.Global search by {template}");
        System.out.println("For exit enter - 0");

        while (true) {
            System.out.println("Enter command:");
            String action = bufferedReader.readLine();

            if(action.equals("0"))
                break;

            //Who is head of department {department_name}
            else if(action.startsWith("Who is head of department ")) {
                String departmentName = action.substring(26);
                departmentService.getHeadOfDepartment(departmentName);
            }

            //Show {department_name} statistic
            else if(action.startsWith("Show") && action.endsWith("statistic")) {
                String[] split = action.split(" ");
                departmentService.getDepartmentStatistic(split[1]);
            }

            //Show the average salary for department {department_name}
            else if(action.startsWith("Show the average salary for department ")) {
                String[] split = action.split(" ");
                departmentService.getAverageSalary(split[6]);
            }

            //Show count of employee for {department_name}
            else if(action.startsWith("Show count of employee for ")) {
                String[] split = action.split(" ");
                departmentService.getCountOfEmployee(split[5]);
            }

            //Global search by {template}
            else if(action.startsWith("Global search by ")) {
                String[] split = action.split(" ");
                departmentService.globalSearch(split[3]);
            }
            else System.out.println("Incorrect input");
        }
    }

    private void createLectorsAndDepartments() {
        //create departments
        Department department1 = new Department();
        department1.setId(1);
        department1.setDepartmentName("Math");

        Department department2 = new Department();
        department2.setId(2);
        department2.setDepartmentName("Physics");

        Department department3 = new Department();
        department3.setId(3);
        department3.setDepartmentName("English");

        //create lectors
        Lector lector1 = new Lector();
        lector1.setId(1);
        lector1.setFirstName("Oleh");
        lector1.setLastName("Bordun");
        lector1.setDegree(Degree.PROFESSOR);
        lector1.setSalary(20000);

        Lector lector2 = new Lector();
        lector2.setId(2);
        lector2.setFirstName("Igor");
        lector2.setLastName("Koman");
        lector2.setDegree(Degree.PROFESSOR);
        lector2.setSalary(15000);

        Lector lector3 = new Lector();
        lector3.setId(3);
        lector3.setFirstName("Stanislav");
        lector3.setLastName("Kostenko");
        lector3.setDegree(Degree.ASSISTANT);
        lector3.setSalary(8000);

        Lector lector4 = new Lector();
        lector4.setId(4);
        lector4.setFirstName("Alex");
        lector4.setLastName("Kushnir");
        lector4.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lector4.setSalary(10000);

        Lector lector5 = new Lector();
        lector5.setId(5);
        lector5.setFirstName("Maxum");
        lector5.setLastName("Petrenko");
        lector5.setDegree(Degree.ASSISTANT);
        lector5.setSalary(7500);

        Lector lector6 = new Lector();
        lector6.setId(6);
        lector6.setFirstName("Illya");
        lector6.setLastName("Barna");
        lector6.setDegree(Degree.PROFESSOR);
        lector6.setSalary(20000);

        Lector lector7 = new Lector();
        lector7.setId(7);
        lector7.setFirstName("Roman");
        lector7.setLastName("Karbovnuk");
        lector7.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lector7.setSalary(14000);

        Lector lector8 = new Lector();
        lector8.setId(8);
        lector8.setFirstName("Rostuslav");
        lector8.setLastName("Stigaylo");
        lector8.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lector8.setSalary(13500);

        Lector lector9 = new Lector();
        lector9.setId(9);
        lector9.setFirstName("Pavlo");
        lector9.setLastName("Martunyk");
        lector9.setDegree(Degree.ASSISTANT);
        lector9.setSalary(8000);

        //set set head Of departments
        department1.setHeadOfDepartmentName(lector1.getFirstName() + " " + lector1.getLastName());
        department2.setHeadOfDepartmentName(lector2.getFirstName() + " " + lector2.getLastName());
        department3.setHeadOfDepartmentName(lector6.getFirstName() + " " + lector6.getLastName());

        //add departments to lectors
        lector1.getDepartments().add(department1);
        lector2.getDepartments().add(department2);
        lector3.getDepartments().add(department1);
        lector4.getDepartments().add(department2);
        lector5.getDepartments().add(department2);
        lector6.getDepartments().add(department3);
        lector7.getDepartments().add(department1);
        lector8.getDepartments().add(department1);
        lector9.getDepartments().add(department3);

        //add lectors to departments
        department1.getLectors().add(lector1);
        department1.getLectors().add(lector3);
        department1.getLectors().add(lector7);
        department1.getLectors().add(lector8);
        department2.getLectors().add(lector2);
        department2.getLectors().add(lector4);
        department2.getLectors().add(lector5);
        department3.getLectors().add(lector6);
        department3.getLectors().add(lector9);

        //save lectors
        lectorService.save(lector1);
        lectorService.save(lector2);
        lectorService.save(lector3);
        lectorService.save(lector4);
        lectorService.save(lector5);
        lectorService.save(lector6);
        lectorService.save(lector7);
        lectorService.save(lector8);
        lectorService.save(lector9);

        //save departments
        departmentService.save(department1);
        departmentService.save(department2);
        departmentService.save(department3);
    }
}
