package by.motolyha.mangaproject.model.dto;

import by.motolyha.mangaproject.model.entity.MangaStatus;

import java.time.LocalDate;
import java.util.Objects;

public class MangaInfoDto {

    private String name;
    private String description;
    private LocalDate postDate;
    private MangaStatus mangaStatus;
    private int idAvatar;



    public MangaInfoDto(String name, String description,
                 LocalDate postDate, MangaStatus mangaStatus, int idAvatar) {
        this.name = name;
        this.description = description;
        this.postDate = postDate;
        this.mangaStatus = mangaStatus;
        this.idAvatar = idAvatar;
    }

    public MangaInfoDto() {
    }

    public int getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(int idAvatar) {
        this.idAvatar = idAvatar;
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
        MangaInfoDto manga = (MangaInfoDto) o;
        return  idAvatar == manga.idAvatar
                && Objects.equals(name, manga.name)
                && Objects.equals(description, manga.description)
                && Objects.equals(postDate, manga.postDate)
                && mangaStatus == manga.mangaStatus;
    }

    @Override
    public int hashCode() {

        int result = 31 * idAvatar;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (mangaStatus != null ? mangaStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", postDate=").append(postDate);
        sb.append(", bookStatus=").append(mangaStatus);
        sb.append(", idAvatar='").append(idAvatar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
