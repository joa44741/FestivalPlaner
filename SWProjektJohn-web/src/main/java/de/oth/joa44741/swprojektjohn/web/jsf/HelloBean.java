/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.web.jsf;

import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named("helloWorld")
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String s1 = "Hello World!!";

    public String getS1() {
        System.out.println(s1);
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

}
