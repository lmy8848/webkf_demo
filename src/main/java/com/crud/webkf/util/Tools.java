package com.crud.webkf.util;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Enumeration;

public class Tools {

    public static Object getParamter(Class c, HttpServletRequest request){
        Object o=null;
        Field f=null;
        try{
            Enumeration<String> names = request.getParameterNames();
            o = c.getDeclaredConstructor().newInstance();
            while (names.hasMoreElements()) {
                String attrName = names.nextElement();
                try {
                 f=c.getDeclaredField(attrName);

                }catch (NoSuchFieldException e){
                    continue;
                }
                f.setAccessible(true);
                if(f.getType()==int.class){
                    f.set(o,Integer.valueOf(request.getParameter(attrName)));
                }else {
                    f.set(o,request.getParameter(attrName));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return o;
    }
}
