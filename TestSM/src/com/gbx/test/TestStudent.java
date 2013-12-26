package com.gbx.test;

import org.junit.Test;

import com.gbx.dao.StudentDao;
import com.gbx.daoimpl.StudentDaoImpl;
import com.gbx.entity.Student;

public class TestStudent {
	private StudentDao dao = new StudentDaoImpl();
	@Test
	public void add() throws Exception {
		Student s = new Student();
		s.setExamid("371425");
		s.setIdcard("10110");
		s.setLocation("山东");
		s.setName("大宝");
		s.setGrade(100);
		dao.add(s);
		System.out.println("添加成功");
	}
	@Test
	public void delete() throws Exception {
		String name = "大宝";
		boolean ok = dao.delete(name);
		if (ok) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}
	@Test
	public void query() throws Exception {
		String examid = "371425";
		Student student = dao.query(examid);
		System.out.println(student);
	}
}
