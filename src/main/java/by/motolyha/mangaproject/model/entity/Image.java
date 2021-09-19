package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class Image implements Entity {

    private int id;
    private int page;
    private String src;

    public Image(int id, int page, String src) {
        this.id = id;
        this.page = page;
        this.src = src;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var image = (Image) o;
        return id == image.id && page == image.page && Objects.equals(src, image.src);
    }

    @Override
    public int hashCode() {

        int result = 31 * id;
        result = 31 * result + page;
        result = 31 * result + (src != null ? src.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("Image{");
        sb.append("id=").append(id);
        sb.append("page=").append(page);
        sb.append(", imageSrc='").append(src).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
