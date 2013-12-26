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
		System.out.println("(a)添加用户 (b)删除用户 (c)查看用户");
		System.out.println("请输出您的选择：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String op = null;
		try {
			op = br.readLine();
			if (op.equals("a")) {
				System.out.print("输出用户名 : ");
				String name = br.readLine();
				System.out.print("输出用户地址: ");
				String location = br.readLine();
				System.out.print("输出用户成绩: ");
				String grade = br.readLine();
				System.out.print("输出用户考号: ");
				String examid = br.readLine();
				System.out.print("输出用户身份证号: ");
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
					System.out.println("添加失败");
				}
				System.out.println("添加 成功");
			} else if (op.equals("b")){
				System.out.print("输入用户姓名: ");
				String name = br.readLine();
				try {
					dao.delete(name);
				} catch (Exception e) {
					System.out.println("删除失败");
					e.printStackTrace();
				}
				System.out.println("删除成功");
			} else if (op.equals("c")) {
				System.out.print("输入要查询用户的 考号 : ");
				String examid = br.readLine();
				try {
					Student student = dao.query(examid);
					System.out.println("查询的用户 : " + student);
				} catch (Exception e) {
					System.out.println("查询的用户不存在");
					e.printStackTrace();
				}
			} else {
				System.out.println("输入错误");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
