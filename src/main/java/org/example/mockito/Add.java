package org.example.mockito;

public class Add {

    private ValidNumber validNumber;
    private Print print;

    public Add(ValidNumber validNumber) {
        this.validNumber = validNumber;
    }

    public Add(ValidNumber validNumber, Print print){
        this.validNumber = validNumber;
        this.print = print;
    }

    public int add(Object a, Object b){
        if(validNumber.check(a) && validNumber.check(b)){
            return (Integer)a + (Integer)b;
        }
        return 0;
    }

    public int addInt(Object a){
        return validNumber.doubleToInto(a) + validNumber.doubleToInto(a);
    }


    public void addPrint(Object a, Object b){
        if(validNumber.check(a) && validNumber.check(b)){
            int resultado = (Integer) a + (Integer) b;
            print.showMessage(resultado);
            return;
        }else{
            print.showError();
        }
    }

}
