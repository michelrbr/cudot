package br.com.mxel.cuedot.movies;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by michelribeiro on 02/08/17.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Movies {
}
