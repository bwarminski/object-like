package com.scalableant.objectlike;

public class MissingNode extends ValueNode
{

    private final static MissingNode instance = new MissingNode();

    private MissingNode() { }
    
    public static MissingNode getInstance() { return instance; }
    
    @Override
    public NodeType getNodeType()
    {
        return NodeType.MISSING;
    }

    @Override public String asText() { return ""; }

    @Override public String asText(String defaultValue) { return defaultValue; }
    
    // // Note: not a numeric node, hence default 'asXxx()' are fine:
    
    /*
    public int asInt(int defaultValue);
    public long asLong(long defaultValue);
    public double asDouble(double defaultValue);
    public boolean asBoolean(boolean defaultValue);
    */
    
    @Override
    public boolean equals(Object o)
    {
        /* Hmmh. Since there's just a singleton instance, this
         * fails in all cases but with identity comparison.
         * However: if this placeholder value was to be considered
         * similar to SQL NULL, it shouldn't even equal itself?
         * That might cause problems when dealing with collections
         * like Sets... so for now, let's let identity comparison
         * return true.
         */
        return (o == this);
    }

    @Override
    public String toString()
    {
        // toString() should never return null
        return "";
    }

}
