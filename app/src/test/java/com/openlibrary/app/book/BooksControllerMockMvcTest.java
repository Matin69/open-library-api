package com.openlibrary.app.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BooksControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testList() throws Exception {
        mockMvc.perform(
                        get("/books")
                                .queryParam("query", "hamlet")
                )
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(
                        get("/books")
                                .queryParam("query", "")
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(
                        get("/books/S04tXAPxaikC")
                )
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(
                        get("/books/kbkjbkjbjkbkbjbkkbjkknknkn")
                )
                .andExpect(status().isNotFound());
    }
}