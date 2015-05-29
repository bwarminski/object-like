package com.scalableant.objectlike;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIterator<E> implements Iterator<E>
{
    @SuppressWarnings("unchecked")
    public static <E> EmptyIterator<E> instance()
    {
        return new EmptyIterator();
    }
    public boolean hasNext()
    {
        return false;
    }

    public E next()
    {
        throw new NoSuchElementException();
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}
