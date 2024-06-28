package org.example;

import java.util.logging.Logger;

import ai.onnxruntime.genai.GenAIException;
import ai.onnxruntime.genai.GeneratorParams;
import ai.onnxruntime.genai.SimpleGenAI;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws GenAIException{
 
        SimpleGenAI generator = new SimpleGenAI("C:\\Users\\nakersha\\Develop\\models\\microsoft\\phi3-mini-4k\\cpu-int4-rtn-block-32-acc-level-4");
        GeneratorParams params = generator.createGeneratorParams("<|user|>\\n" + //
                        "Hello!<|end|>\\n" + //
                        "<|assistant|>");

        String result = generator.generate(params, null);
        logger.info("Result: " + result);
    }
}
