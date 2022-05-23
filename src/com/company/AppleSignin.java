package com.company;

public class AppleSignin implements ISignin {
    @Override
    public boolean connect(String host, int ip) {
        //custom apple connection code
        return false;
    }

    @Override
    public int getToken() {
        // apple auth. token
        return 0;
    }

    @Override
    public void disconnect() {
        //apple disconnect code
    }
}
