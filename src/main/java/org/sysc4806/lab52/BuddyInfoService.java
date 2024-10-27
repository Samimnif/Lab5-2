package org.sysc4806.lab52;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BuddyInfoService {

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    public BuddyInfo save(BuddyInfo buddyInfo) {
        return buddyInfoRepository.save(buddyInfo);
    }

    public Iterable<BuddyInfo> findAll() {
        return buddyInfoRepository.findAll();
    }

    public void deleteById(Long id) {
        buddyInfoRepository.deleteById(id);
    }
}

