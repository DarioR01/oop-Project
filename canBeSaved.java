//Interface that forces the creation of a method that store the state of the class

import java.io.*;
public interface canBeSaved<T>
{
    public void outputObject(String file)throws IOException;
    }