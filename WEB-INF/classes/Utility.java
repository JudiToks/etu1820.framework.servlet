package etu1820.framework;

import java.io.File;
import java.util.Objects;
import java.util.Vector;

public class Utility 
{
    private String value;
    private String className;
    private String method;

    // getters & setters
    public String getValue() 
    {
        return value;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }
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
    public Utility(String value, String className, String method) 
    {
        this.setValue(value);
        this.setClassName(className);
        this.setMethod(method);
    }

    // function
    public static Vector<String> readPackage(String path)
    {
        Vector<String> stringVector = new Vector<>();
        File file = new File(path);

        for (int i = 0; i < Objects.requireNonNull(file.listFiles()).length; i++)
        {
            stringVector.add(Objects.requireNonNull(file.listFiles())[i].toString());

            if (Objects.requireNonNull(file.listFiles())[i].isDirectory())
            {
                stringVector.addAll(readPackage(Objects.requireNonNull(file.listFiles())[i].getPath()));
            }
        }
        return stringVector;
    }
    
    public static Vector<Utility> readAnnotation(String path)
    {
        Vector<Utility> utilities = new Vector<>();
        File file = new File(path);

        if (!file.isDirectory())
        {
            try
            {
                Vector<String> paths = new Vector<>();

                for (int i = file.getAbsolutePath().split("/").length - 1; i > 0; i--)
                {
                    if (file.getAbsolutePath().split("/")[i].equalsIgnoreCase("classes"))
                    {
                        break;
                    }
                    else
                    {
                        paths.add(file.getAbsolutePath().split("/")[i]);
                    }
                }

                StringBuilder stringBuilder = new StringBuilder();

                for (int i = paths.size() - 1; i >= 0; i--)
                {
                    stringBuilder.append(paths.get(i));
                    if (i > 0)
                    {
                        stringBuilder.append(".");
                    }
                }

                path = stringBuilder.toString();
                stringBuilder = new StringBuilder();

                for (int i = 0; i < path.split("\\.").length - 1; i++)
                {
                    stringBuilder.append(path.split("\\.")[i]);
                    if (i < path.split("\\.").length - 2)
                    {
                        stringBuilder.append(".");
                    }
                }

                Class<?> c = Class.forName(stringBuilder.toString());

                for (int j = 0; j < c.getDeclaredMethods().length; j++)
                {
                    Urls urls = c.getDeclaredMethods()[j].getAnnotation(Urls.class);
                    if (urls != null)
                    {
                        utilities.add(new Utility(urls.url(), c.getName(), c.getDeclaredMethods()[j].getName()));
                    }
                }
            }
            catch (Exception ignored)
            {

            }
        }
        return utilities;
    }
}