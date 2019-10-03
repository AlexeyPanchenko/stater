package ru.alexpanchenko.stater;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker for var that need to be saved in bundle.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface State {}
