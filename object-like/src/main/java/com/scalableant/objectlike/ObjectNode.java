package com.scalableant.objectlike;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class ObjectNode extends Node
{
    protected abstract Map<String, Node> getMap();
    
    @Override
    public Node get(int index)
    {
        return null;
    }
    
    @Override
    public Iterator<String> fieldNames()
    {
        return getMap().keySet().iterator();
    }
    
    @Override
    public Iterator<Map.Entry<String,Node>> fields()
    {
        return getMap().entrySet().iterator();
    }

    @Override
    public Node get(String fieldName)
    {
        return getMap().get(fieldName);
    }
    
    @Override
    public Node path(String fieldName)
    {
        Node n = get(fieldName);
        return n == null ? MissingNode.getInstance() : n;
    }

    @Override
    public Node path(int index)
    {
        return MissingNode.getInstance();
    }

    @Override
    public NodeType getNodeType()
    {
        return NodeType.OBJECT;
    }

    @Override
    public String asText()
    {
        return "";
    }

    @Override
    public Node findValue(String fieldName)
    {
        for (Map.Entry<String,Node> entry : getMap().entrySet())
        {
            if (fieldName.equals(entry.getKey()))
            {
                return entry.getValue();
            }
            Node value = entry.getValue().findValue(fieldName);
   
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
        for (Map.Entry<String, Node> entry : getMap().entrySet())
        {
            if (fieldName.equals(entry.getKey()))
            {
                return this;
            }
            Node value = entry.getValue().findParent(fieldName);
            if (value != null)
            {
                return value;
            }
        }
        return null;
    }

    @Override
    public List<Node> findValues(String fieldName, List<Node> foundSoFar)
    {
        for (Map.Entry<String,Node> entry : getMap().entrySet())
        {
            if (fieldName.equals(entry.getKey())) {
                if (foundSoFar == null) 
                {
                    foundSoFar = new ArrayList<Node>();
                }
                foundSoFar.add(entry.getValue());
            }
            else
            {
                foundSoFar = entry.getValue().findValues(fieldName, foundSoFar);
            }
        }
        return foundSoFar;
    }

    @Override
    public List<String> findValuesAsText(String fieldName, List<String> foundSoFar)
    {
        for (Map.Entry<String, Node> entry : getMap().entrySet())
        {
            if (fieldName.equals(entry.getKey()))
            {
                if (foundSoFar == null)
                {
                    foundSoFar = new ArrayList<String>();
                }
                foundSoFar.add(entry.getValue().asText());
            }
            else
            {
                foundSoFar = entry.getValue().findValuesAsText(fieldName, foundSoFar);
            }
        }
        return foundSoFar;
    }

    @Override
    public List<Node> findParents(String fieldName, List<Node> foundSoFar)
    {
        for (Map.Entry<String,Node> entry : getMap().entrySet())
        {
            if (fieldName.equals(entry.getKey()))
            {
                if (foundSoFar == null)
                {
                    foundSoFar = new ArrayList<Node>();
                }
                foundSoFar.add(this);
            }
            else
            {
                foundSoFar = entry.getValue().findParents(fieldName, foundSoFar);
            }
        }
        return foundSoFar;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(32 + (size() << 4));
        sb.append("{");
        int count = 0;
        for (Map.Entry<String, Node> en : getMap().entrySet()) {
            if (count > 0) {
                sb.append(",");
            }
            ++count;
            TextNode.appendQuoted(sb, en.getKey());
            sb.append(':');
            sb.append(en.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (o == null) return false;
        if (o instanceof ObjectNode) {
            return getMap().equals(((ObjectNode) o).getMap());
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return getMap().hashCode();
    }
    
    @Override
    public Iterator<Node> elements()
    {
        return getMap().values().iterator();
    }
}
