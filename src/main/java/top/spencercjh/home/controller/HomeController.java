package top.spencercjh.home.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.spencercjh.home.common.ConstValue;
import top.spencercjh.home.common.util.HttpClientUtil;
import top.spencercjh.home.common.util.ResultUtil;
import top.spencercjh.home.entity.vo.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author spencercjh
 */
@Controller
public class HomeController {
    private final Environment environment;

    @Autowired
    public HomeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @PostMapping(value = "/contact")
    @ResponseBody
    public Result test(String name, String message) {
        if (StringUtils.isBlank(name) || name.length() > ConstValue.MAX_NAME) {
            return new ResultUtil<>().setErrorMsg("非法姓名");
        }
        String url = environment.getProperty("SCURL");
        String sckey = environment.getProperty("SCKEY");
        url = Objects.requireNonNull(url).replace("SCKEY", Objects.requireNonNull(sckey));
        Map<String, String> parameter = new HashMap<>(16);
        parameter.put(ConstValue.TEXT, name);
        parameter.put(ConstValue.DESP, message);
        try {
            HttpClientUtil.postParameters(url, parameter);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil<>().setErrorMsg("发送失败");
        }
        return new ResultUtil<>().setSuccessMsg("发送成功");
    }
}
