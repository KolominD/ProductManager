package ru.netology.domain;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.domain.manager.ProductManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;


class ProductManagerTest {
    private ProductRepository repo = new ProductRepository();
    private ProductManager manager = new ProductManager(repo);
    private Book first = new Book(1, "Posle Bala", 768, "Tolstoy");
    private Book second = new Book(2, "Na dne", 890, "Gorkiy");
    private Book third = new Book(3, "Neznaika", 300, "Noskov");
    private Smartphone forth = new Smartphone(4, "Mobila", 600, "Pioneer");
    private Smartphone fifth = new Smartphone(5, "Truba", 9000, "Sony");
    private Smartphone sixth = new Smartphone(6, "Zvonilka", 8988, "Apple");
    private Book seventh = new Book(7, "War and Peace", 901, "Tolstoy");
    private Book eighth = new Book(8, "Oldwoman Izergeel", 890, "Gorkiy");


    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
        manager.add(eighth);
    }

    @Test
    void searchByAuthorTolstoy() {

        Product[] expected = new Product[]{first, seventh};
        Product[] actual = manager.searchBy("Tolstoy");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchByAthorGorkiy() {
        Product[] expected = new Product[]{second, eighth};
        Product[] actual = manager.searchBy("Gorkiy");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchByAuthorWithRemove() {
        repo.removeById(8);
        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Gorkiy");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchByName() {
        Product[] expected = new Product[]{second};
        Product[] actual = manager.searchBy("Na dne");
        assertArrayEquals(actual, expected);
    }

    @Test
    void searchByFirstID() {
        Product expected = first;
        Product actual = repo.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void searchByIDNoExisted() {
        Product expected = null;
        Product actual = repo.findById(12);
        assertEquals(expected, actual);

    }

    @Test
    void searchByNameSmartphone() {
        Product[] expected = new Product[]{fifth};
        Product[] actual = manager.searchBy("Truba");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByManufacturer() {
        Product[] expected = new Product[]{sixth};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNoExistNameSmartphone() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Pozvonil");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNoExistNameBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Paustovskiy");
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByNotExistId() {
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(-100);
        });
    }

    @Test
    void removeBySecondId() {
        repo.removeById(1);
        Product[] expected = new Product[]{second, third, forth, fifth, sixth, seventh, eighth};
        Product[] actual = repo.findAll();
        assertArrayEquals(actual, expected);

    }

}
