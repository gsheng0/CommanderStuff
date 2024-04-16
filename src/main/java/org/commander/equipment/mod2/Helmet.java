package org.commander.equipment.mod2;

public class Helmet extends Equipment{
    public Helmet(String name){
        super(name);
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Helmet\n");
        builder.append(super.getModGroup().toString());
        return builder.toString();
    }
}
