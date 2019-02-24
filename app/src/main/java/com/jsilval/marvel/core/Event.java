package com.jsilval.marvel.core;

public class Event<T> {

    private T mId;

    private boolean hasBeenHandled = false;

    public Event(T id) {
        if (id == null) {
            throw new IllegalArgumentException("null values in Event are not allowed.");
        }
        mId = id;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return mId;
        }
    }

    public boolean hasBeenHandled() {
        return hasBeenHandled;
    }
}
