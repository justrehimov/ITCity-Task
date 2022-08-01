package az.itcity.itcitytask.entity;

import az.itcity.itcitytask.enums.EnumStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user_status")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "status_id")
    private Status status;
    private Timestamp statusChangedDate;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private User user;
}
