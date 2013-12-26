package com.gbx.daoimpl;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.gbx.dao.StudentDao;
import com.gbx.entity.Student;

import com.gbx.utils.XmlUtils;

public class StudentDaoImpl implements StudentDao {

	@Override
	public boolean add(Student student) throws Exception {
		// 得到Document对象
		Document document = XmlUtils.getDocument();

		// 创建我们要添加的标签
		Element studentTag = document.createElement("student");
		studentTag.setAttribute("idcard",student.getIdcard());
		studentTag.setAttribute("examid", student.getExamid());
		Element name = document.createElement("name");
		name.setTextContent(student.getName());
		Element location = document.createElement("location");
		location.setTextContent(student.getLocation());
		Element grade = document.createElement("grade");
		grade.setTextContent(student.getGrade()+"");
		
		studentTag.appendChild(name);
		studentTag.appendChild(location);
		studentTag.appendChild(grade);

		// 得到父标签， 然后添加
		Element exam = (Element) document.getElementsByTagName("exam").item(0);
		exam.appendChild(studentTag);

		// 刷新XML文件
		XmlUtils.refreshXML(document);

		return true;
	}

	@Override
	public boolean delete(String name) throws Exception {
		boolean delSuccess = false;
		Document document = XmlUtils.getDocument();
		NodeList students = document.getElementsByTagName("student");
       
		for (int i = 0; i < students.getLength(); ++i) {
			Element student = (Element) students.item(i);
//			System.out.println("name = " + student.getElementsByTagName("name").item(0).getTextContent());
			if (student.getElementsByTagName("name").item(0).getTextContent().equals(name)) {
				student.getParentNode().removeChild(student);
				delSuccess = true;
				break;
			}
		}
		XmlUtils.refreshXML(document);
		return delSuccess;
	}

	@Override
	public Student query(String examid) throws Exception {
		Student student = null;
		Document document = XmlUtils.getDocument();
		NodeList students = document.getElementsByTagName("student");
		for (int i = 0; i < students.getLength(); ++i) {
			Element studentInfo = (Element) students.item(i);
			if (studentInfo.getAttribute("examid").equals(examid)) {
				student = new Student();
				student.setIdcard(studentInfo.getAttribute("idcard"));
				student.setExamid(examid);
				student.setName(studentInfo.getElementsByTagName("name").item(0).getTextContent());
				student.setLocation(studentInfo.getElementsByTagName("location").item(0).getTextContent());
				student.setGrade(Double.parseDouble(studentInfo.getElementsByTagName("grade").item(0).getTextContent()));
				break;
			}
		}
		return student;
	}

}
