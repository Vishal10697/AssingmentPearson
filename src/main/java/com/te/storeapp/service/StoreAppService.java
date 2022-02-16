package com.te.storeapp.service;

import java.util.List;

import com.te.storeapp.bean.StoreApp;

public interface StoreAppService {

	public StoreApp getStoreById(String id);
	
	public List<StoreApp> getStoresByField(String field);
	
}
