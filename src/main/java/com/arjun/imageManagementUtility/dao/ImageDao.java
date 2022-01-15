package com.arjun.imageManagementUtility.dao;

import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.arjun.imageManagementUtility.entity.ImageEntity;
import com.arjun.imageManagementUtility.entity.SignupEntity;
import com.arjun.imageManagementUtility.util.HibernateUtil;

public class ImageDao {
	
	public void saveImage(ImageEntity image) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
            transaction = session.beginTransaction();
            session.save(image);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
	public int getUserId(String username) {
		Transaction transaction = null;
		int id = 0;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			NativeQuery query = session.createSQLQuery("Select * from SignupEntity S where S.username = :username");
			query.setParameter("username",username);
			query.addEntity(SignupEntity.class);
			SignupEntity signup = (SignupEntity) query.list().get(0);
			id = signup.getId();
			transaction.commit();
		}catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}
		
		return id;
	}
	
	public List<ImageEntity> getListData(int identity){
		ImageDao imgdao = new ImageDao();
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<ImageEntity> imageList = null;
		try {
			transaction = session.beginTransaction();
			NativeQuery query = session.createSQLQuery("Select * from ImageEntity I where I.commonid = :commonid");
			query.setParameter("commonid",identity);
			query.addEntity(ImageEntity.class);
			imageList = query.list();
			transaction.commit();
			for (ImageEntity convertImg : imageList) {
				convertImg.setBase64Image(Base64.getEncoder().encodeToString(convertImg.getImage()));
			}
			
	}catch (Exception e) {
		if (transaction != null) {
			transaction.rollback();
			System.err.print(e);
		}}
		return imageList;
		}
	
	
	public double convertToMB(double imagesize) {
		double imageSizeInMB = imagesize/1024;
		imageSizeInMB /= 1024;
		imageSizeInMB = Math.round(imageSizeInMB*100)/100D;
		return imageSizeInMB;
		
	}
	
	public void deleteImage(int delId) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			NativeQuery query = session.createSQLQuery("Delete from ImageEntity where imageId = :imageId");
			query.setParameter("imageId",delId);
			query.addEntity(ImageEntity.class);
			query.executeUpdate();
			transaction.commit();
		}catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				System.err.print(e);
			}
		}
		
	}
	
	public void editImage(int editId, String imgName, byte[] img,double imgSize) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			NativeQuery query = session.createSQLQuery("Update ImageEntity set imageName = :imageName, image = :image,imagesize=:imagesize where imageId = :imageId");
			query.setParameter("imageId",editId);
			query.setParameter("imageName",imgName);
			query.setParameter("image",img);
			query.setParameter("imagesize",imgSize);
			query.addEntity(ImageEntity.class);
			query.executeUpdate();
			transaction.commit();
	}catch (Exception e) {
		if (transaction != null) {
			transaction.rollback();
			System.err.print(e);
		}
	}
}
	
	public double getTotalDataSize(int identity) {
		ImageDao imgdao = new ImageDao();
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<ImageEntity> imageList = null;
		double totalSize=0;
		try {
			transaction = session.beginTransaction();
			NativeQuery query = session.createSQLQuery("Select * from ImageEntity I where I.commonid = :commonid");
			query.setParameter("commonid",identity);
			query.addEntity(ImageEntity.class);
			imageList = query.list();
			transaction.commit();
			Iterator<ImageEntity> iterator = imageList.iterator();
			while (iterator.hasNext()) {
				totalSize = totalSize + iterator.next().getImagesize();
			}
	}catch (Exception e) {
		if (transaction != null) {
			transaction.rollback();
			System.err.print(e);
		}
	}
		return totalSize;
}

}
	

