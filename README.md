Tools / IDE : Eclipse Oxygen Release (4.7.0)
Server : Tomcat 7 2.2
Database : MySQL (nama database : store)
Keterangan : File DB terdapat di folder resource

Langkah - langkah run / compile project.
1. Buka project dengan eclipse
2. Setting Run Configuration
3. 1. New Connection
   2. Pilih workspace (project) 
   3. Isi Nama : Maven War Build
   4. Isi Goals dengan : clean install
4. 1. New Connection
   2. Pilih workspace (project)
   3. Isi Nama : Tomcat Run
   4. Isi Goals dengan : tomcat7:run

Test Hasil :
Menggunakan Postman.

Langkah - langkah :
1. Method yang digunakan GET
2. Contoh api http://localhost:8080/books?search=How to &pageNumber=0&pageSize=10&sort=id&filter=active
3. Klik Send
4. Contoh screenshot Hasil (Terdapat di folder resource dengan nama hasil-postman.jpg)
