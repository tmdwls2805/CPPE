package com.hoseo.rot.admin;

import lombok.Data;

@Data
public class Product {
	private int ProductNum;
	private String productKind;
	private String productName;
	private String productOrigin;
	private int productPrice;
	private String productVolume;
	private int productCount;
	private String productImg;
	private String productImgOriName;
	private String productImgUrl;
	private int productCom;
}
