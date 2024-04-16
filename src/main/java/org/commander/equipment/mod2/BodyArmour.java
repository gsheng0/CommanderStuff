package org.commander.equipment.mod2;

public class BodyArmour extends Equipment{
    public BodyArmour(String name){
        super(name);
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Body Armour\n");
        builder.append(super.getModGroup().toString());
        return builder.toString();
    }
}
