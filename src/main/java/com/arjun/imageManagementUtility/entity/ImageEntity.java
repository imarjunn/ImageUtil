package com.arjun.imageManagementUtility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ImageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	private String imageName;
	private double imagesize;
	private int commonId;
	@Column(name = "image", nullable = false, columnDefinition = "mediumblob")
	private byte[] image;
	
	@ManyToOne
	private SignupEntity signup;
	
	private String base64Image;
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public double getImagesize() {
		return imagesize;
	}
	public void setImagesize(double imagesize) {
		this.imagesize = imagesize;
	}
	
	public int getCommonId() {
		return commonId;
	}

	public void setCommonId(int commonId) {
		this.commonId = commonId;
	}

	public SignupEntity getSignup() {
		return signup;
	}

	public void setSignup(SignupEntity signup) {
		this.signup = signup;
	}

	public ImageEntity() {
		super();
	}

	public ImageEntity( String imageName, double imagesize, int commonId, byte[] image, SignupEntity signup) {
		this.imageName = imageName;
		this.imagesize = imagesize;
		this.commonId = commonId;
		this.image = image;
		this.signup = signup;
	}
	
	

}
