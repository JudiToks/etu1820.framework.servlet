package etu1820.framework;

public class Mapping 
{
    String className;
    String method;

    // getters & setters
    public String getClassName() 
    {
        return className;
    }
    public void setClassName(String className) 
    {
        this.className = className;
    }
    public String getMethod() 
    {
        return method;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    // constructor
    public Mapping(String className, String method) 
    {
        this.className = className;
        this.method = method;
    }
}