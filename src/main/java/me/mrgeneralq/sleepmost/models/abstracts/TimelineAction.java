package me.mrgeneralq.sleepmost.models.abstracts;

import me.mrgeneralq.sleepmost.interfaces.ITimelineAction;

public abstract class TimelineAction implements ITimelineAction {

    private String name;
    private Double chance;
    private String[] args;
    private String commandUsage;

    public TimelineAction(String name, Double chance, String commandUsage, String...args) {
        this.name = name;
        this.chance = chance;
        this.args = args;
        this.commandUsage = commandUsage;
    }


    public String getName(){
        return this.name;
    }

    public Double getChance(){
        return this.chance;
    }

    public String[] getArgs(){
        return this.args;
    }

    public String getCommandUsage(){
        return this.commandUsage;
    }

}
