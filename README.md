


The technologies I have used in this project

1-MVVM

2-Retrofit

3-Rxjava & RxAndroid

4-Room

5-Dagger Hilt

6-View Binding

7-Swipe refresh layout

8-Unit Test
 
more details:

Using Retrofit and Rexjava libraries, we receive information from the server, And we display them in a recyclerview with the pagination capability
For the first time, we receive information from the Internet and save it to the database, from now on, every time we open the application, we read the information from the database.
If the user refreshes the page, the information will be deleted from the database and retrieved from the Internet and stored in the database.

point: some of items are same and we have to remove them by date .. i used HashTable to detect  duplicate items,Because it has a better time order
