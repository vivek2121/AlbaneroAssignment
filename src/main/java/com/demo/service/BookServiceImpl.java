package com.demo.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.BookDto;
import com.demo.entity.BookDetails;
import com.demo.entity.BookMetaData;
import com.demo.exception.DataNotFoundException;
import com.demo.repository.BookMetaDataRepository;
import com.demo.repository.BookRepository;
import com.demo.response.BookResponse;
import com.demo.util.CommonVariables;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private BookMetaDataRepository metaDataRepo;

	@Override
	public ResponseEntity<Object> saveBook(BookDto bookDto) {
		if (bookDto != null) {
			BookDetails bookDetails = new BookDetails();
			bookDetails.setBookName(bookDto.getBookName());
			bookDetails.setBookDescription(bookDto.getBookDescription());
			bookRepo.save(bookDetails);

			return new ResponseEntity<>(new BookResponse(new Date(), CommonVariables.STATUS_CODE_200,
					CommonVariables.DATA_SAVED_SUCCESSFULLY, bookDetails), HttpStatus.OK);
		} else {
			throw new DataNotFoundException(CommonVariables.DATA_NOT_FOUND);
		}
	}
	@Override
	public Map<String, List<String>> getMetaData() throws ClassNotFoundException, SQLException {
	    Map<String, List<String>> map=new HashMap<>(); 
	    List<String> tablesName=new ArrayList<>();
	    List<String> columnsName=new ArrayList<>();
	    List<String> columnsType=new ArrayList<>();
	   	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdetails", "root", "root");
		DatabaseMetaData dbmd=connection.getMetaData();		
		String table[]={"TABLE"};
		ResultSet rs=dbmd.getTables(null,null,null,table);  
		  
		while(rs.next()){  
//			System.out.println(rs.getString(3)); 
			tablesName.add(rs.getString(3));
			map.put("Tables", tablesName);
			break;
		}  
		Statement stmt = connection.createStatement();
		PreparedStatement ps=connection.prepareStatement("select * from book_details");
		rs = ps.executeQuery();
		ResultSetMetaData resultSetMetaData=rs.getMetaData();
		for(int i=1;i<=3;i++) {
//			System.out.println(resultSetMetaData.getColumnName(i));
//			System.out.println(resultSetMetaData.getColumnTypeName(i));
			columnsName.add(resultSetMetaData.getColumnName(i));
			map.put("Column Names", columnsName);
			columnsType.add(resultSetMetaData.getColumnTypeName(i));
			map.put("Column Type", columnsType);
		}	
			connection.close(); 
		return map;
	}
	@Override
	public Map<String, List<String>> storeMetaData(BookMetaData bookMetaData) throws ClassNotFoundException, SQLException {
		Map<String, List<String>> metaData = getMetaData();
		List<String> columnNames=new ArrayList<>();
		List<String> columnNmaeList = metaData.get("Column Names");
		for (String colName : columnNmaeList) {
			columnNames.add(colName);
//			System.err.println(colName);
		}
		bookMetaData.setColumnName(columnNames);
		List<String> columnType=new ArrayList<>();
		List<String> columnTypeList = metaData.get("Column Type");
		for (String colType : columnTypeList) {
			columnType.add(colType);
//			System.err.println(colType);
		}
		bookMetaData.setColumnType(columnType);
		List<String> tableName=new ArrayList<>();
		List<String> tableList = metaData.get("Column Type");
		for (String tables : tableList) {
			tableName.add(tables);
//			System.err.println(tables);
		}
		bookMetaData.setTables(tableName);
		metaDataRepo.save(bookMetaData);
		return metaData;
	}

}
