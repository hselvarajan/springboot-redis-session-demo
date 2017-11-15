package hello;

import java.io.Serializable;

public class Foo implements Serializable{

    private String var;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
