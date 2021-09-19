package by.motolyha.mangaproject.model.entity;

public enum BookStatus {
    FINISHED("закончено"),
    GOES_ON("продолжается"),
    ANOUNCE("анонс");

    private String name;

    BookStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
