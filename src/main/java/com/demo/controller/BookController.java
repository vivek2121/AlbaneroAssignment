package com.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.BookDto;
import com.demo.entity.BookMetaData;
import com.demo.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/savebook")
	public ResponseEntity<Object> saveBook(@RequestBody BookDto bookDto) {
		ResponseEntity<Object> bookDetails = bookService.saveBook(bookDto);
		return bookDetails;
	}

	@GetMapping("/books")
	public Map<String,  List<String>> getMetaData() throws ClassNotFoundException, SQLException {
		Map<String, List<String>> map = bookService.getMetaData();
		return map;
	}
	@PostMapping("/storebooks")
	public Map<String,  List<String>> storeMetaData(BookMetaData bookMetaData) throws ClassNotFoundException, SQLException {
		Map<String, List<String>> map = bookService.storeMetaData(bookMetaData);
		return map;
	}
	

}
