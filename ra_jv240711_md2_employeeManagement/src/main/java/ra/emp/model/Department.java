package ra.emp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department {
    private int deptId;
    private String deptName;       // Tên phòng ban
    private String deptDescription; // Mô tả phòng ban
    private boolean deptStatus;     // Trạng thái phòng ban


    public boolean getDeptStatus() {
        return deptStatus;
    }
}
