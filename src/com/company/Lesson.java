package com.company;

import com.company.pvzextras.Zombie;

public class Lesson {
ISignin signin;


    public static void main(String[] args) {
        new Main();
    }


    //Constructor
    public Lesson() {
        Enemy e = new Zombie(10, 30);

        //Cannot instantiate enemy
        //cant do Enemy b = new Enemy(2,5);

//        if(android) {
//            singin = new AndroidSingin();
 //       } else if (apple) {
//            singin = new AppleSignin();
 //       }

        signin.connect("192.160.1.1", 5678);
        signin.getToken();
        signin.disconnect();


        //ISignin signin = new ISignin();
    }
}
