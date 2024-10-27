package org.sysc4806.lab52;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.RedirectView;

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
    public AddressBook createAddressBook() { //@RequestBody AddressBook addressBook
//        System.out.println("Adding: "+addressBook);
//        AddressBook savedAddressBook = addressBookService.save(addressBook);
//        System.out.println(savedAddressBook);
//        System.out.println(addressBook.getBuddies());
        return addressBookService.save(new AddressBook());
    }

    @DeleteMapping("/{id}")
    public void deleteAddressBook(@PathVariable Long id) {
        addressBookService.deleteById(id);
    }

    @GetMapping("/{id}/buddies")
    public String getBuddiesInAddressBook(@PathVariable Long id, Model model) {
        AddressBook addressBook = addressBookService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AddressBook not found with id " + id));
        model.addAttribute("buddies", addressBook.getBuddies());
        System.out.println("Buddies: " + addressBook.getBuddies());
        model.addAttribute("addressBookId", id);
        return "buddylist";
    }

    @PostMapping("/{id}/addBuddy")
    public RedirectView  addBuddy(@PathVariable Long id, @RequestParam(value = "name") String name, @RequestParam(value = "address") String address,
                                 @RequestParam(value = "phoneNumber") String phoneNumber){
        BuddyInfo newBuddy = new BuddyInfo(name, address, phoneNumber);
        System.out.println(newBuddy.toSting());
        AddressBook addressBook = addressBookService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AddressBook not found with id " + id));
        addressBook.addBuddy(newBuddy);
        addressBookService.save(addressBook);
        return new RedirectView("/addressbook/" + id + "/buddies");
    }
}
