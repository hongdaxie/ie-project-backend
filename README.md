# Ieproject-backend
## To run this application, build a MySQL server and run the sql(5.7.27) firstly

### change the IP address [here](https://github.com/hongdaxie/Ieproject-backend/blob/master/src/main/resources/application.properties)

### create a Twilio account and put your SID and Token [here](https://github.com/hongdaxie/Ieproject-backend/blob/master/src/main/resources/application.yml)
This can allow user to send SMS notifications

### create a GOOGLE_API_KEY and GOOGLE_SEARCH_ID_cx and put in [here](https://github.com/hongdaxie/Ieproject-backend/edit/master/src/main/java/com/hongda/googleSearch/CustomerSearch.java)
This is to fetch images

### create a SendGrid API_KEYS and put [here](https://github.com/hongdaxie/Ieproject-backend/blob/master/src/main/java/com/hongda/service/MailService.java) line 36 
This is to send notifications by email
