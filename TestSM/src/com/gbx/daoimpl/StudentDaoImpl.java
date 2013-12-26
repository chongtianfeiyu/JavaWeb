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
		// �õ�Document����
		Document document = XmlUtils.getDocument();

		// ��������Ҫ��ӵı�ǩ
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

		// �õ�����ǩ�� Ȼ�����
		Element exam = (Element) document.getElementsByTagName("exam").item(0);
		exam.appendChild(studentTag);

		// ˢ��XML�ļ�
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
