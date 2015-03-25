package no.storebrand.presentations.web.controllers;

import no.storebrand.presentations.entities.Notification;
import no.storebrand.presentations.entities.UserName;
import no.storebrand.presentations.entities.ValidationError;
import no.storebrand.presentations.functional.Result;
import no.storebrand.presentations.usecases.UserNameValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author OZY on 2015.03.23
 */
@RestController
public class UserNameValidatorController {

    @RequestMapping("/validate")
    public String validate(@RequestParam String candidate) {
        return UserNameValidator.validateUserName(candidate).errorMessage();
    }

}
