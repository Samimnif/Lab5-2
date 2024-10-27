package org.sysc4806.lab52;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressBookController.class)
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService addressBookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAddressBook() throws Exception {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(1L);

        when(addressBookService.save(any(AddressBook.class))).thenReturn(addressBook);

        mockMvc.perform(post("/api/addressbook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testGetAllAddressBooks() throws Exception {
        mockMvc.perform(get("/api/addressbook")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}

