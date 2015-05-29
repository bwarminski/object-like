package com.scalableant.objectlike;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class NumericNode extends ValueNode
{
    @Override
    public final NodeType getNodeType()
    {
        return NodeType.NUMBER;
    }

    // // // Let's re-abstract so sub-classes handle them

    @Override public abstract Number numberValue();
    @Override public abstract int intValue();
    @Override public abstract long longValue();
    @Override public abstract double doubleValue();
    @Override public abstract BigDecimal decimalValue();
    @Override public abstract BigInteger bigIntegerValue();

    @Override public abstract boolean canConvertToInt();
    @Override public abstract boolean canConvertToLong();
    
    /* 
    /**********************************************************
    /* General type coercions
    /**********************************************************
     */
    
    @Override
    public abstract String asText();

    @Override
    public final int asInt() {
        return intValue();
    }
    @Override
    public final int asInt(int defaultValue) {
        return intValue();
    }

    @Override
    public final long asLong() {
        return longValue();
    }
    @Override
    public final long asLong(long defaultValue) {
        return longValue();
    }
    
    @Override
    public final double asDouble() {
        return doubleValue();
    }
    @Override
    public final double asDouble(double defaultValue) {
        return doubleValue();
    }
}
