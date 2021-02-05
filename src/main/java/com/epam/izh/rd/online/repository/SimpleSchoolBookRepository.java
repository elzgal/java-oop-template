package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;


public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] saveBook = new SchoolBook[count() + 1];
        System.arraycopy(schoolBooks, 0, saveBook, 0, schoolBooks.length);
        saveBook[saveBook.length - 1] = book;
        schoolBooks = saveBook;

        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] books = {};

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                SchoolBook[] newBooks = new SchoolBook[books.length + 1];
                System.arraycopy(books, 0, newBooks, 0, books.length);
                newBooks[newBooks.length - 1] = schoolBook;

                books = newBooks;
            }
        }

        return books;

    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] removeBooks = findByName(name);

        if (removeBooks.length == 0) {
            return false;
        }

        SchoolBook[] filteredArray = new SchoolBook[schoolBooks.length - removeBooks.length];
        int indexFilterArray = 0;

        for (SchoolBook schoolBook : schoolBooks) {
            if (!schoolBook.getName().equals(name)) {
                filteredArray[indexFilterArray] = schoolBook;
                indexFilterArray++;
            }
        }

        schoolBooks = filteredArray;

        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
