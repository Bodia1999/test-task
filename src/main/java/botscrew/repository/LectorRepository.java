package botscrew.repository;

import botscrew.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Integer> {
    Lector findById(int id);

    @Query(
            value = "SELECT COUNT(l.id) FROM lector l" +
                    " JOIN lector_department ld on l.id = ld.lector_id" +
                    " WHERE l.degree = :degree AND ld.department_id = :departmentId",
            nativeQuery = true
    )
    Integer countDegreeByDepartment(@Param("degree") String degree, @Param("departmentId") int departmentId);
}

