package org.example.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        validNumber = new ValidNumber();
    }

    @AfterEach
    public void tearDown(){
        validNumber = null;
    }

    @Test
    public void checkPositiveTest(){
        assertEquals(true, validNumber.check(5) );
    }

    @Test
    public void checkNegativeTest(){
        assertEquals(false, validNumber.check(11) );
    }

    @Test
    public void checkStringTest(){
        assertEquals(false, validNumber.check("Hola") );
    }

    @Test
    public void checkZeroTest(){
        assertEquals(true, validNumber.checkZero(-57) );
    }

    @Test
    public void checkZeroStringTest(){
        assertEquals(false, validNumber.checkZero("Hola") );
    }

    @Test
    public void checkZeroExceptionTest(){
       assertThrows(ArithmeticException.class, ()-> validNumber.checkZero(0));
    }

    @Test
    public void checkDoubleToIntTest(){
        assertEquals(9,validNumber.doubleToInto(9.5));
    }

    @Test
    public void checkDoubleInvalidTest(){
        assertEquals(0,validNumber.doubleToInto("5"));
    }


}