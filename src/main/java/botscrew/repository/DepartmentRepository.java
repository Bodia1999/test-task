package botscrew.repository;

import botscrew.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByDepartmentName(String departmentName);

    @Query(value = "SELECT COUNT(ld.lector_id) FROM lector_department ld " +
            "WHERE department_id = :departmentId", nativeQuery = true)
    Integer countEmployee(@Param("departmentId") int departmentId);

    @Query(
            value = "SELECT AVG(l.salary) FROM lector l" +
                    " JOIN lector_department ld on l.id = ld.lector_id" +
                    " JOIN department d on ld.department_id = d.id" +
                    " WHERE d.department_name = :departmentName",
            nativeQuery = true
    )
    Integer averageSalary(@Param("departmentName") String departmentName);

    @Query("SELECT d.headOfDepartmentName FROM Department d WHERE d.departmentName = :departmentName")
    String getHeadOfDepartmentName(@Param("departmentName") String departmentName);
}
