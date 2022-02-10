package  com.example.returnkey.returnmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController extends BaseController{

    @PostMapping("/pending/returns")
    public ResponseEntity<String> pendingReturnCreation(){
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/returns")
    public ResponseEntity<String> returnCreation(){
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/returns/{id}")
    public ResponseEntity<String> getReturn(@PathVariable String id){
        return ResponseEntity.ok().body("ok" + id);
    }

    @PutMapping("/returns/{id}/items/{itemId}/qc/status")
    public ResponseEntity<String> setQualityControlStatus(@PathVariable String id, @PathVariable String itemId){
        return ResponseEntity.ok().body("ok");
    }
}