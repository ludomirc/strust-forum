package com.qbit.user.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;

/**
 * This struts interceptor is written to scan action class and inject EJB instance.
 * It uses caching mechanism, so as each action is ready only once in its lifetime and
 * for further subsequent request it will use cached information.
 *
 * @author Gaurav Daga
 */
public class EJBInjectionInterceptor implements Interceptor {

    private static final Logger logger = Logger.getLogger(EJBInjectionInterceptor.class);
    private static final long serialVersionUID = 8169549936410864757L;
    private static InitialContext context = null;
    private static Map<Class<? extends Object>, List<Field>> ejbInstanceFields = new HashMap<Class<? extends Object>, List<Field>>();

    public static final String APP_NAME = "struts-forum";

    static {
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            StringWriter swriter = new StringWriter();
            PrintWriter pWriter = new PrintWriter(swriter);
            logger.debug(e);
            throw new InstantiationError("Context look-up failed!! \n" + swriter.toString());
        }
    }

    private static synchronized void populateActionClassMap(Object actionObject) {
        List<Field> ejbInstanceFieldList = new ArrayList<Field>();
        Field[] fields = actionObject.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            EJB ejb;
            for (Field f : fields) {
                ejb = f.getAnnotation(EJB.class);
                if (ejb != null) {
                    ejbInstanceFieldList.add(f);
                }
            }
        }
        ejbInstanceFields.put(actionObject.getClass(), ejbInstanceFieldList);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionObject) throws Exception {
        Object action = actionObject.getAction();
        if (ejbInstanceFields.get(action.getClass()) == null) {
            synchronized (action.getClass()) {
                EJBInjectionInterceptor.populateActionClassMap(action);
            }
        }
        injectEJBInstance(action);
        return actionObject.invoke();
    }

    private void injectEJBInstance(Object actionObject) throws IllegalArgumentException, IllegalAccessException, NamingException {

        logger.debug("========start===========");
        NamingEnumeration<NameClassPair> list = context.list("java:global/"+APP_NAME+"/");
        while (list.hasMore()) {
            logger.debug(list.next().getName());
        }
        logger.debug("=========end==========");

        List<Field> ejbInstanceFieldList = ejbInstanceFields.get(actionObject.getClass());
        for (Iterator<Field> iterator = ejbInstanceFieldList.iterator(); iterator.hasNext(); ) {
            Field field = iterator.next();
            field.setAccessible(true);


            String fullJndiName = "java:global/"+APP_NAME+"/" + getJndiBeanName(field);
            logger.debug("full jndi name:" + fullJndiName);
            field.set(actionObject, context.lookup(fullJndiName));
        }


    }

    private String getLocalImplementingInterface(Field field) {
        Class<?> interfaceImplemented = field.getAnnotation(EJB.class).beanInterface();
        if (interfaceImplemented.getAnnotation(Local.class) != null) {
            return field.getAnnotation(EJB.class).beanInterface().getName();
        }
        return field.getType().isInterface() ? field.getType().getName() : getLocalInterfaceForEJB(field.getType());
    }

    private String getJndiBeanName(Field field) {
        /*Class<?> interfaceImplemented = field.getAnnotation(EJB.class).beanInterface();
        if (interfaceImplemented.getAnnotation(Local.class) != null) {*/
        return field.getAnnotation(EJB.class).name() + "!" + (field.getType().isInterface() ? field.getType().getName() : getLocalInterfaceForEJB(field.getType()));
/*        }
        return field.getType().isInterface() ? field.getType().getName() : getLocalInterfaceForEJB(field.getType());*/
    }

    private String getLocalInterfaceForEJB(Class<?> clazzType) {
        throw new RuntimeException("Variable with the type " + clazzType.getName() + " is declared as EJB implementing class, not the local interface.");
    }

}