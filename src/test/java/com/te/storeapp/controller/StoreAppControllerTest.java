package com.te.storeapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.storeapp.bean.StoreApp;
import com.te.storeapp.service.StoreAppService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StoreAppControllerTest {

	@MockBean
	private StoreAppService service;

	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getStoreByIdTest() throws UnsupportedEncodingException, Exception {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse("12-12-1999");
		StoreApp store = new StoreApp();
		store.setStoreId("12");
		store.setPostCode("563322");
		store.setCity("bangalore");
		store.setAddress("krt");
		store.setOpenedDate(date);

		when(service.getStoreById(Mockito.anyString())).thenReturn(store);

		String contentAsString = mockmvc
				.perform(get("/getStoreById/12").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(store)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		StoreApp readValue = mapper.readValue(contentAsString,StoreApp.class);
		assertEquals(store.getStoreId(),readValue.getStoreId());
	}

	@Test
	void getStoresByCityTest() throws UnsupportedEncodingException, Exception {

		List<StoreApp> list = new ArrayList<>();
		StoreApp store = new StoreApp();
		store.setStoreId("12");
		store.setPostCode("563322");
		store.setCity("bangalore");
		store.setAddress("krt");

		list.add(store);

		when(service.getStoresByField(Mockito.anyString())).thenReturn(list);

		String contentAsString = mockmvc
				.perform(get("/getStoresByField/city").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(store)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(mapper.writeValueAsString(list), contentAsString);
	}

}
