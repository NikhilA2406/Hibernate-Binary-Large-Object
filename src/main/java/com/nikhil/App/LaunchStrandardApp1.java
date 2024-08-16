package com.nikhil.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nikhil.model.StudentInfo;

public class LaunchStrandardApp1 {

	public static void main(String[] args) 
	{
		Configuration config = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		FileInputStream fis=null;
		boolean flag=false;
		byte image[]=null;
		File file=null;
		FileReader reader=null;
		char txt[]=null;
		
		config=new Configuration();
		
		config.configure();
		
		config.addAnnotatedClass(StudentInfo.class);

		sessionFactory=config.buildSessionFactory();
		
		session=sessionFactory.openSession();
		
		try 
		{
			 fis=new FileInputStream("C:\\Users\\LENOVO\\Desktop\\Nikhil Jaiswal.png");
			 image=new byte[fis.available()];	
			 fis.read(image);
			 
			 file=new File("C:\\Users\\LENOVO\\Desktop\\Personal_Info.txt");
			 reader=new FileReader(file);
			 txt=new char[(int)file.length()];
			 reader.read(txt);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		StudentInfo student=new StudentInfo();
	
		student.setsName("Rohan");
		student.setScity("London");
		student.setImage(image);
		student.setTextFile(txt);
		
		try
		{
			transaction=session.beginTransaction();
			//session.save(student);
			session.persist(student);
			flag=true;
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(flag==true)
			{
				transaction.commit();
			}
			else
			{
				transaction.rollback();
			}
			
			try 
			{
				fis.close();
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			session.close();
			sessionFactory.close();
		}
	}

}
