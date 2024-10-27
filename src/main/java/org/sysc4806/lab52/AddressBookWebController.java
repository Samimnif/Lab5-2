package org.sysc4806.lab52;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addressbook")
public class AddressBookWebController {
    @Autowired
    private AddressBookService addressBookService;

    @GetMapping("/")
    public void listAddress(){

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
}
