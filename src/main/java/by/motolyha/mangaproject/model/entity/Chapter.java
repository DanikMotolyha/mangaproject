package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class Chapter {

    private Book book;
    private int page;
    private String name;

    public Chapter(Book book, int page, String name) {
        this.book = book;
        this.page = page;
        this.name = name;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
                && Objects.equals(book, chapter.book)
                && Objects.equals(name, chapter.name);
    }

    @Override
    public int hashCode() {
        int result = 31 + (book != null ? book.hashCode() : 0);
        result = 31 * result + page;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;

    }

    @Override
    public String toString() {
        var sb = new StringBuilder("Chapter{");
        sb.append("book=").append(book);
        sb.append(", page=").append(page);
        sb.append(", chapterName='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
