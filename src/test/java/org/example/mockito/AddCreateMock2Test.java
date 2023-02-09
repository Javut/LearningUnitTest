package org.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AddCreateMock2Test { //Mockear por medio de Anotaciones

    @InjectMocks //Crea una instancia de la clase e inyecta el mock que ha sido creado con la anotación @Mock, //Indica el Objeto bajo prueba
    private Add add;
    @Mock //Anotacion para indicar que un objeto va a ser un mock
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this); //Esta es la forma de inicializar los mocks
    }

    @Test
    public void addTest(){
        add.add(3,2); //Segundo ejecutar el metodo de la clase bajo test
        Mockito.verify(validNumber).check(3);  //Por ultimo validar que sucedio el comportamiento esperado por medio del verify
    }



}