package by.motolyha.mangaproject.model.entity;

import java.util.Arrays;

public enum MangaStatus {
    GOES_ON("продолжается"),
    FINISHED("закончено"),
    ANNOUNCE("анонс");

    private String name;

    MangaStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MangaStatus valueOfName(String name) {
        for (MangaStatus status : MangaStatus.values()) {
            if(status.getName().equals(name)){
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant " + name);
    }
}
