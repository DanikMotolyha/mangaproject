package by.motolyha.mangaproject.model.dto;

import by.motolyha.mangaproject.model.entity.MangaStatus;

import java.time.LocalDate;
import java.util.Objects;

public class MangaBriefDto {

    private long id;
    private String name;
    private int idAvatar;
    private LocalDate postDate;
    private MangaStatus status;

    public MangaBriefDto(long id, String name, int idAvatar,
                         LocalDate postDate, MangaStatus status) {
        this.id = id;
        this.name = name;
        this.idAvatar = idAvatar;
        this.postDate = postDate;
        this.status = status;
    }

    public MangaBriefDto() {
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

    public int getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(int idAvatar) {
        this.idAvatar = idAvatar;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public MangaStatus getStatus() {
        return status;
    }

    public void setStatus(MangaStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MangaBriefDto mangaBriefDto = (MangaBriefDto) o;
        return Objects.equals(name, mangaBriefDto.name)
                && idAvatar == mangaBriefDto.idAvatar
                && Objects.equals(postDate, mangaBriefDto.postDate)
                && status == mangaBriefDto.status;
    }

    @Override
    public int hashCode() {
        long result = 31 * id;
        result = 31L * (name != null ? name.hashCode() : 0);
        result = 31 * result + idAvatar;
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return (int) result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MangaData{");
        sb.append("id='").append(id).append('\'');
        sb.append("name='").append(name).append('\'');
        sb.append(", idAvatar='").append(idAvatar).append('\'');
        sb.append(", post_date=").append(postDate);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
