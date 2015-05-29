package com.scalableant.objectlike;

import java.util.Iterator;
import java.util.List;

public abstract class ArrayNode extends Node
{
    protected abstract List<Node> getList();
    
    @Override
    public Node get(int index)
    {
        if (index >= 0 && index < getList().size())
        {
            return getList().get(index);
        }
        return null;
    }

    @Override
    public Node path(String fieldName)
    {
        return MissingNode.getInstance();
    }

    @Override
    public Node path(int index)
    {
        Node n = get(index);
        return n == null ? MissingNode.getInstance() : n;
    }

    @Override
    public NodeType getNodeType()
    {
        return NodeType.ARRAY;
    }

    @Override
    public String asText()
    {
        return "";
    }

    @Override
    public Node findValue(String fieldName)
    {
        for (Node node : getList())
        {
            Node value = node.findValue(fieldName);
            if (value != null)
            {
                return value;
            }
        }
        
        return null;
    }

    @Override
    public Node findParent(String fieldName)
    {
        for (Node node : getList())
        {
            Node parent = node.findParent(fieldName);
            if (parent != null)
            {
                return parent;
            }
        }
        return null;
    }

    @Override
    public List<Node> findValues(String fieldName, List<Node> foundSoFar)
    {
        for (Node node : getList())
        {
            foundSoFar = node.findValues(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public List<String> findValuesAsText(String fieldName, List<String> foundSoFar)
    {
        for (Node node : getList())
        {
            foundSoFar = node.findValuesAsText(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public List<Node> findParents(String fieldName, List<Node> foundSoFar)
    {
        for (Node node : getList())
        {
            foundSoFar = node.findParents(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(16 + (size() << 4));
        sb.append('[');
        for (int i = 0, len = getList().size(); i < len; ++i) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(getList().get(i).toString());
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o instanceof ArrayNode) {
            return getList().equals(((ArrayNode) o).getList());
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return getList().hashCode();
    }
    
    @Override
    public int size()
    {
        return getList().size();
    }
    
    @Override
    public Iterator<Node> elements()
    {
        return getList().iterator();
    }

}
