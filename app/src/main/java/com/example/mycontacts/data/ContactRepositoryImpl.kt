package com.example.mycontacts.data

import com.example.mycontacts.data.model.Contact
import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import java.util.*

class ContactRepositoryImpl(
    private val realm: Realm
) : ContactRepository {
    override fun addContact(name: String, surname: String, number: String) {
        realm.executeTransaction {
            it.createObject(Contact::class.java, UUID.randomUUID().toString()).apply {
                this.name = name
                this.surname = surname
                this.number = number
            }
        }
    }

    override fun getContact(): List<Contact> {
        return realm.where(Contact::class.java).findAll()
    }

    override fun update(name: String, surname: String, number: String, contact: Contact) {
        realm.executeTransaction {
            val dataObjectTransaction = realm.where(Contact::class.java).equalTo("id", contact.id ).findFirst()
            dataObjectTransaction?.name = name
            dataObjectTransaction?.surname = surname
            dataObjectTransaction?.number = number
        }

    }

    override fun delete(contact: Contact) {
        realm.executeTransaction {
            realm.where(Contact::class.java).equalTo("id", contact.id ).findFirst()
                ?.deleteFromRealm()
        }
    }
}