package org.commander.equipment.mod2;

public class Weapon extends Equipment{
    public Weapon(String name){
        super(name);
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Weapon\n");
        builder.append(super.getModGroup().toString());
        return builder.toString();
    }
}
