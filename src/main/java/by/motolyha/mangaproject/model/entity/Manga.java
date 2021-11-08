package by.motolyha.mangaproject.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Manga {

    private long id;
    private String name;
    private String description;
    private LocalDate postDate;
    private MangaStatus mangaStatus;
    private Avatar avatar;


    public Manga(long id, String name, String description,
                 LocalDate postDate, MangaStatus mangaStatus, Avatar avatar) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.postDate = postDate;
        this.mangaStatus = mangaStatus;
        this.avatar = avatar;
    }

    public Manga() {

    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public MangaStatus getMangaStatus() {
        return mangaStatus;
    }

    public void setMangaStatus(MangaStatus mangaStatus) {
        this.mangaStatus = mangaStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manga manga = (Manga) o;
        return id == manga.id
                && Objects.equals(avatar, manga.avatar)
                && Objects.equals(name, manga.name)
                && Objects.equals(description, manga.description)
                && Objects.equals(postDate, manga.postDate)
                && mangaStatus == manga.mangaStatus;
    }

    @Override
    public int hashCode() {

        long result = 31 * id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (mangaStatus != null ? mangaStatus.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return (int) result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", postDate=").append(postDate);
        sb.append(", bookStatus=").append(mangaStatus);
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
