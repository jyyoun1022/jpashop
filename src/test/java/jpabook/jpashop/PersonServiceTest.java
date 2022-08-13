package jpabook.jpashop;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @BeforeEach
    void setUP(){
        this.personService = new PersonService(personRepository);
    }

    // 성이 youn 인 Person을 미리 만들어 놓고 --given
    //미리 만들어진 person 이 저장소에서 나왔을 때 --when
    //저장소에서 나온 person 은 성이 kim일 것이다. -- then

    @Test
    @DisplayName("mock test")
    void mock_test() throws IllegalAccessException {

        Optional<Person> person = Optional.of(Person.builder().firstName("jaeyeal").lastName("youn").build());

        when(personRepository.findById(anyLong())).thenReturn(person);

        assertEquals(personService.findById(1L).getLastName(),"youn");

        //목 객체의 findById 가 한번 실행되엇는지 검증
        verify(personRepository,times(1)).findById(1L);

        //findAll 이 한번도 실행하지 않았는지 검증
        verify(personRepository,never()).findAll();

        //해당 mock이 더이상 interactional  발생되지 않아야한다.
        verifyNoInteractions(person);
    }

    @Test
    @DisplayName("BDD 테스트")
    void bdd() throws IllegalAccessException {
        Optional<Person> person = Optional.of(Person.builder().id(1L).firstName("jaeyeal").lastName("youn").build());
        given(personRepository.findById(1L)).willReturn(person);

        Person selectPerson = personService.findById(1L);

        assertEquals(person.get(),selectPerson);
    }
}