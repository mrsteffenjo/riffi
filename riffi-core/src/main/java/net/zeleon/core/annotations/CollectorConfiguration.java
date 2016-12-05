package net.zeleon.core.annotations;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.*;

/**
 * Created on 05.12.2016.
 *
 * @author Steffen Jørgensen (mrsteffenjo@gmail.com)
 * @version 1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@IndexAnnotated
public @interface CollectorConfiguration {
}
