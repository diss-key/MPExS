module MPExS {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires jess;
    requires java.net.http;
    requires org.json;
    requires fonoapi;
    requires fonoapi.retrofit;
    requires retrofit;
    requires kotlin.stdlib;
    requires junit;
    requires okhttp;
    requires okio;
    requires converter.gson;
    requires com.google.gson;
    requires httpclient;


    requires rxjava.apache.http;
    requires rxjava.core;


    opens sample;
}