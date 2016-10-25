/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.actionform;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author pavikumbhar
 */
public class TokenForm extends ActionForm {
    
    private String token;

    public String getToken() {
      
        return token;
    }

    public void setToken(String token) {
       
        this.token = token;
    }
    
}
