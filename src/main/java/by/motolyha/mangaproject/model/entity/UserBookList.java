package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class UserBookList {

    private Book book;
    private User user;
    private ReadingStatus readingStatus;

    public UserBookList(Book book, User user, ReadingStatus readingStatus) {
        this.book = book;
        this.user = user;
        this.readingStatus = readingStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
        return readingStatus == that.readingStatus && Objects.equals(book, that.book) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        int result = 31 + (book != null ? book.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (readingStatus != null ? readingStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserBookList{");
        sb.append("book=").append(book);
        sb.append(", user=").append(user);
        sb.append(", readingStatus=").append(readingStatus);
        sb.append('}');
        return sb.toString();
    }
}
