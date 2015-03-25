package no.storebrand.presentations.usecases;

import no.storebrand.presentations.entities.UserName;
import no.storebrand.presentations.entities.ValidationError;
import no.storebrand.presentations.functional.Result;
import org.junit.Test;

import static no.storebrand.presentations.functional.Result.success;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserNameValidatorTest {

    @Test
    public void whenCandidateIsNull_ReturnFailure() throws Exception {
        assertFailure(UserNameValidator.validate(null));
    }

    @Test
    public void whenCandidateIsEmpty_ReturnFailure() throws Exception {
        assertFailure(UserNameValidator.validate(""));
    }

    @Test
    public void whenCandidateHasSpaces_ReturnTrimmedUsername() throws Exception {
        assertResultEquals(success(new UserName("FOOBAR")), UserNameValidator.validate(" FOO BAR "));
    }

    @Test
    public void whenCandidateHasLowercaseSymbols_ReturnUppercase() throws Exception {
        assertResultEquals(success(new UserName("FOOBAR")), UserNameValidator.validate("FooBar"));
    }

    @Test
    public void whenCandidateHasLowercaseSymbolsAndHasSpaces_ReturnTrimmedAndUppercase() throws Exception {
        assertResultEquals(success(new UserName("FOOBAR")), UserNameValidator.validate(" Foo Bar "));
    }

    @Test
    public void whenCandidateIsUppercase_ReturnSuccess() throws Exception {
        assertSuccess(UserNameValidator.validate("FOOBAR"));
    }

    public static void assertResultEquals(Result<ValidationError, UserName> expected, Result<ValidationError, UserName> actual) {
        assertEquals(expected, actual);
    }

    public static void assertSuccess(Result<ValidationError, UserName> result) {
        assertTrue(result.isSuccess());
    }

    public static void assertFailure(Result<ValidationError, UserName> result) {
        assertTrue(result.isFailure());
    }

}