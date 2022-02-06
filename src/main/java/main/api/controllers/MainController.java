package main.api.controllers;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value = "Endpoints for Creating, Retrieving, Updating and Deleting of Contacts.", tags = {"Main"})
@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ApiOperation(value = "Just test hello", notes = "Nothing to note", tags = { "bodyhello" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response= List.class )  })
    @GetMapping("/hello")
    @ResponseBody
    public String bodyHello() {
        return new String("bodyhello");
    }
}
