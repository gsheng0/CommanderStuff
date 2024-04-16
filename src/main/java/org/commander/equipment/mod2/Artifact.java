package org.commander.equipment.mod2;

public class Artifact extends Equipment{
    public Artifact(String name){
        super(name);
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Artifact\n");
        builder.append(super.getModGroup().toString());
        return builder.toString();
    }
}
