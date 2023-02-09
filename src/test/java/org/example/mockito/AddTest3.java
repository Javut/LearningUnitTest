package org.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class AddTest3 {

    //Initial configuration
    @InjectMocks  //Anotacion empleada para indicar que esta clase recibira como dependencias los objetos mockeados con @Mock
    private Add add;
    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest(){
        when(validNumber.check(3)).thenReturn(true);
        boolean checkNumber = validNumber.check(3);
        assertEquals(true, checkNumber);

        when(validNumber.check("a")).thenReturn(false);
        checkNumber = validNumber.check("a");
        assertEquals(false, checkNumber);

    }


    @Test
    public void addMockException(){
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero"));
        Exception exception = null;
        try{
            validNumber.checkZero(0);
        }catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void addRealMethod(){
        when(validNumber.check(3)).thenCallRealMethod();
        assertEquals(true,validNumber.check(3));

        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(false,validNumber.check("3"));
    }

    @Test
    public void addDoubleToIntTest(){
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }
        };

        when(validNumber.doubleToInto(7.7)).thenAnswer(answer);
        assertEquals(14,add.addInt(7.7));
    }

             //Strategies for Mockito Test:

//                First Form
            //Arrange ---> Parametros de actualización es decir en que condiciones queremos que se ejecute dicho Test
           //Act ---> Que metodos queremos probar
          //Assert ---> La afirmación de lo que va a ocurrir

//                Second Form
         //Given ---> igual a arrange, permite inicializar los mock con su configuración
        //When ---> La actuacion o que metodo queremos probar
       //Then ---> La afirmacion de lo que va a ocurrir

    //
    @Test
    public void patterTest(){
        //Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int resultado = add.add(4,5);
        //Assert
        assertEquals(9,resultado);
    }

    @Test
    public void PatterBDDTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        int resultado = add.add(4,5);
        //Then
        assertEquals(9,resultado);
    }

    @Test
    public void argumentMatcherTest(){
        //Given
        given(validNumber.check(anyInt())).willReturn(true);
        //When
        int resultado = add.add(4,5);
        //Then
        assertEquals(9,resultado);
    }


}