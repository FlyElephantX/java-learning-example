package com.example.elasticsearch.respository;

import com.example.elasticsearch.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, String>, BookRepositoryCustom {

    List<Book> findBookByBookNameLike(String name);

    List<Book> findBookByBookNameContains(String name);
}
