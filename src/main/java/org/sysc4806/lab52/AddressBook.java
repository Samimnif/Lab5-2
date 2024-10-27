package org.sysc4806.lab52;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddies;

    private int totalSize = 0;

    private String addressName;


    public AddressBook(String name) {
        buddies = new ArrayList<>();
        addressName = name;
    }
    public AddressBook(){

    }


    public void addBuddy(BuddyInfo newBuddy) {
        buddies.add(newBuddy);
        totalSize++;
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddies.remove(buddy);
        totalSize--;
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public String getAddressName() {
        return addressName;
    }
}
