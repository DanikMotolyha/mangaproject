package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class BookReview implements Entity {

    private User user;
    private Book book;
    private int rating;
    private String description;

    public BookReview(User user, Book book, int rating, String description) {
        this.user = user;
        this.book = book;
        this.rating = rating;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReview that = (BookReview) o;
        return rating == that.rating
                && Objects.equals(user, that.user)
                && Objects.equals(book, that.book)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        int result = 31;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookReview{");
        sb.append("user=").append(user);
        sb.append(", book=").append(book);
        sb.append(", rating=").append(rating);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
