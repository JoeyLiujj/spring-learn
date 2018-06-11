package cn.joey.mvc.validator;

import cn.joey.mvc.validator.ItemsCustom;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @RequestMapping("/requestJson")
    public @ResponseBody
    ItemsCustom requestJson(@RequestBody  ItemsCustom itemsCustom){
        return itemsCustom;
    }
}
