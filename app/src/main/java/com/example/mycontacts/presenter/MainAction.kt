package com.example.mycontacts.presenter

import com.example.mycontacts.data.model.Contact

interface MainAction {

    fun onAddContact(contacts: List<Contact>)
}