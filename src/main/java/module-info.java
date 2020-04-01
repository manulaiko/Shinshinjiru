module atarashiinjiru.main {
    requires javafx.fxml;
    requires javafx.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    opens com.manulaiko.shinshinjiru;
    opens com.manulaiko.shinshinjiru.presenter;
    opens com.manulaiko.shinshinjiru.view.event;
    opens com.manulaiko.shinshinjiru.view.handler;
}