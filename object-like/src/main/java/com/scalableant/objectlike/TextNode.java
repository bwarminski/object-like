package com.scalableant.objectlike;

public class TextNode extends ValueNode
{
    final static TextNode EMPTY_STRING_NODE = new TextNode("");

    protected final String _value;

    public TextNode(String v) { _value = v; }

    /**
     * Factory method that should be used to construct instances.
     * For some common cases, can reuse canonical instances: currently
     * this is the case for empty Strings, in future possible for
     * others as well. If null is passed, will return null.
     *
     * @return Resulting {@link TextNode} object, if <b>v</b>
     *   is NOT null; null if it is.
     */
    public static TextNode valueOf(String v)
    {
        if (v == null) {
            return null;
        }
        if (v.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(v);
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.STRING;
    }

    @Override
    public String textValue() {
        return _value;
    }
    
    /* 
    /**********************************************************
    /* General type coercions
    /**********************************************************
     */

    @Override
    public String asText() {
        return _value;
    }

    @Override
    public String asText(String defaultValue) {
        return (_value == null) ? defaultValue : _value;
    }
    
    // note: neither fast nor elegant, but these work for now:

    @Override
    public boolean asBoolean(boolean defaultValue) {
        if (_value != null) {
            String v = _value.trim();
            if ("true".equals(v)) {
                return true;
            }
            if ("false".equals(v)) {
                return false;
            }
        }
        return defaultValue;
    }
    
    @Override
    public int asInt(int def) {
        String s = _value;
        if (s == null) {
            return def;
        }
        s = s.trim();
        int len = s.length();
        if (len == 0) {
            return def;
        }
        // One more thing: use integer parsing for 'simple'
        int i = 0;
        if (i < len) { // skip leading sign:
            char c = s.charAt(0);
            if (c == '+') { // for plus, actually physically remove
                s = s.substring(1);
                len = s.length();
            } else if (c == '-') { // minus, just skip for checks, must retain
                ++i;
            }
        }
        for (; i < len; ++i) {
            char c = s.charAt(i);
            // if other symbols, parse as Double, coerce
            if (c > '9' || c < '0') {
                try {
                    return (int) Double.parseDouble(s);
                } catch (NumberFormatException e) {
                    return def;
                }
            }
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) { }
        return def;
    }

    @Override
    public long asLong(long def) {
        String s = _value;
        if (s == null) {
            return def;
        }
        s = s.trim();
        int len = s.length();
        if (len == 0) {
            return def;
        }
        // One more thing: use long parsing for 'simple'
        int i = 0;
        if (i < len) { // skip leading sign:
            char c = s.charAt(0);
            if (c == '+') { // for plus, actually physically remove
                s = s.substring(1);
                len = s.length();
            } else if (c == '-') { // minus, just skip for checks, must retain
                ++i;
            }
        }
        for (; i < len; ++i) {
            char c = s.charAt(i);
            // if other symbols, parse as Double, coerce
            if (c > '9' || c < '0') {
                try {
                    return (long) Double.parseDouble(s);
                } catch (NumberFormatException e) {
                    return def;
                }
            }
        }
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) { }
        return def;
    }
    
    @Override
    public double asDouble(double def) {
        String s = _value;
        if (s == null) { return def; }
        s = s.trim();
        int len = s.length();
        if (len == 0) {
            return def;
        }
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) { }
        return def;
    }
    
    /*
    /**********************************************************
    /* Overridden standard methods
    /**********************************************************
     */
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o instanceof TextNode) {
            return ((TextNode) o)._value.equals(_value);
        }
        return false;
    }
    
    @Override
    public int hashCode() { return _value.hashCode(); }

    /**
     * Different from other values, Strings need quoting
     */
    @Override
    public String toString()
    {
        int len = _value.length();
        len = len + 2 + (len >> 4);
        StringBuilder sb = new StringBuilder(len);
        appendQuoted(sb, _value);
        return sb.toString();
    }

    /**
     * Lookup table used for determining which output characters in
     * 7-bit ASCII range need to be quoted.
     */
    final static int[] sOutputEscapes128;
    static {
        int[] table = new int[128];
        // Control chars need generic escape sequence
        for (int i = 0; i < 32; ++i) {
            // 04-Mar-2011, tatu: Used to use "-(i + 1)", replaced with constant
            table[i] = -1;
        }
        /* Others (and some within that range too) have explicit shorter
         * sequences
         */
        table['"'] = '"';
        table['\\'] = '\\';
        // Escaping of slash is optional, so let's not add it
        table[0x08] = 'b';
        table[0x09] = 't';
        table[0x0C] = 'f';
        table[0x0A] = 'n';
        table[0x0D] = 'r';
        sOutputEscapes128 = table;
    }
    
    private final static char[] HC = "0123456789ABCDEF".toCharArray();
    
    protected static void appendQuoted(StringBuilder sb, String content)
    {
        sb.append('"');
        final int[] escCodes = sOutputEscapes128;
        int escLen = escCodes.length;
        for (int i = 0, len = content.length(); i < len; ++i) {
            char c = content.charAt(i);
            if (c >= escLen || escCodes[c] == 0) {
                sb.append(c);
                continue;
            }
            sb.append('\\');
            int escCode = escCodes[c];
            if (escCode < 0) { // generic quoting (hex value)
                // The only negative value sOutputEscapes128 returns
                // is CharacterEscapes.ESCAPE_STANDARD, which mean
                // appendQuotes should encode using the Unicode encoding;
                // not sure if this is the right way to encode for
                // CharacterEscapes.ESCAPE_CUSTOM or other (future)
                // CharacterEscapes.ESCAPE_XXX values.

                // We know that it has to fit in just 2 hex chars
                sb.append('u');
                sb.append('0');
                sb.append('0');
                int value = c;  // widening
                sb.append(HC[value >> 4]);
                sb.append(HC[value & 0xF]);
            } else { // "named", i.e. prepend with slash
                sb.append((char) escCode);
            }
        }
        sb.append('"');
    }

}
