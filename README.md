# MPExS

* Os : linux
* Install intellij
* Install jdk 11 or above
* Install javafx-linux-sdk for intelliJ from https://gluonhq.com/products/javafx/?fbclid=IwAR1Jm-Or2NJcTbDwniJE80hhLE63SFUfg5qR4qmLzQHWHb5h_g5DAryugXM
* Copy jar files from /javafx-sdk-*/lib/ and add to global modules in intelliJ
* Download jess from https://www.jessrules.com/jess/download.shtml?fbclid=IwAR34rjg4ObP4YHdCCwqdvdU4gNfmYOO3qDK5op-8-IbZIFk_9sIiSIRWF9E
* From /Jess71p2/lib add jess.jar to global modules in intelliJ
* From https://mvnrepository.com/artifact/org.json/json/20190722?fbclid=IwAR2ihczx6aHQEzdqKKcJfImmKpsXlJN4emz6XEN82a6g8uunQj2YjTffLB0 , Click on bundle and download the json.jar file
* Ctrl + shift + alt + s  → add to maven → add  com.squareup.okhttp3.sample:crawler:3.3.1  
* Repeat above for com.netflix.rxjava:rxjava-apache-http:0.20.7
* Repeat for com.google.code.gson:gson:2.8.6
* Clone repo https://github.com/diss-key/MPExS.git and add files to directory structure


* File -> new ->module -> add gradle ->set artifact ID
* Add maven {
             url 'https://dl.bintray.com/aafanasev/maven'
      } to the repositories sub block
* Add compile 'com.aafanasev:fonoapi-retrofit:1.0' to the dependencies
* Add all modules to main project eg. JESSFX.
* Remove com.google.gson form dependencies 
