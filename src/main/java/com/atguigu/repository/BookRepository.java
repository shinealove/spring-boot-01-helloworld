package com.atguigu.repository;

import com.atguigu.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
}
