module shinshinjiru.main {
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.controls;
    requires jfxtras.controls;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.web;

    requires com.fasterxml.classmate;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.module.paramnames;

    requires jdk.httpserver;
    requires org.slf4j;
    requires lombok;

    opens com.manulaiko.shinshinjiru;
    opens com.manulaiko.shinshinjiru.presenter;
    opens com.manulaiko.shinshinjiru.view;
    opens com.manulaiko.shinshinjiru.view.event;
    opens com.manulaiko.shinshinjiru.view.handler;
    opens com.manulaiko.shinshinjiru.oauth;
    opens com.manulaiko.shinshinjiru.oauth.event;
    opens com.manulaiko.shinshinjiru.oauth.handler;
    opens com.manulaiko.shinshinjiru.api;
}