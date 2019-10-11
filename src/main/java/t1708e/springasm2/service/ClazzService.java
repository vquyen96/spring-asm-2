package t1708e.springasm2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t1708e.springasm2.entity.Clazz;
import t1708e.springasm2.repository.ClazzRepository;

import java.util.List;

@Service
public class ClazzService {

    @Autowired
    ClazzRepository clazzRepository;

    public List<Clazz> getAll(){
        return clazzRepository.findAll();
    }

    public Clazz getById(int id) {
        return clazzRepository.findById(id).orElse(null);
    }

    public void create(Clazz clazz) {
        clazzRepository.save(clazz);
    }
}
