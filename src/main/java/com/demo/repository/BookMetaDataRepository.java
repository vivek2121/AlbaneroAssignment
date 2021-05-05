package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.BookMetaData;

public interface BookMetaDataRepository extends JpaRepository<BookMetaData, Long>{

}
