package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TextRepository extends CrudRepository<Text, String>{

	@Override
    List<Text> findAll();

}
