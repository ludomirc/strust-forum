package com.qbit.user.action;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Benek on 2015-07-11.
 */
public class JndiUtil {

    public void printContext(PrintWriter out, Context ctx, int indent) throws ServletException, IOException, NamingException {
        NamingEnumeration en = ctx.listBindings("");
        while (en.hasMore()) {
            Binding b = (Binding) en.next();
            char[] tabs = new char[indent];
            Arrays.fill(tabs, '\t');
            out.println(new String(tabs) + b.getName() + " = " + b.getClassName()+ " ");
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
