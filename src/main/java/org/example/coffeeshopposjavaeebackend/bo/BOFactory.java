package org.example.coffeeshopposjavaeebackend.bo;

import org.example.coffeeshopposjavaeebackend.bo.custom.impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes) {
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            default:
                return null;
        }
    }
}
