package com.demven.chronocat.ui.selectors;

/**
 * Interface, that provides methods for convenient view selecting.
 * @author Dmitry Salnikov (Demven)
 * @since 29.03.2014
 */
public interface Selector {

    /**
     * Highlights and selects a specified view
     * @param viewTag
     */
    public void select(String viewTag);
}
