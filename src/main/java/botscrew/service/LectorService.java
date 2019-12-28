package botscrew.service;

import botscrew.entity.Degree;
import botscrew.entity.Lector;
import botscrew.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LectorService {
    private final LectorRepository lectorRepository;

    public void save(Lector lector) { lectorRepository.save(lector); }

    public void delete(int id) {
        Lector lector = lectorRepository.findById(id);
        if(lector == null)
            System.out.println("Incorrect departmentName");
        else
            lectorRepository.delete(lector);
    }

    public Integer countDegreeByDepartment(Degree degree, int departmentId) {
        return lectorRepository.countDegreeByDepartment(String.valueOf(degree), departmentId);
    }
}
