package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class Avatar {

    int id;
    String src;

    public Avatar(int id, String src) {
        this.id = id;
        this.src = src;
    }

    public Avatar() {
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
        Avatar avatar = (Avatar) o;
        return id == avatar.id && Objects.equals(src, avatar.src);
    }

    @Override
    public int hashCode() {
        int result = 31 + id;
        result = 31 * result + (src != null ? src.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Avatar{");
        sb.append("id=").append(id);
        sb.append(", src='").append(src).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
