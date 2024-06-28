package org.example;

import java.io.File;
import java.util.logging.Logger;

import ai.onnxruntime.genai.GenAIException;
import ai.onnxruntime.genai.GeneratorParams;
import ai.onnxruntime.genai.SimpleGenAI;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    private static final String phi2ModelPath() {
        File f = new File("C:\\Users\\nakersha\\Develop\\models\\microsoft\\phi3-mini-4k\\cpu-int4-rtn-block-32-acc-level-4");
    
        if (!f.exists()) {
          logger.warning("phi2 model not found at: " + f.getPath());
          logger.warning(
              "Please install as per https://github.com/microsoft/onnxruntime-genai/tree/rel-0.2.0/examples/csharp/HelloPhi2");
          return null;
        }
    
        return f.getPath();
      }

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws GenAIException{
        System.out.println(new App().getGreeting());

        SimpleGenAI generator = new SimpleGenAI(phi2ModelPath());
        GeneratorParams params = generator.createGeneratorParams("What's 6 times 7?");

        String result = generator.generate(params, null);
        logger.info("Result: " + result);
    }
}
