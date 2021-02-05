package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        } else {
            authors = Arrays.copyOf(authors, count() + 1);
            authors[count() - 1] = author;
        }
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        String authorName = author.getName();
        String authorLastName = author.getLastName();

        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] filteredAuthorsArray = new Author[count() - 1];

        for (int i = 0; i < count(); i++) {
            if (authors[i].getName().equals(authorName) && authors[i].getLastName().equals(authorLastName)) {
                System.arraycopy(authors, i, filteredAuthorsArray, i, filteredAuthorsArray.length - i);
            } else {
                filteredAuthorsArray[i] = authors[i];
            }
        }

        authors = filteredAuthorsArray;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
