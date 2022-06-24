package me.mrgeneralq.sleepmost.interfaces;

import me.mrgeneralq.sleepmost.models.DreamSession;

public interface IDreamSessionService {

    void createSession(DreamSession session);
    void removeSession(DreamSession session);

}
