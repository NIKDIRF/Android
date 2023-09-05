package com.app.contactlist.model

import android.content.Context
import android.provider.ContactsContract

data class Contact(val name: String, val phoneNumber: String, val photo : String)

fun Context.fetchAllContacts(): List<Contact> {
    contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        .use { cursor ->
            if (cursor == null) return emptyList()
            val builder = ArrayList<Contact>()
            while (cursor.moveToNext()) {
                var name = ""
                var phoneNumber = ""
                var photo = ""
                val c1 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                if (c1 >= 0) {
                    name =
                        cursor.getString(c1)
                            ?: "N/A"
                }
                val c2 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                if (c2 >= 0) {
                    phoneNumber =
                        cursor.getString(c2)
                            ?: "N/A"
                }
                val c3 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)
                if (c3 >= 0) {
                    photo =
                        cursor.getString(c3)
                            ?: "N/A"
                }
                builder.add(Contact(name, phoneNumber, photo))
            }
            return builder
        }
}