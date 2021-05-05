# AlbaneroAssignment

#For this assignment i have used spring boot , jpa , mysql.
1). I have created a table BookDetails using jpa.
2). After that i have push the data on BookDetails using saveBook method.
3). To access the saveBook method you have to hit the url(method = post , url = http://localhost:8081/savebook)
Payload - 
  {
    "bookName":"Core Java",
    "bookDescription":"Basic concepts of java"
  }
 Response -
 {
    "timestamp": "2021-05-05T17:21:21.685+00:00",
    "status": "200",
    "message": "DATA_SAVED_SUCCESSFULLY",
    "data": {
        "bookId": 1,
        "bookName": "Core Java",
        "bookDescription": "Basic concepts of java"
    }
}

4). After that to get the meta data you have to hit the url (method = get , url = http://localhost:8081/books)
Response - 
{
    "Column Names": [
        "book_id",
        "book_description",
        "book_name"
    ],
    "Column Type": [
        "BIGINT",
        "VARCHAR",
        "VARCHAR"
    ],
    "Tables": [
        "book_details"
    ]
}
5). After that to store the meta data you have to hit the url (method = post , url = http://localhost:8081/storebooks)
Response - 
{
    "Column Names": [
        "book_id",
        "book_description",
        "book_name"
    ],
    "Column Type": [
        "BIGINT",
        "VARCHAR",
        "VARCHAR"
    ],
    "Tables": [
        "book_details"
    ]
}


 
