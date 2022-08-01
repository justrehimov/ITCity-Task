package az.itcity.itcitytask.repository;

import az.itcity.itcitytask.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
}
