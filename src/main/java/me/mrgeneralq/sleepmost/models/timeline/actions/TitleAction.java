package me.mrgeneralq.sleepmost.models.timeline.actions;

import me.mrgeneralq.sleepmost.models.abstracts.TimelineAction;

public class TitleAction extends TimelineAction {

    private final static String ACTION_NAME = "title";
    private final static String COMMAND_USAGE = "";

    private String title;
    private String subTitle;
    private int duration;


    public TitleAction(Double chance, String... args) {
        super(ACTION_NAME, chance, COMMAND_USAGE , args);
    }

    @Override
    public boolean isValidInput(String[] args) {
        return false;
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public boolean isSupportedInCurrentVersion() {
        return false;
    }

    @Override
    public String serialize() {
        return null;
    }

    @Override
    public Object deserialize(String configLine) {
        return null;
    }

    public String getTitle(){
        return this.title;
    }

    public String getSubTitle(){
        return this.subTitle;
    }

}
