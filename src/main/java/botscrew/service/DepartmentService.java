package botscrew.service;

import botscrew.entity.Degree;
import botscrew.entity.Department;
import botscrew.entity.Lector;
import botscrew.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LectorService lectorService;

    public Department findByDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }

    public void save(Department department) {
        departmentRepository.save(department);
    }

    public void delete(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if(department == null)
            System.out.println("Incorrect departmentName");
        else
            departmentRepository.delete(department);
    }

    public void getHeadOfDepartment(String departmentName) {
        String headOfDepartmentName = departmentRepository.getHeadOfDepartmentName(departmentName);
        if(headOfDepartmentName == null)
            System.out.println("Incorrect departmentName");
        else
            System.out.println("Head of " + departmentName + " department is " + headOfDepartmentName);
    }

    public void getDepartmentStatistic(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if(department == null)
            System.out.println("Incorrect departmentName");
        else {
            Integer assistantsCount = lectorService.countDegreeByDepartment(Degree.ASSISTANT, department.getId());
            Integer associateProfessorCount = lectorService.countDegreeByDepartment(Degree.ASSOCIATE_PROFESSOR, department.getId());
            Integer professorCount = lectorService.countDegreeByDepartment(Degree.PROFESSOR, department.getId());

            System.out.println("assistants - " + assistantsCount);
            System.out.println("associateProfessors - " + associateProfessorCount);
            System.out.println("professors - " + professorCount);
        }
    }

    public void getAverageSalary(String departmentName) {
        Integer averageSalary = departmentRepository.averageSalary(departmentName);
        if(averageSalary == null)
            System.out.println("Incorrect departmentName");
        else
            System.out.println("The average salary of " + departmentName + " is " + averageSalary);
    }

    public void getCountOfEmployee(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if(department == null)
            System.out.println("Incorrect departmentName");
        else {
            System.out.println(departmentRepository.countEmployee(department.getId()));
        }
    }

    public void globalSearch(String template) {
        for (Department department : departmentRepository.findAll()) {
            for (Lector lector : department.getLectors()) {
                if(lector.getFirstName().contains(template) || lector.getLastName().contains(template))
                    System.out.println(lector.getFirstName() + " " + lector.getLastName());
            }
            if(department.getDepartmentName().contains(template))
                System.out.println(department.getDepartmentName());
        }
    }
}
