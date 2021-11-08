package by.motolyha.mangaproject.controller.imagecontroller;

public class CommandResult {

    private final String page;
    private final byte[] image;

    public CommandResult(String page, byte[] image) {
        this.page = page;
        this.image = image;
    }

    public CommandResult(String page) {
        this.page = page;
        this.image = null;
    }

    public CommandResult(byte[] image) {
        this.page = null;
        this.image = image;
    }

    public String getPage() {
        return page;
    }

    public byte[] getImage() {
        return image;
    }
}
