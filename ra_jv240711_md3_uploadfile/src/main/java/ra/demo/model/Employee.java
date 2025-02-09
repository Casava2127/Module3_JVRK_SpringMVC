package ra.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @Column(name = "emp_id", columnDefinition = "char(5)")
    private String empId;
    @Column(name = "emp_name", columnDefinition = "varchar(100)", nullable = false)
    private String empName;
    @Column(name = "emp_avatar", columnDefinition = "text")
    private String avatarLink;
}
