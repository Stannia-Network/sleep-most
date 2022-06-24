package me.mrgeneralq.sleepmost.enums;

public enum DreamType {

    NORMAL("normal"),
    NIGHTMARE("nightmare"),
    LUCID("lucid");

    private String name;

    DreamType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }



}
