package br.com.finance.DigitalBank.validation;

import org.springframework.stereotype.Repository;
@Repository
public class PasswordDigitsValidation {

    public boolean passwordDigitsValidation (String password) {
        int length = password.length();
        return length < 8;
    }
}
