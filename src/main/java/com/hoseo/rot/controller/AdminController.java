package com.hoseo.rot.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoseo.rot.admin.AdminService;
import com.hoseo.rot.admin.Product;



@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/productEnroll")
	public String productEnroll() {
		return "admin/productEnroll";
	}
	
	/*
	 * @PostMapping("/productEnroll") public String enroll(Product p) {
	 * if(adminService.addProduct(p)) { return "redirect:/productEnroll"; } return
	 * "admin/productEnroll"; }
	 */
	
	@PostMapping("/addProduct")
	public String addProduct(Product p, HttpServletRequest request, MultipartHttpServletRequest multi) {
		Iterator<String> imgs = multi.getFileNames();
		String path = "D:\\upload";
		String folderName1 = "\\product\\";      
        
		while (imgs.hasNext()) {
			String uploadFile = imgs.next();			
			MultipartFile mFile = multi.getFile(uploadFile);
			String sourceFileName = mFile.getOriginalFilename();
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
			File destinationFile; 
	        String destinationFileName;        
	        	        
	        
			do { 
	            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;            	
	            	destinationFile = new File(path + folderName1 +destinationFileName);
	            	p.setProductImg(destinationFileName);
	            	p.setProductImgOriName(sourceFileName);
	            	p.setProductImgUrl(path+folderName1);	
	            
	        } while (destinationFile.exists()); 
	        
	        destinationFile.getParentFile().mkdirs(); 
	        try {
	        	mFile.transferTo(destinationFile);	        		            	           
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}        
		if(adminService.addProduct(p)) {			
			return "redirect:/blank";
		}
		return "admin/productEnroll"; 
	}

	
	@GetMapping("/blank")
	public String getProduct(ModelMap m) {
		m.put("productList", adminService.getProduct());
		return "admin/blank";
	}


	
	
	@GetMapping("/management")
	public String management() {
		return "admin/management";
	}
	
	@GetMapping("/charts")
	public String charts() {
		return "admin/charts";
	}
	
	@GetMapping("/tables")
	public String tables() {
		return "admin/tables";
	}

}
