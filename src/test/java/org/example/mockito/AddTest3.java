package org.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AddTest3 {

    @InjectMocks  //Anotacion empleada para indicar que esta clase recibira como dependencias los objetos mockeados con @Mock
    private Add add;

    @Mock
    private ValidNumber validNumber;

    @Mock
    private Print print;

    @Captor
    private ArgumentCaptor<Integer> captor;



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

            //Arrange ---> Parametros de actualización es decir en que condiciones queremos que se ejecute dicho Test
           //Act ---> Que metodos queremos probar
          //Assert ---> La afirmación de lo que va a ocurrir

         //Given ---> igual a arrange, permite inicializar los mock con su configuración
        //When ---> La actuacion o que metodo queremos probar
       //Then ---> La afirmacion de lo que va a ocurrir

    @Test
    public void addPrintTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        //When
        add.addPrint(4,4);
        //Then
        verify(validNumber,times(2)).check(4);
        verify(validNumber, never()).check(99);
        verify(validNumber, atLeast(1)).check(4);
        verify(validNumber, atMost(3)).check(4);

        verify(print).showMessage(8); //Verifica con que valor se esta llamando el metodo showMessage
        verify(print, never()).showError();
    }


    @Test
    public void captorTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(print).showMessage(captor.capture()); //Captor por medio del metodo capture permite recuperar cual es el valor que se le esta pasando
        assertEquals(captor.getValue().intValue(), 9);
    }

    //A diferencia de un mock normal el Spy me permite obtener el valor real devuelto por un metodo en caso de no cumplirse una condición dada

    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

/*    @Test
    public void spyTest(){
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2,spyList.size());
    }*/


    @Test
    public void mockTest(){
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        given(mockList.size()).willReturn(2);
        assertEquals(2,mockList.size());
    }




}