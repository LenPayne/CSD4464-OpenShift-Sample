/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0587637
 */
@Named
@ApplicationScoped
public class HelloBean {
    private String name = "World";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
