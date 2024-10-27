package org.sysc4806.lab52;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
}
