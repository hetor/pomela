package com.tangerine.yuzu.examples;

/**
 * Copyright (c) 2014, All Rights Reserved. 
 *
 *  Constructor call test(this(),super())
 *
 * @Author hetor, tao.he1989@gmail.com
 * @Date Apr 26, 2014 8:54:30 PM
 * @Since JDK1.7
 * @version 1.0.0
 */
public class ConstructorCallTest {

    /**
     * super() is the same
     */
    public ConstructorCallTest() {
        //int i=0; //Constructor call must be the first statement in a constructor
        this("abc");
        //this("abc"); //Constructor call only once
    }
    
    public ConstructorCallTest(String arg) {
        // TODO Auto-generated method stub
    }
}
