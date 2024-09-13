CRM Dashboard

Bu proje, müşteri yönetimi ve kullanıcı kimlik doğrulama işlemleri için bir CRM (Müşteri İlişkileri Yönetimi) uygulamasıdır. Uygulama, kullanıcılar ve müşteriler arasında etkileşim sağlayarak yönetimi kolaylaştırır.


İçindekiler

    -Proje Özeti
    -API Dokümantasyonu
        Kullanıcı API
        Müşteri API
    -Veritabanı
    -Kullanım

Proje Özeti:
Bu proje, kullanıcı yönetimi ve müşteri yönetimi işlemlerini gerçekleştiren bir RESTful API içerir. İki ana bileşen bulunmaktadır:

    Kullanıcı Yönetimi: Kullanıcı kayıt, giriş ve güncelleme işlemlerini sağlar.
    Müşteri Yönetimi: Müşteri ekleme, güncelleme, silme, listeleme ve filtreleme işlemlerini sağlar.


Kullanıcı API
1. Kullanıcı Kayıt

   Yöntem: POST
   Yol: /api/v1/user/register
   İçerik:
            
        {
           "username": "user",
           "password": "password",
           "email": "user@example.com"
           }

   Yanıt olarak: Oluşturulan kullanıcı bilgileri
        içerik:
                
            {
                "username": "user",
                "email": "user@example,
                 "role": "USER",
                "token": null,
                "createdAt": "2024-09-13T16:08:02.646562",
                 "updatedAt": "2024-09-13T16:08:02.648576"
                }

2. Kullanıcı Giriş

   Yöntem: POST
   Yol: /api/v1/user/login
   İçerik:


           {
           "username": "user",
           "password": "password"
           }

   Yanıt: JWT token ve kullanıcı bilgileri
        içerik:
            
            {
               "username": "cem",
               "email": "cem@gmail.com",
               "role": "USER",
               "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInVzZXJJZCI6MjEsImVtYWlsIjoiY2VtQGdtYWlsLmNvbSIsInN1YiI6ImNlbSIsImlhdCI6MTcyNjIzMzAwNCwiZXhwIjoxNzI2MjM2NjA0fQ.9aYeMNhHR18xlkV_IGoLdLDhjy2QBj6gWEwF5-MTPt0",
               "createdAt": "2024-09-13T16:08:02.646562",
               "updatedAt": "2024-09-13T16:08:02.648576"
               } 

3. Kullanıcı Güncelleme

   Yöntem: PUT
   Yol: /api/v1/user/{id}
   İçerik:
           
        {
           "username": "newuser",
           "password": "newpassword",
           "email": "newuser@example.com"
           }

   Yanıt: Güncellenmiş kullanıcı bilgileri

Müşteri API
1. Müşteri Ekleme

   Yöntem: POST
   Yol: /api/v1/customer
   İçerik:

            {
               "firstName": "John",
               "lastName": "Doe",
               "email": "john.doe@example.com",
               "region": "North"
               }

   Yanıt: Oluşturulan müşteri bilgileri

2. Müşteri Güncelleme

   Yöntem: PUT
   Yol: /api/v1/customer/{id}
   İçerik:

 
    {
       "firstName": "John",
       "lastName": "Smith",
       "email": "john.smith@example.com",
       "region": "South"
       }

   Yanıt: Güncellenmiş müşteri bilgileri

3. Müşteri Silme

   Yöntem: DELETE
   Yol: /api/v1/customer/{id}
   Yanıt: 204 No Content

4. Müşteri Listeleme

   Yöntem: GET
   Yol: /api/v1/customer
   Yanıt: Admin kullanıcısı için tüm Müşterilerin listesi User kullanıcısı için kendi ekledikleri

   Müşteri Filtreleme

      Yöntem: GET
   Yol: /api/v1/customer/filter
   Parametreler:

                firstName (isteğe bağlı)
                 lastName (isteğe bağlı)
                 email (isteğe bağlı)
                 startDate (isteğe bağlı)
                 endDate (isteğe bağlı)
                 region (isteğe bağlı)
                 Yanıt: Filtrelenmiş müşteri listesi


Veri Tabanı:
    
Veri tabanı Bağlantısı için application.properties kısmındaki db bilgilerini kendi db username ve passwordu yapın
    
    spring.datasource.username=admin
    spring.datasource.password=test1234

Kullanım

    Kayıt Olun: POST /api/v1/user/register endpoint'ini kullanarak yeni bir kullanıcı oluşturun.
    Giriş Yapın: POST /api/v1/user/login endpoint'ini kullanarak giriş yapın ve JWT token alın.
    Müşteri Yönetimi: JWT token ile Authorization başlığı ekleyerek müşteri ekleme, güncelleme, silme ve listeleme işlemlerini yapabilirsiniz.