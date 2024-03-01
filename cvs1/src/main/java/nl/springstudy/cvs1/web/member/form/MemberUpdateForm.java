package nl.springstudy.cvs1.web.member.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberUpdateForm {

    @NotEmpty
    private String password;
}
