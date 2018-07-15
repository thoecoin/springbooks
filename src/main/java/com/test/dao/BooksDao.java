package com.test.dao;

import java.util.List;

import com.test.model.Books;

public interface BooksDao {
	Integer save(Books books);
	Books get(Integer id);
	List<Books> list();
	List<Object[]> list(Integer pageNumber, Integer pageSize, String sort, String search, String filter);
	void update(Integer id, Books books);
	void delete(Integer id);
}
