package com.threefour.backend.Janindu;

import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

@Service
public class JaninduService {
    private final   JaninduRepository janinduRepository ;

public JaninduService(JaninduRepository janinduRepository){
    this.janinduRepository = janinduRepository ;
}


    public static String addnumbers(int num1, int num2) {
    return "The sum is " + (num1+num2) ;
    }
}
