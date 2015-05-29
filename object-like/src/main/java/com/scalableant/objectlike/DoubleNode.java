package com.scalableant.objectlike;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class DoubleNode extends NumericNode
{
    protected final double _value;

    /* 
    /**********************************************************
    /* Construction
    /**********************************************************
     */

    public DoubleNode(double v) { _value = v; }

    public static DoubleNode valueOf(double v) { return new DoubleNode(v); }

    /* 
    /**********************************************************
    /* Overrridden JsonNode methods
    /**********************************************************
     */

    @Override
    public boolean isFloatingPointNumber() { return true; }

    @Override
    public boolean isDouble() { return true; }

    @Override public boolean canConvertToInt() {
        return (_value >= Integer.MIN_VALUE && _value <= Integer.MAX_VALUE);
    }
    @Override public boolean canConvertToLong() {
        return (_value >= Long.MIN_VALUE && _value <= Long.MAX_VALUE);
    }
    
    @Override
    public Number numberValue() {
        return Double.valueOf(_value);
    }

    @Override
    public short shortValue() { return (short) _value; }

    @Override
    public int intValue() { return (int) _value; }

    @Override
    public long longValue() { return (long) _value; }

    @Override
    public float floatValue() { return (float) _value; }
    
    @Override
    public double doubleValue() { return _value; }

    @Override
    public BigDecimal decimalValue() { return BigDecimal.valueOf(_value); }

    @Override
    public BigInteger bigIntegerValue() {
        return decimalValue().toBigInteger();
    }

    @Override
    public String asText() {
        return Double.toString(_value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o instanceof DoubleNode) {
            // We must account for NaNs: NaN does not equal NaN, therefore we have
            // to use Double.compare().
            final double otherValue = ((DoubleNode) o)._value;
            return Double.compare(_value, otherValue) == 0;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        // same as hashCode Double.class uses
        long l = Double.doubleToLongBits(_value);
        return ((int) l) ^ (int) (l >> 32);

    }
}
