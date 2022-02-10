package  com.example.returnkey.returnmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.returnkey.returnmanagement.entity.Return;
import com.example.returnkey.returnmanagement.entity.ReturnItem;

@RestController
public class ReturnController extends BaseController{

    @PostMapping(path="/pending/returns", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pendingReturnCreation(@RequestParam("orderId") String orderId, @RequestParam("email") String email){
        String token = returnService.findPendingReturn(email, orderId);

        // build the response
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok().body(gson.toJson(response));
    }

    @PostMapping(path="/returns", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> returnCreation(@RequestParam("token") String token){

        Return returnValue = returnService.createReturn(token);
        if(returnValue != null){
            List<ReturnItem> returnItems = returnService.findReturnItemsByReturnId(returnValue.getId());
            returnValue.setReturnItemList(returnItems);
        }
        return ResponseEntity.ok().body(gson.toJson(returnValue));
    }

    @GetMapping(path = "/returns/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getReturn(@PathVariable Long id){
        
        Return returnValue = returnService.findReturn(id);
        if(returnValue != null){
            // find latest return item
            List<ReturnItem> returnItems = returnService.findReturnItemsByReturnId(returnValue.getId());
            returnValue.setReturnItemList(returnItems);
        }

        return ResponseEntity.ok().body(gson.toJson(returnValue));
    }

    @PutMapping(path = "/returns/{id}/items/{itemId}/qc/status", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setQualityControlStatus(@PathVariable Long id, @PathVariable Long itemId, @RequestParam("status") String status){

        String message = StringUtils.EMPTY;
        try {
            returnService.setQCStatus(id, itemId, status);
            message = String.format("item %d : %s", itemId, status);    
        } catch (Exception e) {
            message = e.getMessage();
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", message);

        return ResponseEntity.ok().body(gson.toJson(response));
    }
}