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
        //addressBook.setId(1L);
        addressBook.addBuddy(new BuddyInfo("John Doe", "123 Elm St", "555-1234"));

        ResponseEntity<AddressBook> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/addressbook", addressBook, AddressBook.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        System.out.println(response);
        System.out.println("Created AddressBook: " + response.getBody());
    }

    @Test
    public void testGetAllAddressBooks() {
        ResponseEntity<AddressBook[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/addressbook", AddressBook[].class
        );
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(response.getBody()).isNotEmpty();

    }
}
