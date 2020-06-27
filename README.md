# mintcards
This is a REST API that does card verification by supplying the bin/inn.
It also sends card scheme information via Kafka messaging bus to a listener.



Usage:
To build: ./mvnw package && java -jar target/mintfintechcards-1.jar

Note: Use -v for verbose output and -d if you don't see the Version Number, search string may be different.
