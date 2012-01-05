package com.acidblue.collections.binpack;

public interface Block<T> {

    public abstract long getSize();

    public abstract T getData();
}
