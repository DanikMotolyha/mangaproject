package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class ChapterImage {

    private Chapter chapter;
    private Image image;

    public ChapterImage(Chapter chapter, Image image) {
        this.chapter = chapter;
        this.image = image;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterImage that = (ChapterImage) o;
        return Objects.equals(image, that.image)
                && Objects.equals(chapter, that.chapter);
    }

    @Override
    public int hashCode() {

        int result = 31;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (chapter != null ? chapter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChapterImage{");
        sb.append(", image=").append(image);
        sb.append(", chapter=").append(chapter);
        sb.append('}');
        return sb.toString();
    }
}

