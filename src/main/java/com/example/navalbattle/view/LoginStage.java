package com.example.navalbattle.view;

import com.example.navalbattle.controller.LoginController;
import com.example.navalbattle.controller.WelcomeController;
import com.example.navalbattle.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginStage extends Stage {
    private LoginController loginController;
    private Parent root;

    public LoginStage() {
        super();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/navalbattle/login-view.fxml"));
        try{
            root = loader.load();
            loginController = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Batalla naval");
        setResizable(false);
        // initStyle(StageStyle.UNDECORATED);
        show();
    }

    public LoginController getLoginController() {
        return loginController;
    }

    private static class LoginStageHolder {
        private static LoginStage INSTANCE;
    }

    public static LoginStage getInstance() {
        LoginStageHolder.INSTANCE = (LoginStageHolder.INSTANCE != null ? LoginStageHolder.INSTANCE : new LoginStage());
        return LoginStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        LoginStageHolder.INSTANCE.close();
        LoginStageHolder.INSTANCE = null;
    }
}