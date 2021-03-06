package com.scalableant.objectlike;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DecimalNode extends NumericNode
{
    public static final DecimalNode ZERO = new DecimalNode(BigDecimal.ZERO);

    private final static BigDecimal MIN_INTEGER = BigDecimal.valueOf(Integer.MIN_VALUE);
    private final static BigDecimal MAX_INTEGER = BigDecimal.valueOf(Integer.MAX_VALUE);
    private final static BigDecimal MIN_LONG = BigDecimal.valueOf(Long.MIN_VALUE);
    private final static BigDecimal MAX_LONG = BigDecimal.valueOf(Long.MAX_VALUE);

    final protected BigDecimal _value;

    /* 
    /**********************************************************
    /* Construction
    /**********************************************************
     */

    public DecimalNode(BigDecimal v) { _value = v; }

    public static DecimalNode valueOf(BigDecimal d) { return new DecimalNode(d); }

    /* 
    /**********************************************************
    /* Overrridden JsonNode methods
    /**********************************************************
     */

    @Override
    public boolean isFloatingPointNumber() { return true; }
    
    @Override
    public boolean isBigDecimal() { return true; }

    @Override public boolean canConvertToInt() {
        return (_value.compareTo(MIN_INTEGER) >= 0) && (_value.compareTo(MAX_INTEGER) <= 0);
    }
    @Override public boolean canConvertToLong() {
        return (_value.compareTo(MIN_LONG) >= 0) && (_value.compareTo(MAX_LONG) <= 0);
    }
    
    @Override
    public Number numberValue() { return _value; }

    @Override
    public short shortValue() { return _value.shortValue(); }

    @Override
    public int intValue() { return _value.intValue(); }

    @Override
    public long longValue() { return _value.longValue(); }


    @Override
    public BigInteger bigIntegerValue() { return _value.toBigInteger(); }

    @Override
    public float floatValue() { return _value.floatValue(); }
    
    @Override
    public double doubleValue() { return _value.doubleValue(); }

    @Override
    public BigDecimal decimalValue() { return _value; }

    @Override
    public String asText() {
        return _value.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o instanceof DecimalNode) {
            return ((DecimalNode) o)._value.compareTo(_value) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() { return Double.valueOf(doubleValue()).hashCode(); }
}
