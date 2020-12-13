package com.sylinx.sample2;

import com.sylinx.sample2.common.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

@Controller
@RequestMapping("/streaming")
public class StreamingController {

    @Autowired
    AsyncHelper asyncHelper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseBodyEmitter streaming(@RequestParam(defaultValue = "1") long eventNumber, @RequestParam(defaultValue = "0") long intervalSec) throws IOException, IOException {
        Console.println("Start get.");

        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        asyncHelper.streaming(emitter, eventNumber, intervalSec);

        Console.println("End get.");
        return emitter;
    }

}