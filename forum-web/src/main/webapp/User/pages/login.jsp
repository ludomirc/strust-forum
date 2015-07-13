<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NameClassPair" %>
<%@ page import="javax.naming.NamingEnumeration" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="com.qbit.user.action.JndiUtil" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head></head>
<body>
<h1>Struts 2 Hello World Example</h1>
<%

    InitialContext context = new InitialContext();


    NamingEnumeration<NameClassPair> list = context.list("java:app/");
    while (list.hasMore()) {
        out.print(list.next().getName());
    }
    out.flush();

%>
<br>
<%
  /*  JndiUtil util = new JndiUtil();
    PrintWriter pw = new PrintWriter(out, true);
    InitialContext ictx = new InitialContext();
    Context ctx = (Context) ictx.lookup("java:");
    out.println("java: = " + ctx.getClass().getName());
    util.printContext( pw, ctx, 1);*/
%>

<s:form action="Welcome">
    <s:textfield name="username" label="Username"/>
    <s:password name="password" label="Password"/>
    <s:submit/>
</s:form>

</body>
</html>