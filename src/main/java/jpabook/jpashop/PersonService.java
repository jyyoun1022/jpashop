package jpabook.jpashop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person findById(Long id) throws IllegalAccessException {
        return personRepository.findById(id).orElseThrow(IllegalAccessException::new);
    }
}
