package org.j110.lab1.books;

public class Main {
    public static void main(String[] args) {
        Publisher prospekt = new Publisher("Проспект", "Москва");
        Publisher piter = new Publisher("Питер", "Санкт-Петербург");
        Publisher bhv = new Publisher("БХВ", "Санкт-Петебург");
        Publisher dialektika = new Publisher("Диалектика", "Киев");
        Book[] books = new Book[]{
                new Book("Computer Science: основы программирования на Java, ООП, алгоритмы и структуры данных",
                        piter, 2018, "Седжвик Роберт", "Уэйн Кевин"),
                new Book("Разработка требований к программному обеспечению. 3-е издание, дополненное",
                        bhv, 2019, "Вигерс Карл"),
                new Book("Java. Полное руководство, 10-е издание", dialektika, 2018, "Шилдт Герберт"),
                new Book("C/C++. Процедурное программирование", bhv, 2017, "Полубенцева М.И."),
                new Book("Конституция РФ", prospekt, 2020)
        };
        Book.printAll(books);
        bhv.setCity("Санкт-Петербург");
        Book.printAll(books); // the output has changed for all books published by BHV because they all refer to the same object of the Publisher class that has been modified
    }
}
