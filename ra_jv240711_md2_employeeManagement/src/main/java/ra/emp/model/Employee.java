package ra.emp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private String empId;
    private String empName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bod;
    private int deptId;
    private String deptName;
    private int status;
}
