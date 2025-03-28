package org.jabref.gui.fieldeditors;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class LinkedFilesEditorKeyBindingIntegrationTest {

    private int renameCounter = 0;

    @Start
    void onStart(Stage stage) {
        StackPane root = new StackPane();
        root.setId("testPane");
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R && event.isControlDown()) {
                renameCounter++;
            }
        });

        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();

        root.requestFocus();
    }

    @Test
    void pressingCtrlRWhenFileIsSelectedTriggersRename(FxRobot robot) {
        robot.clickOn("#testPane");
        robot.press(KeyCode.CONTROL).press(KeyCode.R).release(KeyCode.R).release(KeyCode.CONTROL);
        assertEquals(1, renameCounter);
    }
}
