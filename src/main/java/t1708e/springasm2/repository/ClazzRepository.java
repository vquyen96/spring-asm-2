package t1708e.springasm2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1708e.springasm2.entity.Clazz;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
}
