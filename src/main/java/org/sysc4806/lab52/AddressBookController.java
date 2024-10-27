package org.sysc4806.lab52;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public Iterable<AddressBook> getAllAddressBooks() {
        return addressBookService.findAll();
    }

    @PostMapping
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookService.save(addressBook);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressBook(@PathVariable Long id) {
        addressBookService.deleteById(id);
    }

    // Additional endpoints for adding/removing buddies can be added here
}
