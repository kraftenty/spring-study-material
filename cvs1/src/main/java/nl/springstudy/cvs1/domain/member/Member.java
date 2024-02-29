package nl.springstudy.cvs1.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String email; // 이메일
    @NotEmpty
    private String password; // 비밀번호

}
