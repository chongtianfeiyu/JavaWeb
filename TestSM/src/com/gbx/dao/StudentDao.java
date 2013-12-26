package com.gbx.dao;

import com.gbx.entity.Student;

public interface StudentDao {
	public boolean add(Student student) throws Exception ;
	public boolean delete(String name) throws Exception;
	public Student query(String examid) throws Exception ;
	
}
