package az.itcity.itcitytask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String url;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
