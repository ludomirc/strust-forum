package com.qbit.user.action;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Benek on 2015-07-11.
 */
@WebServlet("/listjndi")
public class ListJndi extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        try {
            InitialContext ictx = new InitialContext();
            Context ctx = (Context) ictx.lookup("java:");
            out.println("java: = " + ctx.getClass().getName());
            printContext(out, ctx, 1);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void printContext(PrintWriter out, Context ctx, int indent) throws ServletException, IOException, NamingException {
        NamingEnumeration en = ctx.listBindings("");
        while (en.hasMore()) {
            Binding b = (Binding) en.next();
            char[] tabs = new char[indent];
            Arrays.fill(tabs, '\t');
            out.println(new String(tabs) + b.getName() + " = " + b.getClassName());
            try {
                if (b.getObject() instanceof Context) {
                    printContext(out, (Context) b.getObject(), indent + 1);
                }
            } catch (Exception exc) {
                throw new ServletException(exc);
            }
        }
    }
}

