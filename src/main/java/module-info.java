module shinshinjiru.main {
    requires javafx.fxml;
    requires javafx.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires jdk.httpserver;
    requires spring.web;
    requires com.fasterxml.classmate;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.module.paramnames;
    requires org.slf4j;

    opens com.manulaiko.shinshinjiru;
    opens com.manulaiko.shinshinjiru.presenter;
    opens com.manulaiko.shinshinjiru.view.event;
    opens com.manulaiko.shinshinjiru.view.handler;
    opens com.manulaiko.shinshinjiru.oauth;
    opens com.manulaiko.shinshinjiru.api;
}