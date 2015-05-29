package com.scalableant.objectlike;

import java.io.IOException;

public class NullNode extends ValueNode
{
    // // Just need a fly-weight singleton

    public final static NullNode instance = new NullNode();

    private NullNode() { }

    public static NullNode getInstance() { return instance; }

    @Override
    public NodeType getNodeType()
    {
        return NodeType.NULL;
    }

    @Override public String asText(String defaultValue) { return defaultValue; }
    @Override public String asText() { return "null"; }

    // as with MissingNode, not considered number node; hence defaults are returned if provided
    
    /*
    public int asInt(int defaultValue);
    public long asLong(long defaultValue);
    public double asDouble(double defaultValue);
    public boolean asBoolean(boolean defaultValue);
    */

    @Override
    public boolean equals(Object o)
    {
        return (o == this);
    }
}
