package com.example.mycontacts.data

import com.example.mycontacts.data.model.Contact

interface ContactRepository {

    fun addContact(name: String, surname: String, number: String)

    fun getContact(): List<Contact>

    fun update(name: String, surname: String, number: String, contact: Contact)

    fun delete(contact: Contact)
}