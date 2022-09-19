package com.example.mycontacts.fakeClasses

class FakeContactRepository {

    private val contactList = mutableListOf<FakeContact>()

    fun addContact(contact: FakeContact) {
        contactList.add(contact)
    }

    fun getAllContacts(): MutableList<FakeContact> = contactList

    fun deleteContact(contact: FakeContact) {
        contactList.remove(contact)
    }
}