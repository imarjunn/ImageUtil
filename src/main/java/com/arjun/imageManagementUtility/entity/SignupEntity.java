package com.arjun.imageManagementUtility.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SignupEntity {
	
	@Id
    @Column(length=50)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
    private String name;
    private String username;
    private String password;
    private String rePassword;
    
    @OneToMany(mappedBy = "signup",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageEntity> imageList = new ArrayList<ImageEntity>();
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ImageEntity> getImageList() {
		return imageList;
	}
	public void setImageList(List<ImageEntity> imageList) {
		this.imageList = imageList;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}
