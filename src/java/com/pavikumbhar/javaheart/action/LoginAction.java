/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.action;

import com.pavikumbhar.javaheart.actionform.LoginForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author pavikumbhar
 */
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;

        System.out.println("LoginAction.execute()" + loginForm.getPassword());
        HttpSession session = request.getSession(false);
        String loginToken = (String) session.getAttribute("loginToken");

        System.out.println("loginToken:   " + loginToken);
        String password = com.pavikumbhar.javaheart.util.RC4.RC4(loginToken, com.pavikumbhar.javaheart.util.RC4.ASCIIhex2Data(loginForm.getPassword()));

        loginForm.setPassword(password);

        System.out.println("password :   " + password);
        if (loginForm.getUserName().equals(loginForm.getPassword())) {
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
