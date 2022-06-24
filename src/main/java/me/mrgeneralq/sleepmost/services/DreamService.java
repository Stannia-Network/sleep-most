package me.mrgeneralq.sleepmost.services;

import me.mrgeneralq.sleepmost.interfaces.IDreamService;
import me.mrgeneralq.sleepmost.models.Dream;
import me.mrgeneralq.sleepmost.repositories.impl.DreamRepository;

import java.io.IOException;

public class DreamService implements IDreamService {

    private final DreamRepository dreamRepository;

    public DreamService(DreamRepository dreamRepository) throws IOException {
        this.dreamRepository = dreamRepository;
        this.dreamRepository.load();
    }

    @Override
    public void createDream(Dream dream) {
        try {
            this.dreamRepository.createAndSave(dream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Dream getDream(String dreamName) {
        return this.dreamRepository.findById(dreamName);
    }

    @Override
    public void updateDream(Dream dream){
        try {
            this.dreamRepository.createAndSave(dream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean dreamExists(String name) {
        return this.dreamRepository.idExists(name);
    }

    @Override
    public void removeDream(String name) {
        this.dreamRepository.deleteById(name);
    }
}
