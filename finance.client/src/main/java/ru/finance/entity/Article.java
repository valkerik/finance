package ru.finance.entity;

import ru.finance.exception.ModelException;

import java.util.Objects;

public class Article{
    private String title;

    public Article() {
    }

    public Article(String title) throws ModelException {
        if(title.length() ==0) throw new ModelException(0);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                '}';
    }
}
