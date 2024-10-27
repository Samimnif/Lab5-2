package org.sysc4806.lab52;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressBookIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAddressBook() {
        AddressBook addressBook = new AddressBook(); // Assume AddressBook has a default constructor
        ResponseEntity<AddressBook> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/addressbook", addressBook, AddressBook.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testGetAllAddressBooks() {
        ResponseEntity<AddressBook[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/addressbook", AddressBook[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }
}
