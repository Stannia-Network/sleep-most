package me.mrgeneralq.sleepmost.models.abstracts;

public abstract class DreamTimelineAction extends TimelineAction {
    public DreamTimelineAction(String name, Double chance, String commandUsage, String... args) {
        super(name, chance, commandUsage, args);
    }
}
