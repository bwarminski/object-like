package com.scalableant.objectlike;

import java.util.List;

public abstract class ValueNode extends Node
{
    @Override
    public String toString() { return asText(); }
    
    @Override
    public final Node get(int index) { return null; }

    @Override
    public final Node path(int index) { return MissingNode.getInstance(); }

    @Override
    public final boolean has(int index) { return false; }

    @Override
    public final boolean hasNonNull(int index) { return false; }

    @Override
    public final Node get(String fieldName) { return null; }

    @Override
    public final Node path(String fieldName) { return MissingNode.getInstance(); }

    @Override
    public final boolean has(String fieldName) { return false; }

    @Override
    public final boolean hasNonNull(String fieldName) { return false; }
    
    @Override
    public final Node findValue(String fieldName) {
        return null;
    }

    // note: co-variant return type
    @Override
    public final ObjectNode findParent(String fieldName) {
        return null;
    }

    @Override
    public final List<Node> findValues(String fieldName, List<Node> foundSoFar) {
        return foundSoFar;
    }

    @Override
    public final List<String> findValuesAsText(String fieldName, List<String> foundSoFar) {
        return foundSoFar;
    }

    @Override
    public final List<Node> findParents(String fieldName, List<Node> foundSoFar) {
        return foundSoFar;
    }
}
