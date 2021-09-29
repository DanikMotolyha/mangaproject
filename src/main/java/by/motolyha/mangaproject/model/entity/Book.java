package by.motolyha.mangaproject.model.entity;

import java.sql.Date;
import java.util.Objects;

public class Book {

    private int id;
    private String name;
    private String description;
    private BookType bookType;
    private Date postDate;
    private BookStatus bookStatus;
    private String avatarSrc;

    public Book(int id, String name, String description, BookType bookType,
                Date postDate, BookStatus bookStatus, String avatarSrc) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.bookType = bookType;
        this.postDate = postDate;
        this.bookStatus = bookStatus;
        this.avatarSrc = avatarSrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var book = (Book) o;
        return id == book.id
                && Objects.equals(name, book.name)
                && Objects.equals(description, book.description)
                && Objects.equals(bookType, book.bookType)
                && Objects.equals(postDate, book.postDate)
                && bookStatus == book.bookStatus
                && Objects.equals(avatarSrc, book.avatarSrc);
    }

    @Override
    public int hashCode() {

        int result = 31 * id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (bookType != null ? bookType.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (bookStatus != null ? bookStatus.hashCode() : 0);
        result = 31 * result + (avatarSrc != null ? avatarSrc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", bookType=").append(bookType);
        sb.append(", postDate=").append(postDate);
        sb.append(", bookStatus=").append(bookStatus);
        sb.append(", avatarSrc='").append(avatarSrc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
