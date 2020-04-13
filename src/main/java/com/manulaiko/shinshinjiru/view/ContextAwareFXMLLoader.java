package com.manulaiko.shinshinjiru.view;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import javafx.fxml.FXMLLoader;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Context aware FXMLLoader.
 * =========================
 *
 * Allows to load FXML models with Spring context available.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ContextAwareFXMLLoader extends FXMLLoader {
    /**
     * Constructor.
     *
     * @param file Path to the file.
     *
     * @throws IOException
     */
    public ContextAwareFXMLLoader(String file) throws IOException {
        super(new ClassPathResource(file).getURL());

        this.setControllerFactory(ShinshinjiruApplication.getApplicationContext()::getBean);
    }
}
