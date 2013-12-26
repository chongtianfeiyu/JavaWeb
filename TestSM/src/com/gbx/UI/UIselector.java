package com.gbx.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gbx.dao.StudentDao;
import com.gbx.daoimpl.StudentDaoImpl;
import com.gbx.entity.Student;

public class UIselector {
	private static StudentDao dao = new StudentDaoImpl();
	public static void main(String[] args) {
		System.out.println("(a)����û� (b)ɾ���û� (c)�鿴�û�");
		System.out.println("���������ѡ��");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String op = null;
		try {
			op = br.readLine();
			if (op.equals("a")) {
				System.out.print("����û��� : ");
				String name = br.readLine();
				System.out.print("����û���ַ: ");
				String location = br.readLine();
				System.out.print("����û��ɼ�: ");
				String grade = br.readLine();
				System.out.print("����û�����: ");
				String examid = br.readLine();
				System.out.print("����û����֤��: ");
				String idcard = br.readLine();
				
				Student student = new Student();
				student.setName(name);
				student.setLocation(location);
				student.setIdcard(idcard);
				student.setExamid(examid);
				student.setGrade(Double.parseDouble(grade));
				try {
					dao.add(student);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("���ʧ��");
				}
				System.out.println("��� �ɹ�");
			} else if (op.equals("b")){
				System.out.print("�����û�����: ");
				String name = br.readLine();
				try {
					dao.delete(name);
				} catch (Exception e) {
					System.out.println("ɾ��ʧ��");
					e.printStackTrace();
				}
				System.out.println("ɾ���ɹ�");
			} else if (op.equals("c")) {
				System.out.print("����Ҫ��ѯ�û��� ���� : ");
				String examid = br.readLine();
				try {
					Student student = dao.query(examid);
					System.out.println("��ѯ���û� : " + student);
				} catch (Exception e) {
					System.out.println("��ѯ���û�������");
					e.printStackTrace();
				}
			} else {
				System.out.println("�������");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
