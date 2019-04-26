package org.andestech.learning.rfb19.g4.refl;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataSecure {
   Cyphers cyphers() default Cyphers.CRC32;
   int pipes() default 1;

}
