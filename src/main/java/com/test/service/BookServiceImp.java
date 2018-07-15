package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.BooksDao;
import com.test.model.Books;

@Service
@Transactional(readOnly = true)
public class BookServiceImp implements BooksService {
	@Autowired
	private BooksDao booksDao;

	@Transactional
	@Override
	public Integer save(Books books) {
		return booksDao.save(books);
	}

	@Override
	public Books get(Integer id) {
		return booksDao.get(id);
	}

	@Override
	public List<Books> list() {
		return booksDao.list();
	}
	
	@Override
	public List<Object[]> list(Integer pageNumber, Integer pageSize, String sort, String search, String filter) {
		return booksDao.list(pageNumber, pageSize, sort, search, filter);
	}

	@Transactional
	@Override
	public void update(Integer id, Books books) {
		booksDao.update(id, books);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		booksDao.delete(id);
	}

}
