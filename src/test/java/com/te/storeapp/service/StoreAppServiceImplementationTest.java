package com.te.storeapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.opencsv.CSVReader;
import com.te.storeapp.bean.StoreApp;
import com.te.storeapp.service.StoreAppServiceImplementation;

@ExtendWith(MockitoExtension.class)
class StoreAppServiceImplementationTest {
	
	CSVReader reader;
	
	@InjectMocks
	private StoreAppServiceImplementation service;

	public StoreAppServiceImplementationTest() {
		reader = mock(CSVReader.class);
	}

	@Test
	void testGetStoreById() throws IOException {
		StoreApp store = new StoreApp();
		store.setAddress("krt");
		store.setCity("bangalore");
		store.setPostCode("563322");
		store.setStoreId("1525eec4-7bed-4597-bf5a-e06fcede5f4f");

		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", service.getStoreById(store.getStoreId()).getStoreId());
	}

	@Test
	void testGetStoresByCity() throws IOException {

		StoreApp store = new StoreApp();
		store.setAddress("krt");
		store.setCity("bangalore");
		store.setPostCode("563322");
		store.setStoreId("1525eec4-7bed-4597-bf5a-e06fcede5f4f");

		List<StoreApp> list = new ArrayList<>();
		list.add(store);

		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", service.getStoresByField("city").get(0).getStoreId());

	}

}