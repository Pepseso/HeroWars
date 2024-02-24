package com.exampleapp.heroWars.exception;

import com.exampleapp.heroWars.exception.hero.*;
import com.exampleapp.heroWars.exception.registration.*;
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

    @ExceptionHandler(PasswordTooShortException.class)
    public static ResponseEntity<Object> passwordTooShortException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("Password must be at least 4 letters long."));
    }
    @ExceptionHandler(RegistrationDataMissingException.class)
    public static ResponseEntity<Object> registrationDataMissing() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("Registration data is mandatory"));
    }
    @ExceptionHandler(WrongFromatOfUsernameException.class)
    public static ResponseEntity<Object> usernameWrongFormat() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("Username must contain only letters and numbers"));
    }

    @ExceptionHandler(QuestsFileReadingErrorException.class)
    public static ResponseEntity<Object> fileReadingError() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorDTO("Some error with quests"));
    }

    @ExceptionHandler(CannotGenerateMonsterException.class)
    public static ResponseEntity<Object> cannotGenerateMonster() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorDTO("Some error with getting monster to battle with"));
    }

    @ExceptionHandler(MonsterNotFoundException.class)
    public static ResponseEntity<Object> monsterNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("This monster does not exists"));
    }

    @ExceptionHandler(MonsterNotAliveException.class)
    public static ResponseEntity<Object> monsterDeadAlready() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("This monster is not alive anymore"));
    }
}
