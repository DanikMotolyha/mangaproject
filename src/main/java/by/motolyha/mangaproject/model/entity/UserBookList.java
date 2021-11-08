package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class UserBookList {

    private Manga manga;
    private User user;
    private ReadingStatus readingStatus;

    public UserBookList(Manga manga, User user, ReadingStatus readingStatus) {
        this.manga = manga;
        this.user = user;
        this.readingStatus = readingStatus;
    }

    public Manga getBook() {
        return manga;
    }

    public void setBook(Manga manga) {
        this.manga = manga;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBookList that = (UserBookList) o;
        return readingStatus == that.readingStatus && Objects.equals(manga, that.manga) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        int result = 31 + (manga != null ? manga.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (readingStatus != null ? readingStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserBookList{");
        sb.append("book=").append(manga);
        sb.append(", user=").append(user);
        sb.append(", readingStatus=").append(readingStatus);
        sb.append('}');
        return sb.toString();
    }
}
