package botscrew.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    private Integer id;
    private String departmentName;
    private String headOfDepartmentName;
    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Lector> lectors = new ArrayList<>();
}
