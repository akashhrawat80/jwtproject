package com.jwtproject.service;

import com.jwtproject.model.Design;
import com.jwtproject.repository.DesignRepository;

public class DesignService {
	
	private DesignRepository designRepo;
	public void saveDesign(Design design) {
		this.designRepo.save(design);
	}
}
