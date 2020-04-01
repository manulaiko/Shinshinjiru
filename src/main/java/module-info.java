module atarashiinjiru.main {
    requires javafx.fxml;
    requires javafx.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    opens com.manulaiko.atarashiinjiru;
    opens com.manulaiko.atarashiinjiru.presenter;
    opens com.manulaiko.atarashiinjiru.view.event;
    opens com.manulaiko.atarashiinjiru.view.handler;
}