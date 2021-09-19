package by.motolyha.mangaproject.model.entity;

public enum ReadingStatus {
    STARTED("начато"),
    ABANDONED("заброшено"),
    WILL_READ("буду читать"),
    READ("закончено");

    private String name;

    ReadingStatus(String name) {
        this.name = name;
    }

}
