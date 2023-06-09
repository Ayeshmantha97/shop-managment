package com.devstack.pos.bo;

import com.devstack.pos.bo.custom.ProductDetailBoImpl;
import com.devstack.pos.bo.custom.impl.CustomerBoImpl;
import com.devstack.pos.bo.custom.impl.ProductBoImpl;
import com.devstack.pos.bo.custom.impl.UserBoImpl;
import com.devstack.pos.enums.BoType;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}
    public static BoFactory getInstance(){
        return (boFactory==null)?new BoFactory():boFactory;
    }
    public <T> T  getBo(BoType boType){
        switch (boType){
            case USER:
                return (T) new UserBoImpl();
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case PRODUCT:
                return (T) new ProductBoImpl();
            case PRODUCT_DETAILS:
                return (T) new ProductDetailBoImpl();
            default:
                return null;
        }
    }
}
