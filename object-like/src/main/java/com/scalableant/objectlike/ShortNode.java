package com.scalableant.objectlike;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ShortNode extends NumericNode
{
    protected final short _value;

    /* 
    ************************************************
    * Construction
    ************************************************
    */

    public ShortNode(short v) { _value = v; }

    public static ShortNode valueOf(short l) { return new ShortNode(l); }

    @Override
    public boolean isIntegralNumber() { return true; }

    @Override
    public boolean isShort() { return true; }

    @Override public boolean canConvertToInt() { return true; }
    @Override public boolean canConvertToLong() { return true; }
    
    @Override
    public Number numberValue() {
        return Short.valueOf(_value);
    }

    @Override
    public short shortValue() { return _value; }

    @Override
    public int intValue() { return _value; }

    @Override
    public long longValue() { return _value; }

    @Override
    public float floatValue() { return _value; }

    @Override
    public double doubleValue() { return _value; }

    @Override
    public BigDecimal decimalValue() { return BigDecimal.valueOf(_value); }

    @Override
    public BigInteger bigIntegerValue() { return BigInteger.valueOf(_value); }

    @Override
    public String asText() {
        return Short.toString(_value);
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return _value != 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o instanceof ShortNode) {
            return ((ShortNode) o)._value == _value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return _value;
    }
}
