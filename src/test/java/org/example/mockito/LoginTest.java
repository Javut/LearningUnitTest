package org.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoginTest {

    @InjectMocks
    private Login login;

    @Mock
    private WebService webService;

    @Captor
    private ArgumentCaptor<CallBack> callBackArgumentCaptor; //Se usa para capturar los argumentos de los metodos simulados

    @BeforeEach
    public void setUp(){ //Siempre debe ser public
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doLoginPositiveTest(){ //Siempre debe ser public
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String user = (String) invocationOnMock.getArguments()[0]; //Con el invocationOnMock es posible recuperar el argumento real que se le esta pasando
                assertEquals("Alberto", user);
                String password = (String) invocationOnMock.getArguments()[1];
                assertEquals("12345", password);
                CallBack callBack = (CallBack) invocationOnMock.getArguments()[2];
                callBack.onSuccess("OK");
                return null;
            }
        }).when(webService).login(anyString(),anyString(),any(CallBack.class)); //Cuando se llame al metodo login del webService se debe ejecutar el codigo correspondiente al doAnswer

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(CallBack.class));
        assertEquals(login.isLogin, true);
    }

    @Test
    public void doLoginNegativeTest(){ //Siempre debe ser public
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                String user = (String) invocationOnMock.getArguments()[0]; //Con el invocationOnMock es posible recuperar el argumento real que se le esta pasando
                assertEquals("Alberto", user);
                String password = (String) invocationOnMock.getArguments()[1];
                assertEquals("12345", password);
                CallBack callBack = (CallBack) invocationOnMock.getArguments()[2];
                callBack.onFail("Error");
                return null;
            }
        }).when(webService).login(anyString(),anyString(),any(CallBack.class)); //Cuando se llame al metodo login del webService se debe ejecutar el codigo correspondiente al doAnswer

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(CallBack.class));
        assertEquals(login.isLogin, false);
    }

    @Test
    public void doLoginCaptorTest(){
        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), callBackArgumentCaptor.capture());
        assertEquals(login.isLogin, false);

        CallBack callBack = callBackArgumentCaptor.getValue();
        callBack.onSuccess("OK");
        assertEquals(login.isLogin,true);

        callBack.onFail("Error");
        assertEquals(login.isLogin, false);
    }

}