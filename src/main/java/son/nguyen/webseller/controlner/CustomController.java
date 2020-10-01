package son.nguyen.webseller.controlner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import son.nguyen.webseller.model.Book;
import son.nguyen.webseller.service.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomController {

    private BookService bookService;


    private ElasticsearchRestTemplate elasticsearchTemplate;
@Autowired
    public CustomController(BookService bookService, ElasticsearchRestTemplate elasticsearchTemplate) {
        this.bookService = bookService;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @GetMapping(value = "/custom")
    public List<Book> custom() {

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(null, "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        bookList.add(new Book(null, "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        bookList.add(new Book(null, "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
        bookList.add(new Book(null, "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
        bookList.add(new Book(null, "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));
        List<Book> l=new ArrayList<>();
        for (Book book : bookList) {
          Book save=  bookService.save(book);
          l.add(save);
        }
        List<Book> result = new ArrayList<>();
        bookService.findByAuthor("Mkyong", PageRequest.of(0,3));
         return result;
    }
}
