package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class BookGenre {

    private Book book;
    private Genre genre;

    public BookGenre(Book book, Genre genre) {
        this.book = book;
        this.genre = genre;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenre bookGenre = (BookGenre) o;
        return Objects.equals(book, bookGenre.book)
                && Objects.equals(genre, bookGenre.genre);
    }

    @Override
    public int hashCode() {

        var result = 31 + (book != null ? book.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookGenre{");
        sb.append("book=").append(book);
        sb.append(", genre=").append(genre);
        sb.append('}');
        return sb.toString();
    }
}
