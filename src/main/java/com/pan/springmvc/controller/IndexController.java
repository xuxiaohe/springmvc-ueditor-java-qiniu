/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pan.springmvc.controller;

import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;
import com.domain.News;
import com.util.Config;
import com.util.FileUpload;
import com.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = {"/"})
public class IndexController extends BaseController {

    @RequestMapping(value = {"/changeNewsInfo"}, method = RequestMethod.GET)
    public String index(Model model) {
        JSONObject addnews = addnews();
        JSONArray data = addnews.getJSONArray("data");
        News news=new News();
        model.addAttribute("data",data);
        model.addAttribute("news",news);
        model.addAttribute("path",Config.CORESERVICE);

        return "addnews";
    }



    @RequestMapping(value = {"/updateNewsInfo"}, method = RequestMethod.GET)
    public String updateNewsInfo(Model model,String id) {
        JSONObject updatenews = updatenews(id);
        JSONArray data = updatenews.getJSONArray("data");
        JSONObject news = updatenews.getJSONObject("news");
        model.addAttribute("data",data);
        model.addAttribute("news",news);
        model.addAttribute("path",Config.CORESERVICE);

        return "addnews";
    }



    /*
            * 添加新闻
            */
    @RequestMapping("addnewinfo")
    public String addnewinfo(Model model, HttpServletRequest request,@RequestParam MultipartFile image) throws IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String ischoose = request.getParameter("ischoose");
        String top = request.getParameter("top");
        String content = request.getParameter("content");
        String finalpath = request.getParameter("oldimage");
        State state=new BaseState(false);
        Map mm=new HashMap();
        mm.put("id",id);
        mm.put("title",title);
        mm.put("ischoose",ischoose);
        mm.put("top",top);
        mm.put("content",content);
        if(!image.isEmpty()){
            Map file = FileUpload.uploadFile(image, request);
            File f=new File((String) file.get("path"));
             state = StorageManager.saveTmpFile(f, (String) file.get("path"));
             finalpath=Config.BASEPATH+file.get("end");
        }
        if(state.isSuccess()){

           mm.put("oldimage",finalpath);
        }
        else {
            mm.put("oldimage",finalpath);
        }


        addnews(mm);
        News news=new News();
        JSONObject addnews = addnews();
        JSONArray data = addnews.getJSONArray("data");
        model.addAttribute("news",news);
        model.addAttribute("data",data);
        model.addAttribute("path",Config.CORESERVICE);
        if ("0".equals(id)) {
            return "addnews";
        }
        else {
            return "redirect:/updateNewsInfo?id="+id;
        }
    }





    private JSONObject addnews(Map requestParams) {
        String url = Config.CORESERVICE+"/addnewinfo";
        return getRestApiData(url, requestParams);
    }



    private JSONObject addnews() {
        String url = Config.CORESERVICE+"/addnews";
        return getRestApiData(url);
    }


    private JSONObject updatenews(String id) {
        String url = Config.CORESERVICE+"/updateaddnews?id="+id;
        return getRestApiData(url);
    }

}
