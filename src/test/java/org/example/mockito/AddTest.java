package org.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AddTest {

    private Add add;
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        validNumber = Mockito.mock(ValidNumber.class); //Primero mockear las dependencias
        add = new Add(validNumber);
    }

    @Test
    public void addTest(){
        add.add(3,2); //Segundo ejecutar el metodo de la clase bajo test
        Mockito.verify(validNumber).check(3);  //Por ultimo validar que sucedio el comportamiento esperado
        Mockito.verify(validNumber).check(5);  //Por ultimo validar que sucedio el comportamiento esperado
    }




}