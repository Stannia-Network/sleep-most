package me.mrgeneralq.sleepmost.models.abstracts;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.mrgeneralq.sleepmost.interfaces.ITimeline;

import java.util.ArrayList;
import java.util.List;

public abstract class Timeline<T extends TimelineAction> implements ITimeline {

    Multimap<Integer, T> timeLineActions = ArrayListMultimap.create();

    public List<T> getActionsByTime(Integer timeIndex){
        return new ArrayList<>(this.timeLineActions.get(timeIndex));
    }

    public void addTimelineAction(Integer index, T action){
        this.timeLineActions.put(index, action);
    }

    public void setTimelineActions(Integer index, List<T> actionList){
        for(T action: actionList)
        this.timeLineActions.put(index, action);
    }

    public void removeTimelineActions(Integer index){
        this.timeLineActions.removeAll(index);
    }

}
