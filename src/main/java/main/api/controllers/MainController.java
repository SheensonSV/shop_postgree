package main.api.controllers;

import io.swagger.annotations.*;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Api(value = "Endpoints for Creating, Retrieving, Updating and Deleting of Contacts.", tags = {"Main"})
@Controller
public class MainController {

    @ApiOperation(value = "Shows today's date", notes = "Nothing to note", tags = { "date" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response= List.class )  })

    @GetMapping("/")
    @ResponseBody
    public String toDay() {
        return "Today is " + new Date().toString();
    }
}
