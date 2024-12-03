package ra.demo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "App_User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_name", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String userName;
    @Column(name = "user_passwod", columnDefinition = "varchar(50)", nullable = false)
    private String password;
    @Column(name = "user_fullname", columnDefinition = "varchar(100)", nullable = false)
    private String fullName;
    @Column(name = "user_email", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String email;
    @Column(name = "user_phone", columnDefinition = "char(10)", unique = true, nullable = false)
    private String phone;
    @Column(name = "user_sex")
    private boolean sex;
    @Column(name = "user_bod")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bod;
    @Column(name = "user_address", nullable = false)
    private String address;
    @Column(name = "user_created")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created = LocalDate.now();
    @Column(name = "user_status", columnDefinition = "bit default(1)")
    private boolean status = true;

}
