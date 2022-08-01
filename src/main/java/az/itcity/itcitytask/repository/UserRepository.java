package az.itcity.itcitytask.repository;

import az.itcity.itcitytask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u inner join UserStatus us on u.id = us.id inner join " +
            "Status s on us.status.id = s.id where s.id=:statusId and us.statusChangedDate >= :timeStamp")
    List<User> findUsersByStatusAndTimeStamp(Long statusId, Timestamp timeStamp);

    @Query("select u from User u inner join UserStatus us on u.id = us.id inner join" +
            " Status s on us.status.id = s.id where s.id=:statusId")
    List<User> findUsersByStatusId(Long statusId);

    @Query("select u from User u inner join UserStatus us on u.id = us.id" +
            " where us.statusChangedDate >= :timeStamp")
    List<User> findUsersByTimeStamp(Timestamp timeStamp);
}
