package me.mrgeneralq.sleepmost.interfaces;

public interface ITimelineAction<T> {

    boolean isValidInput(String[] args);
    void execute(Object object);
    Double getChance();
    String getName();
    String[] getArgs();
    String getCommandUsage();
    boolean isSupportedInCurrentVersion();
    String serialize();
    T deserialize(String configLine);

}
