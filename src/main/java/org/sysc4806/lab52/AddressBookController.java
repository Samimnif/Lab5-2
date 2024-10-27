package org.sysc4806.lab52;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public List<AddressBook> getAllAddressBooks() {
        System.out.println("Find All: "+addressBookService.findAll());
        return addressBookService.findAll();
    }

    @PostMapping
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        System.out.println("Adding: "+addressBook);
        AddressBook savedAddressBook = addressBookService.save(addressBook);
        System.out.println(savedAddressBook);
        System.out.println(addressBook.getBuddies());
        return savedAddressBook;
    }

    @DeleteMapping("/{id}")
    public void deleteAddressBook(@PathVariable Long id) {
        addressBookService.deleteById(id);
    }

    // Additional endpoints for adding/removing buddies can be added here
}
