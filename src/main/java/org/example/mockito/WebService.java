package org.example.mockito;



public class WebService {



    public void login(String user, String password, CallBack callBack){
        if(checkLogin(user, password)){
            callBack.onSuccess("Usuario Correcto");
        }else{
           callBack.onFail("Error");
        }
    }


    public boolean checkLogin(String user, String password){
        if(user.equals("Jaider") && password.equals("1234")){
            return true;
        }
        return false;
    }
}
