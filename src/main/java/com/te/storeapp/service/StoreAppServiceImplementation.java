package com.te.storeapp.service;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.te.storeapp.Exception.CustomException;
import com.te.storeapp.bean.StoreApp;

@Service
public class StoreAppServiceImplementation implements StoreAppService {

	
	public StoreApp getStoreById(String storeId) {
		int count = 0;
		StoreApp store = new StoreApp();
		List<String> list = new ArrayList<>();
		try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/task.csv"));) {
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				list = Arrays.asList(values);
				if (list.get(0).equals(storeId)) {
//					Date date = new SimpleDateFormat("dd-MM-yyyy").parse(list.get(4));
					store.setStoreId(list.get(0));
					store.setPostCode(list.get(1));
					store.setCity(list.get(2));
					store.setAddress(list.get(3));
					store.setOpenedDate(new SimpleDateFormat("dd-MM-yyyy").parse(list.get(4)));
					count++;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0)
			return store;
		else
			throw new CustomException("Please given currect Stored Id!!");

	}

	@Override
	public List<StoreApp> getStoresByField(String field) {
		List<StoreApp> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/task.csv"));) {
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				list1 = Arrays.asList(values);
				StoreApp store = new StoreApp();
//				Date date = new SimpleDateFormat("dd-MM-yyyy").parse(list1.get(4));
				store.setStoreId(list1.get(0));
				store.setPostCode(list1.get(1));
				store.setCity(list1.get(2));
				store.setAddress(list1.get(3));
				store.setOpenedDate(new SimpleDateFormat("dd-MM-yyyy").parse(list1.get(4)));
				list.add(store);
			}
			Comparator<StoreApp> sort = null;
			if (field.equalsIgnoreCase("city")) {
				sort = (a, b) -> {
					return a.getCity().compareTo(b.getCity());
				};
			} else if (field.equalsIgnoreCase("date")) {
				sort = (a, b) -> {
					return a.getOpenedDate().compareTo(b.getOpenedDate());
				};
			}
			Collections.sort(list, sort);
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new CustomException("give currect field!!!");

	}

}
