package az.itcity.itcitytask.repository;

import az.itcity.itcitytask.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
