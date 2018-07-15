package com.test.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.model.Books;

@Repository
public class BooksDaoImp implements BooksDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer save(Books books) {
		sessionFactory.getCurrentSession().save(books);
		return books.getId();
	}
	
	@Override
	public Books get(Integer id) {
		return sessionFactory.getCurrentSession().get(Books.class, id);
	}
	
	@Override
	public List<Books> list() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Books> cq = cb.createQuery(Books.class);
		Root<Books> root = cq.from(Books.class);
		cq.select(root);
		Query<Books> query = session.createQuery(cq);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> list(Integer pageNumber, Integer pageSize, String sort, String search, String filter) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		int whereOrAnd = 0;
		
//		query agar flexible ketika tidak menggunakan salah satu / lebih param
		query.append("select * from books ");
		
		if(filter != null) {
			whereOrAnd = 1;
			query.append("where ");
			query.append("status = '"+filter+"' ");
		}
		
		if(search != null) {
			if(whereOrAnd == 1) {
				query.append("and ");
			}else {
				query.append("where ");
			}
			
			query.append("title like '%"+search+"%' ");
		}
		
		if(sort != null) {
			query.append("order by "+sort+" asc ");
		}
		
		if(pageNumber != null && pageSize != null) {
			query.append("limit "+pageNumber+", "+pageSize+" ");
		}

		Query<Object[]> generateQuery = session.createNativeQuery(query.toString());
		return generateQuery.getResultList();
	}

	@Override
	public void update(Integer id, Books books) {
		Session session = sessionFactory.getCurrentSession();
		Books book2 = session.byId(Books.class).load(id);
		book2.setTitle(books.getTitle());
		book2.setPrice(books.getPrice());
		book2.setStatus(books.getStatus());
		session.flush();
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Books books = session.byId(Books.class).load(id);
		session.delete(books);
	}
}
