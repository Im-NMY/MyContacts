package com.example.mycontacts

import com.example.mycontacts.fakeClasses.FakeContact
import com.example.mycontacts.fakeClasses.FakeContactRepository
import org.junit.Test

/**
 * Пример локального модульного теста, который будет выполняться на машине разработки (хосте).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    /**
     * Обявление ложных объектов для тестов
     */
    val contact = FakeContact(name = "FakeName", surname = "FakeSurname", number = "+71111111111")
    val repository = FakeContactRepository()

    /**
     * Тест на добовление контакта
     */
    @Test
    fun testAddContact() {

        val name = "testFake"
        repository.addContact(contact)
        val list = repository.getAllContacts()
        val lastContact = list.last()

        kotlin.test.assertEquals(contact, lastContact)
        kotlin.test.assertNotEquals(name, lastContact.name)
    }
    /**
     * Тест на удаление контакта
     */
    @Test
    fun testDeleteContact() {
        val contactOne =
            FakeContact(name = "FakeName", surname = "FakeSurname", number = "+71111111111")
        val contactTwo =
            FakeContact(name = "testName", surname = "testSurname", number = "+72222222222")
        repository.addContact(contactOne)
        repository.addContact(contactTwo)
        repository.deleteContact(contactOne)
        val list = repository.getAllContacts()

        kotlin.test.assertEquals(list.size, 1)
        kotlin.test.assertNotEquals(list.size, 2)
    }
    /**
     * Тест на на получение всех контактов
     */
    @Test
    fun testGetAllContacts() {
        val contactOne =
            FakeContact(name = "FakeName", surname = "FakeSurname", number = "+71111111111")
        val contactTwo =
            FakeContact(name = "testName", surname = "testSurname", number = "+72222222222")
        val repositoryOne = repository
        repositoryOne.addContact(contactOne)
        repositoryOne.addContact(contactTwo)
        val listOne = repositoryOne.getAllContacts()

        val contactThree =
            FakeContact(name = "FakeName", surname = "FakeSurname", number = "+71111111111")
        val contactFour =
            FakeContact(name = "testName", surname = "testSurname", number = "+72222222222")
        val repositoryTwo = repository
        repositoryTwo.addContact(contactThree)
        repositoryTwo.addContact(contactFour)
        val listTwo = repositoryTwo.getAllContacts()

        kotlin.test.assertEquals(listOne.size, listTwo.size)
    }
}