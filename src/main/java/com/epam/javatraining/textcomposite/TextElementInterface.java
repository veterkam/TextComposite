package com.epam.javatraining.textcomposite;

interface TextElementInterface<T> {
    T copy();
    String toMarketString();
    default boolean isSeparator() {return false;}
}
