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

    @Autowired
    private BuddyInfoService buddyInfoService;

    @GetMapping
    public List<AddressBook> getAllAddressBooks() {
        System.out.println("Find All: "+addressBookService.findAll());
        return addressBookService.findAll();
    }

    @PostMapping
    public RedirectView createAddressBook(@RequestParam(value = "name") String name) { //@RequestBody AddressBook addressBook
        addressBookService.save(new AddressBook(name));
        return new RedirectView("/addressbook/");
    }

    @PostMapping("/rest")
    public AddressBook createAddressBookRest(@RequestBody AddressBook addressBook) { //@RequestBody AddressBook addressBook
        return addressBookService.save(addressBook);
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

    @GetMapping("/{id}/getbuddies")
    public List<BuddyInfo> getBuddiesInAddressBookRest(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AddressBook not found with id " + id));
        return addressBook.getBuddies();
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

    @PostMapping("/{id}/removeBuddy/{bid}")
    public RedirectView removeBuddy(@PathVariable Long id, @PathVariable Long bid){
        AddressBook addressBook = addressBookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("AddressBook not found with id " + id));
        List<BuddyInfo> buddies = addressBook.getBuddies();
        BuddyInfo removeB = buddyInfoService.findById(bid).orElseThrow(() -> new ResourceNotFoundException("AddressBook not found with id " + bid));
        addressBook.removeBuddy(removeB);
        addressBookService.save(addressBook);
        return new RedirectView("/addressbook/" + id + "/buddies");
    }
}
