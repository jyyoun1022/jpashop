package jpabook.jpashop;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(MockitoExtension.class)//테스트 클래스가 Mockito를 사용했음을 알림
public class UserServiceTest {

    @Mock//실제구현된 객체 대신에 Mock객체에 사용하게 될 클래스를 의미
    UserRepository userRepository;

    @InjectMocks//Mock 객체가 주입된 클래스를 사용하게 될 클래스를 의미
    //테스트 런타임 시 클래스 내부에 선언된 멤버 변수들 중에서 @Mock으로 등록된 클래스의 변수에 실제 객체 대신 Mock 객체 주입
    UserService userService;

    @Test
    public void saveNewUser() {
        // given
        User user = User.builder().idx(1L)
                .username("lattechiffon")
                .email("lattechiffon@gmail.com")
                .roles(Arrays.asList("USER", "ADMIN")).build();

        when(userRepository.save(any())).thenReturn(user);

        // when
        User result = userService.createUser(User.builder().username("lattechiffon").build());

        // then
        verify(userRepository, times(1)).save(any());
        assertThat(result, equalTo(user));
    }

}
