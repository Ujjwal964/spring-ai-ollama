package com.springai.ollama.spring_ai_ollama.controller;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private ChatClient chatClient;          //using chatCleint , we can say main part of SPRING AI.

    public ChatController(ChatClient.Builder builder){          //since we can't autowire chatclient we use constructor and do it using chatclient.builder.
        this.chatClient = builder.build();                      //and build() using builder and assign it to chatClient.
    }
    

    @GetMapping("/chat")
    public ResponseEntity<String> chat( @RequestParam(value="q" , required=true) String q){

        String aiResponse = this.chatClient.prompt(q).call().content(); 
        //passing user query param to ai using prompt().call() - will call ai and pass prompt and content() will get the ai content means response and store it in a string var.

        return new ResponseEntity<String>(aiResponse,HttpStatus.OK);
        //OR
        // return ResponseEntity.ok(aiResponse);
    }
}
