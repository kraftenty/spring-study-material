package nl.springstudy.cvs1.web.member.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberJoinForm {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
