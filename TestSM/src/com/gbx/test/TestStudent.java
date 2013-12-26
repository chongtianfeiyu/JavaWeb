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
		s.setLocation("ɽ��");
		s.setName("��");
		s.setGrade(100);
		dao.add(s);
		System.out.println("��ӳɹ�");
	}
	@Test
	public void delete() throws Exception {
		String name = "��";
		boolean ok = dao.delete(name);
		if (ok) {
			System.out.println("ɾ���ɹ�");
		} else {
			System.out.println("ɾ��ʧ��");
		}
	}
	@Test
	public void query() throws Exception {
		String examid = "371425";
		Student student = dao.query(examid);
		System.out.println(student);
	}
}
