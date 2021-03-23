# microservices-capstone-project

This is a capstone project made as part of the 'Distributed Systems and Microservices Architecture' course, based on SivaLabs Microservices tutorials focused on Spring Cloud.

The project consists of the following applications:

* *catalog-application* - that holds in-memory storage of a product dataset (https://www.kaggle.com/PromptCloudHQ/all-jc-penny-products), 
* *inventory-application* - that stores data about each product availability, 
* *product-application* - that returns product data to end-clients, making calls to catalog and inventory applications.

Dependencies used:
* *Netflix Eureka*
* *Netflix Hystrix*
* *Spring Cloud Sleuth and Zipkin*


