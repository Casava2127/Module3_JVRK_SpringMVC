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
    private String empAddress; // Không cần @DateTimeFormat
    private String empPhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Áp dụng định dạng cho bod
    private Date bod;

    private int deptId;
    private String empRate;
    private int status;
}
