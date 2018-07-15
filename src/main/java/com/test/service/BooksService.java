package com.test.service;

import java.util.List;

import com.test.model.Books;

public interface BooksService {
	Integer save(Books books);
	Books get(Integer id);
	List<Books> list();
	List<Object[]> list(Integer pageNumber, Integer pageSize, String sort, String search, String filter);
	void update(Integer id, Books books);
	void delete(Integer id);
}
