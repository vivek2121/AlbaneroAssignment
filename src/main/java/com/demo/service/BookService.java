package com.demo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.demo.dto.BookDto;
import com.demo.entity.BookMetaData;

public interface BookService {

	public ResponseEntity<Object> saveBook(BookDto bookDto);

	public Map<String, List<String>> getMetaData() throws ClassNotFoundException,SQLException;

	public Map<String, List<String>> storeMetaData(BookMetaData bookMetaData) throws ClassNotFoundException,SQLException;
}
