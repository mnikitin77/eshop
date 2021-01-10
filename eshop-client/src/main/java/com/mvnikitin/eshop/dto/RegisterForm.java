package com.mvnikitin.eshop.dto;

import com.mvnikitin.eshop.constraints.NotExistsUser;
import com.mvnikitin.eshop.constraints.PasswordMatch;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@PasswordMatch(message = "{user.repeatpassword}")
public class RegisterForm {

    @NotBlank(message = "{user.username.notempty}")
    @NotExistsUser(message = "{user.exists}")
    String username;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "{user.password.correct}")
    String password;

    @NotBlank(message = "{user.repeatpassword}")
    String repeatPassword;

    @NotBlank(message = "{user.firstname.notempty}")
    String firstName;

    String middleName;

    @NotBlank(message = "{user.lastname.notempty}")
    String lastName;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message = "{user.phone}")
    String phone;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+" +
            "(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "{user.email}")
    String email;
}
