package com.te.storeapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.te.storeapp.bean.StoreApp;
import com.te.storeapp.service.StoreAppService;

@RestController
public class StoreAppController {
	
	@Autowired
	private StoreAppService service;

	@GetMapping("/getStoreById/{storeId}")
	public ResponseEntity getStoreById(@PathVariable String storeId) {
		StoreApp storeById = service.getStoreById(storeId);
		if(storeById!=null)
			return new ResponseEntity(storeById,HttpStatus.OK);
		else
			return new ResponseEntity("Data not found",HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/getStoresByField/{field}")
	public ResponseEntity getStoresByField(@PathVariable String field) {
		List<StoreApp> storesByCity = service.getStoresByField(field);
		if(!storesByCity.isEmpty()) {
			return new ResponseEntity(storesByCity,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("No data is present",HttpStatus.NOT_FOUND);
		}
	}

}
