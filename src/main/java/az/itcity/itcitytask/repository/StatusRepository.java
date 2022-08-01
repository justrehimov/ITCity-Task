package az.itcity.itcitytask.repository;

import az.itcity.itcitytask.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("select s from Status s where lower(s.status)=:status")
    Status getStatusByStatus(String status);
}
