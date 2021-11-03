package domain.manager;

import domain.products.Book;
import domain.products.Product;
import domain.products.Smartphone;
import org.junit.jupiter.api.Test;
import repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductManagerTest {
    private ProductRepository repo = new ProductRepository();
    private ProductManager manager = new ProductManager(repo);
    private Book first = new Book(1, "Posle Bala", 768, "Tolstoy", 89090, 1890);
    private Book second = new Book(2, "Na dne", 890, "Gorkiy", 200, 1902);
    private Book third = new Book(3, "Neznaika", 300, "Noskov", 300, 1020);
    private Smartphone forth = new Smartphone(4, "Mobila", 600, "Pioneer");
    private Smartphone fifth = new Smartphone(5, "Truba", 9000, "Sony");
    private Smartphone sixth = new Smartphone(6, "Zvonilka", 8988, "Apple");
    private Book seventh = new Book(7, "War and Peace", 901, "Tolstoy", 99101, 1899);
    private Book eighth = new Book(8, "Oldwoman Izergeel", 890, "Gorkiy", 100, 1910);

    @Test
    void searchBy() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(sixth);
        manager.add(fifth);
        manager.add(seventh);
        Product[] expected = new Product[]{first, seventh};
        Product[] actual = manager.searchBy("Tolstoy");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchBy1() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(sixth);
        manager.add(fifth);
        manager.add(seventh);
        manager.add(eighth);
        Product[] expected = new Product[]{second, eighth};
        Product[] actual = manager.searchBy("Gorkiy");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchBy2() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(sixth);
        manager.add(fifth);
        manager.add(seventh);
        manager.add(eighth);
        repo.removeById(8);
        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Gorkiy");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchByName() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(sixth);
        manager.add(fifth);
        manager.add(seventh);
        manager.add(eighth);
        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Na dne");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchByID1() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(sixth);
        manager.add(fifth);
        manager.add(seventh);
        manager.add(eighth);
        Product expected = first;
        Product actual = repo.findById(1);
        assertEquals(expected,actual);
    }
    @Test
    void searchByIDNoExisted() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(sixth);
        manager.add(fifth);
        manager.add(seventh);
        manager.add(eighth);
        Product expected = null;
        Product actual = repo.findById(12);
        assertEquals(expected,actual);

    }
}
