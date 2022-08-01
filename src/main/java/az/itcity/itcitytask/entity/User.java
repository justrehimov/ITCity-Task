package az.itcity.itcitytask.entity;

import az.itcity.itcitytask.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 40, nullable = false)
    private String fullName;
    @Column(length = 30, nullable = false)
    private String email;
    @Column(length = 100)
    private String photoUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToOne(mappedBy = "user")
    private UserStatus userStatus;

}
