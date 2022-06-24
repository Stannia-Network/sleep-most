package me.mrgeneralq.sleepmost.interfaces;

import me.mrgeneralq.sleepmost.models.Dream;
public interface IDreamService {

    void createDream(Dream dream);
    Dream getDream(String name);

    void updateDream(Dream dream);

    boolean dreamExists(String name);
    void removeDream(String name);

}
