package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.BookDetails;

public interface BookRepository extends JpaRepository<BookDetails, Long>{

}
