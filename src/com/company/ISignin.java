package com.company;

public interface ISignin {

    boolean connect(String host, int ip);
    int getToken();
    void disconnect();  //void returns nothing

}
