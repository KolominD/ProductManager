package domain.manager;

import domain.products.Book;
import repository.ProductRepository;
import domain.products.Product;

public class ProductManager {
    private final ProductRepository repo;

    public ProductManager(ProductRepository repo){
        this.repo = repo;
    }
    public void add(Product product){
        repo.save (product);
    }
    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product: repo.findAll()){
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result,0,tmp,0,result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }

        }

        return result;
    }

    public boolean matches(Product product, String search) {
        if (product instanceof Book) { // если в параметре product лежит объект класса Book
            Book book = (Book) product; // положем его в переменную типа Book чтобы пользоваться методами класса Book
            if (book.getAuthor().contains(search)) { // проверим есть ли поисковое слово в данных об авторе
                return true;
            }
            if (book.getName().contains(search)) {
                return true;
            }
            return false;
        }

        return false;
    }
}
