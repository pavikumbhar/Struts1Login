/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.action;

import com.pavikumbhar.javaheart.actionform.TokenForm;
import com.pavikumbhar.javaheart.util.PasswordGenerator;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author pavikumbhar
 */
public class TokenAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String loginToken = passwordGenerator.generateLoginpagetoken();
        session.setAttribute("loginToken", loginToken);
        System.out.println("AjaxAction.execute()>>>>>>0000");
        TokenForm tokenForm = (TokenForm) form;
        response.setContentType("text/text;charset=utf-8");
        response.setHeader("cache-control", "no-cache");
        PrintWriter out = response.getWriter();
        tokenForm.setToken(loginToken);
        out.println(tokenForm.getToken());
        out.flush();
        return null;
    }
}
