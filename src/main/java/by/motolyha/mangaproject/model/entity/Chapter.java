package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class Chapter {

    private int id;
    private int page;
    private String name;

    public Chapter(int id, int page, String name) {
        this.id = id;
        this.page = page;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var chapter = (Chapter) o;
        return page == chapter.page
                && id == chapter.id
                && Objects.equals(name, chapter.name);
    }

    @Override
    public int hashCode() {
        int result = 31 * id;
        result = 31 * result + page;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;

    }

    @Override
    public String toString() {
        var sb = new StringBuilder("Chapter{");
        sb.append("id=").append(id);
        sb.append(", page=").append(page);
        sb.append(", chapterName='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
