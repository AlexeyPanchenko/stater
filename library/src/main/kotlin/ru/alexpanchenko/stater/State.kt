package ru.alexpanchenko.stater

/**
 * Marker for var that need to be saved in bundle.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.BINARY)
annotation class State