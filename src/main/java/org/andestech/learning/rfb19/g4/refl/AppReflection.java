package org.andestech.learning.rfb19.g4.refl;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.andestech.learning.rfb19.g4.utils.Utils.echo;


//@XmlElement()
class A
{


}

@DataSecure(cyphers = Cyphers.SHA2048, pipes = 3)
class B
{

    public void m1(B b){
        System.out.println("m1 public!");
    }

    private void m3Private(){
        System.out.println("m3 private! secure data...");
    }

    void m2(String data){
        System.out.println("m2:" + data);
    }

    protected void m4(){
        System.out.println("m4!!!");
    }

}

public class AppReflection
{
    public static void main( String[] args ) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException
    {

     echo();
     B b = new B();

     Class<?> c1 = b.getClass();
     Class<?> c2 = B.class;

     if(c1.isAnnotationPresent(DataSecure.class))
     {
         //... secure write data.
         System.out.println("DataSecure Ok!");
         Annotation annotation = c1.getAnnotation(DataSecure.class);
         System.out.println("cypher: " + ((DataSecure) annotation).cyphers().name());
         System.out.println("pipe: " + ((DataSecure) annotation).pipes());
     }

     for(Method m: c1.getDeclaredMethods())
     {
         System.out.println(m.getName() + " : " + m.getModifiers());
     }

     Method meth1 = c1.getDeclaredMethod("m1", B.class);
        meth1.invoke(b, new B());
     Method meth4 = c1.getDeclaredMethod("m4");
        meth4.invoke(b);

        Method meth3 = c1.getDeclaredMethod("m3Private");
        meth3.setAccessible(true);
        meth3.invoke(b);


    }
}
