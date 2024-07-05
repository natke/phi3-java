package org.example;

import java.util.logging.Logger;

import ai.onnxruntime.genai.*;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    private void runSimple() {

        try {
            SimpleGenAI generator = new SimpleGenAI("C:\\Users\\nakersha\\Develop\\models\\microsoft\\phi3-mini-4k\\cpu-int4-rtn-block-32-acc-level-4");
            GeneratorParams params = generator.createGeneratorParams("<|user|>\\nHello!<|end|>\\n<|assistant|>");

            String result = generator.generate(params, null);
            logger.info("Result: " + result);
        } catch (GenAIException e) {
            e.printStackTrace();    
        }
    }

    private void runLoop() {
       Model model;
       Tokenizer tokenizer;
       TokenizerStream stream;

        try {
           model = new Model("C:\\Users\\nakersha\\Develop\\models\\microsoft\\phi3-mini-4k\\cpu-int4-rtn-block-32-acc-level-4");
           tokenizer = model.createTokenizer();
           stream = tokenizer.createStream();

           GeneratorParams params = model.createGeneratorParams();
           params.setSearchOption("max_length", 200);

           Sequences input_ids = tokenizer.encode("<|user|>\\nHello!<|end|>\\n<|assistant|>");
           params.setInput(input_ids);

           Generator generator = new Generator(model, params);

           logger.info("Running loop: ");

           while (!generator.isDone()) {
               generator.computeLogits();
               generator.generateNextToken();

               int token = generator.getLastTokenInSequence(0);

               System.out.print(stream.decode(token));

            }

           generator.close();
        
        } catch (GenAIException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws GenAIException{
        App app = new App();
        app.runSimple();
        app.runLoop();
 
    }
}
