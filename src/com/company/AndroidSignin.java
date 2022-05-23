package com.company;

public class AndroidSignin implements ISignin {

    @Override
    public boolean connect(String host, int ip) {
        //custom connection code for android authentication
        return false;
    }

    @Override
    public int getToken() {
        // custom code to get an auth token
        return 0;
    }

    @Override
    public void disconnect() {
        //custom code for disconnecting
    }
}
