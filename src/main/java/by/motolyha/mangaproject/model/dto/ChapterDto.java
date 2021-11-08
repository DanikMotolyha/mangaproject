package by.motolyha.mangaproject.model.dto;

import java.util.List;
import java.util.Objects;

public class ChapterDto {

    private String name;
    private int chapterPage;
    private List<Page> pages;
    private Page currentPage;

    public ChapterDto(String name, int chapterPage, List<Page> pages, Page currentPage) {
        this.name = name;
        this.chapterPage = chapterPage;
        this.pages = pages;
        this.currentPage = currentPage;
    }

    public ChapterDto() {

    }

    private static class Page {

        private int page;
        private String src;

        public Page(int page, String src) {
            this.page = page;
            this.src = src;
        }

        public Page() {
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Page page1 = (Page) o;
            return page == page1.page && Objects.equals(src, page1.src);
        }

        @Override
        public int hashCode() {
            int result = 31 * page;
            result = 31 * result + (src != null ? src.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Page{");
            sb.append("page=").append(page);
            sb.append(", src='").append(src).append('\'');
            sb.append('}');
            return sb.toString();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChapterPage() {
        return chapterPage;
    }

    public void setChapterPage(int chapterPage) {
        this.chapterPage = chapterPage;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }


}
