package org.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WebServiceTest {


    private WebService webService;

    @Mock
    private CallBack callBack;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        webService = new WebService();
    }

    @Test // ------------ Unit Test
    public void checkPositiveTest(){
        assertEquals(true, webService.checkLogin("Jaider","1234"));
    }

    @Test // ------------ Unit Test
    public void checkNegativeTest(){
        assertEquals(false, webService.checkLogin("Julio", "63737") );
    }

    @Test
    public void checkLoginTest(){
        webService.login("Jaider", "1234", callBack);
        verify(callBack).onSuccess("Usuario Correcto");
    }

    @Test
    public void loginErrorTest(){
        webService.login("Raquel", "437373", callBack);
        verify(callBack).onFail("Error");
    }


}