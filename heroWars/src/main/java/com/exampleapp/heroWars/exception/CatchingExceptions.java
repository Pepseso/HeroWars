package com.exampleapp.heroWars.exception;

import com.exampleapp.heroWars.exception.hero.*;
import com.exampleapp.heroWars.exception.registration.UsernameAlreadyExistsException;
import com.exampleapp.heroWars.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CatchingExceptions {

    @ExceptionHandler(HeroWithThisNameAlreadyExistsException.class)
    public static ResponseEntity<Object> heroNameExists() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("Hero with this name already exists"));
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public static ResponseEntity<Object> usernameExists() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("This username already exists"));
    }

    @ExceptionHandler(AlreadyHaveHeroException.class)
    public static ResponseEntity<Object> alreadyHaveHero() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("Hey. You already have one hero!"));
    }

    @ExceptionHandler(NoHeroConnectedToUserException.class)
    public static ResponseEntity<Object> userHasNoHeroYet() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("Hey. You should create one, before you can see the stats."));
    }
}
