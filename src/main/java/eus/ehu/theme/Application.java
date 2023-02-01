package eus.ehu.theme;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class Application extends javafx.application.Application {
    private String darckmode;
    private Scene scene;

    private Properties prop;

    private String readUserMode()
    {
        try (InputStream input = new
                FileInputStream("config.properties")) {
            prop.load(input);
            return prop.getProperty("db.usermode");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    private void writeUserMode(String mode)
    {
        try (OutputStream output = new FileOutputStream("config.properties")) {
            prop.setProperty("db.usermode", mode);
            prop.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        File style = new File("style.css");
        darckmode = style.toURI().toURL().toExternalForm();
        Parent root = fxmlLoader.load();

        // get the controller
        AppController controller = fxmlLoader.getController();
        controller.setMain(this);

        scene = new Scene(root);
        scene.getStylesheets().add(style.toURI().toURL().toExternalForm());
        stage.setTitle("Choose your theme");
        stage.setScene(scene);
        stage.show();

        prop = new Properties();
        if(readUserMode().equals("Dark Mode"))
        {
            scene.getStylesheets().add(darckmode);
        }
        else
        {
            scene.getStylesheets().clear();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public void ApplyTheme(String text) {

        switch (text) {
            case "Dark Mode":
                System.out.println("Dark mode bitch");
                scene.getStylesheets().add(darckmode);
                // write modification
                writeUserMode("Dark Mode");

                break;
            case "White Mode":
                System.out.println("Light Mode bitch");
                scene.getStylesheets().clear();
                // write modification
                writeUserMode("White Mode");

                break;
            default:
                System.out.println("You are dumb or what");
        }
    }
}