package se.iths.springbootlab2.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.springbootlab2.dtos.BooksDto;
import se.iths.springbootlab2.entities.Författare;
import se.iths.springbootlab2.services.Service;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@WebMvcTest(BooksController.class)
public class MvcTest {

    Författare writer;

    @MockBean
    Service service;

    @Autowired
    ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingWithUrlBöckerShoudReturnAllBooksAsJson() throws Exception {

        when(service.getAllBooks()).thenReturn(List.of(new BooksDto("7894561238521", "TestTitel3", "TestSpråk3", 0.0, Date.valueOf("2021-01-06"),writer)));
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/Böcker")
                .accept(MediaType.APPLICATION_JSON)).andReturn();


        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }


    @Test
    void callingPOSTWithNewBookShouldSaveBookToServiceAndReturnNewBookWithISBN13() throws Exception {
        //Tell mockito what to return when callingfmethods on Service
        var booksDto = new BooksDto("9999999999999", "TestTitel4", "TestSpråk4", 0.0, Date.valueOf("2020-01-01"),writer);
        when(service.createBook(eq(booksDto))).thenReturn(new BooksDto("7894561238528", "RealTitel4", "RealSpråk4", 100.0, Date.valueOf("2021-01-08"),writer));

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/Böcker")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsBytes(booksDto))
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(201);
    }


}
