package com.swc.booksearch2.model

import com.google.gson.annotations.SerializedName

class DetailedBook

/**
 * {
 * error": "0"
 * title": "Practical MongoDB"
 * subtitle": "Architecting, Developing, and Administering MongoDB"
 * authors": "Shakuntala Gupta Edward, Navin Sabharwal"
 * publisher": "Apress"
 * isbn10": "1484206487"
 * isbn13": "9781484206485"
 * pages": "272"
 * year": "2015"
 * rating": "3"
 * desc": "Practical Guide to MongoDB: Architecting, Developing, and Administering MongoDB begins with a short introduction to the basics of NoSQL databases and then introduces readers to MongoDB - the leading document based NoSQL database, acquainting them step-by-step with all aspects of MongoDB.Practical Gu..."
 * price": "$32.04"
 * image": "https://itbook.store/img/books/9781484206485.png"
 * url": "https://itbook.store/books/9781484206485"
 * }
 */

(@field:SerializedName("title")
 var title: String?,
@field:SerializedName("subtitle")
 var subtitle: String?,
@field:SerializedName("price")
 var price: String?,
@field:SerializedName("image")
 var image: String?,
@field:SerializedName("authors")
 var authors: String?,
@field:SerializedName("desc")
 var desc: String?)
