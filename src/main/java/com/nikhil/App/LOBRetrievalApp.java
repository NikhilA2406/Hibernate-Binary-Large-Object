package com.nikhil.App;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nikhil.model.StudentInfo;

public class LOBRetrievalApp 
{
	public static void main(String arg[]) 
	{
		Configuration config = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		FileOutputStream fos=null;
		FileWriter writer=null;
		config=new Configuration();
		
		config.configure();
		
		config.addAnnotatedClass(StudentInfo.class);

		sessionFactory=config.buildSessionFactory();
		
		session=sessionFactory.openSession();
		 StudentInfo studentInfo = session.get(StudentInfo.class, 1);
		try 
		{
		fos=new FileOutputStream("Nikhil Jaiswal.png");
			 writer=new FileWriter("Personal_Info.txt");
			fos.write(studentInfo.getImage());
			
			writer.write(studentInfo.getTextFile());
			 
		} 
		catch (FileNotFoundException e1) 
		{
		
			e1.printStackTrace();
		}
		catch (Exception e1) 
		{
		
			e1.printStackTrace();
		}
		finally
		{
			
			try 
			{
				fos.close();
				writer.close();
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

