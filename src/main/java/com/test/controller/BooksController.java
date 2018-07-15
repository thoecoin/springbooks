package com.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.test.model.Books;
import com.test.service.BooksService;

@RestController
public class BooksController {

	@Autowired
	private BooksService booksService;
	
	/*---Add new book---*/
	@PostMapping("/books")
	public ResponseEntity<?> save(@RequestBody Books books) {
		Integer id = booksService.save(books);
		return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
	}
	
	/*---Get a book by id---*/
	@GetMapping("/books/{id}")
	public ResponseEntity<Books> get(@PathVariable("id") Integer id) {
		Books books = booksService.get(id);
		return ResponseEntity.ok().body(books);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/books")
	@ResponseBody
	public String list(Integer pageNumber, Integer pageSize, String sort, String search, String filter) {
		List<Object[]> books = booksService.list(pageNumber, pageSize, sort, search, filter);
		List<Object[]> totalData = booksService.list(null, null, sort, search, filter);
		Map map = new HashMap<>();
		if(books != null) {
			map = new HashMap<>();
			map.put("status", "success");
			map.put("code", 200);
			
			List<Map> listData = new ArrayList<>();
			for (Object[] objects : books) {
				Map data = new HashMap<>();
				data.put("id", Integer.parseInt(objects[0].toString())); //[0] = field ke 1. karna object -> String -> Integer
				data.put("title", objects[1]);
				data.put("price", Long.parseLong(objects[2].toString()));
				data.put("status", objects[3]);
				listData.add(data);
				map.put("data", listData);
			}
			
			Map meta = new HashMap<>();
			meta.put("page", pageNumber+1);
			meta.put("size", pageSize);
			meta.put("count", books.size() > pageSize ? pageSize : books.size());
			meta.put("totalPages", (int) Math.ceil(totalData.size() / pageSize.doubleValue()));
			meta.put("totalData", totalData.size());
			map.put("meta", meta);
			System.out.println("=-==-=-=-="+pageSize.doubleValue());
			System.out.println("=-==-=-=-="+pageSize);
		}
		
		return new Gson().toJson(map);
	}

//	/*---get all books---*/
//	@GetMapping("/books")
//	public ResponseEntity<List<Books>> list() {
//		List<Books> books = booksService.list();
//		return ResponseEntity.ok().body(books);
//	}

	/*---Update a book by id---*/
	@PutMapping("/books/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Books books) {
		booksService.update(id, books);
		return ResponseEntity.ok().body("Book has been updated successfully.");
	}

	/*---Delete a book by id---*/
	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		booksService.delete(id);
		return ResponseEntity.ok().body("Book has been deleted successfully.");
	}
}
