package org.commander.equipment.mod2;

public class Gem extends Equipment{
    public Gem(String name){
        super(name);
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Gem\n");
        builder.append(super.getModGroup().toString());
        return builder.toString();
    }
}
