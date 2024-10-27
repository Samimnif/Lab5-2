package org.sysc4806.lab52;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public AddressBook save(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    public Iterable<AddressBook> findAll() {
        return addressBookRepository.findAll();
    }

    public void deleteById(Long id) {
        addressBookRepository.deleteById(id);
    }

    // Additional methods like findById or addBuddy can be added as needed
}
