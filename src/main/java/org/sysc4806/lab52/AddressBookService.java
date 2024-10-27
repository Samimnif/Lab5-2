package org.sysc4806.lab52;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public AddressBook save(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    public List<AddressBook> findAll() {
        return (List<AddressBook>) addressBookRepository.findAll();
    }

    public void deleteById(Long id) {
        addressBookRepository.deleteById(id);
    }

    public Optional<AddressBook> findById(Long id){
        return addressBookRepository.findById(id);
    }

    public List<AddressBook> getAllAddress(){
        return (List<AddressBook>) addressBookRepository.findAll();
    }

    // Additional methods like findById or addBuddy can be added as needed
}
