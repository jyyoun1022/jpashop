package jpabook.jpashop;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long idx;
    private String username;
    private String email;
    private List<String> roles;
}
