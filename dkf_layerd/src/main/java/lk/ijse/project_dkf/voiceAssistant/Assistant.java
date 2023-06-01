package lk.ijse.project_dkf.voiceAssistant;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.recognizer.Recognizer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import lk.ijse.project_dkf.controller.MainDashBoardController;
import edu.cmu.sphinx.result.Result;

import java.io.IOException;

public class Assistant {
    private static LiveSpeechRecognizer recognizer;
    public static String assistant() {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("src/main/resources/assistantResources/assistant.dic");
        configuration.setLanguageModelPath("src/main/resources/assistantResources/assistant.lm");

        try {
            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }

        recognizer.startRecognition(true);
        System.out.println("listening");

        String result = null;
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < 5000) {
            SpeechResult r = recognizer.getResult();
            if (r != null) {
                result = r.getHypothesis();
                return result;
            }
        }
        recognizer.stopRecognition();
        return result;

    }
}
