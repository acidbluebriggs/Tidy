package com.acidblue.collections.binpack;

public interface BadBlock<T> extends Block<T> {

    //todo, is a string good enough?
    public abstract String getMessage();
}
