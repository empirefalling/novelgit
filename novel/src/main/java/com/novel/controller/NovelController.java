package com.novel.controller;

import com.github.pagehelper.PageInfo;
import com.novel.pojo.*;
import com.novel.service.NovelService;
import com.novel.servlet.CaptchaUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.*;


@Controller
public class NovelController {


    @Autowired
    private NovelService NovelService;
    int p = 1;
    String value;



//    List<Integer> textlist = new ArrayList<>();


    //首页分页
//    @ResponseBody
    @RequestMapping(value = "/root/book/all/{pageNum}", method = RequestMethod.GET)
    public String getBookPage(@PathVariable("pageNum") Integer pageNum, Model model, HttpSession session){
        //获取商品的分页信息

        User user = (User) session.getAttribute("ROOT_SESSION");

        PageInfo<Book> page = NovelService.getBookPage(pageNum);

        //将分页数据共享到请求域中
        model.addAttribute("page", page);
        model.addAttribute("user",user);

        return "book_list";


    }
    //首页分页
//    @ResponseBody
    @RequestMapping(value = "/book/all/{pageNum}", method = RequestMethod.GET)
    public String UsergetBookPage(@PathVariable("pageNum") Integer pageNum, Model model, HttpSession session){
        //获取商品的分页信息

        User user = (User) session.getAttribute("USER_SESSION");

        p = pageNum;
        PageInfo<BookAndText> page = NovelService.getBookAndTextPage(pageNum);

        //将分页数据共享到请求域中
        model.addAttribute("page", page);
        model.addAttribute("user",user);


        return "bookhome";
//        return page;

    }
    //小说详情
//    @ResponseBody
    @RequestMapping(value = "/book/{bookid}",method = RequestMethod.GET)
    public String Book(@PathVariable("bookid") Integer bookid, Model model){

        Book onebook =  NovelService.getOneBook(bookid);
        List<Text> books = NovelService.getBook(bookid);
        Integer first = books.get(0).getBid();



//        Integer a = 314;

        model.addAttribute("back",p);
        model.addAttribute("onebook", onebook);
        model.addAttribute("books", books);
        model.addAttribute("first",first);

        return "book";
    }

    //小说内容
//    @ResponseBody
    @RequestMapping(value = "/book/{bookid}/{bid}",method = RequestMethod.GET)
    public String Read(@PathVariable("bookid") Integer bookid, @PathVariable("bid") Integer bid, Model model){
        Text text = NovelService.getPage(bid);
        Book book = NovelService.getOneBook(bookid);

        List<Text> books = NovelService.getBook(bookid);

        List<String> page = new ArrayList<>();
        List<Integer> ll = new ArrayList();
        if (text.getPage().contains("\n")) {
            List<String> pages = Arrays.asList(text.getPage().split("\n"));
            for (String page1 : pages) {
                page.add(page1);
            }
            for (Text bo : books) {
                ll.add(bo.getBid());
            }

        } else {
            if (text.getPage().contains("。")) {
                List<String> pages = Arrays.asList(text.getPage().split("。"));
                for (String page1 : pages) {
                    page.add(page1 + "。");
                }
                for (Text bo : books) {
                    ll.add(bo.getBid());
                }

            }
            else {
                List<String> pages = Arrays.asList(text.getPage());
                for (String page1 : pages) {
                    page.add(page1);
                }
                for (Text bo : books) {
                    ll.add(bo.getBid());
                }
            }

        }

        Integer pre = 0;
        Integer next = 0;
        if (bid.equals(ll.get(0)) && ll.size()>1) {
            next = ll.get(ll.indexOf(bid)+1);
            model.addAttribute("pre"," ");
            model.addAttribute("next",next);
        } else if (bid.equals(ll.get(0)) && ll.size()==1) {
            model.addAttribute("pre"," ");
            model.addAttribute("next"," ");
        } else if (bid.equals(ll.get(ll.size()-1))) {
            pre = ll.get(ll.indexOf(bid)-1);
            model.addAttribute("pre",pre);
            model.addAttribute("next"," ");
        }else {
            pre = ll.get(ll.indexOf(bid)-1);
            next = ll.get(ll.indexOf(bid)+1);
            model.addAttribute("pre",pre);
            model.addAttribute("next",next);
        }


        model.addAttribute("book", book);
        model.addAttribute("text",text);
        model.addAttribute("page",page);



        return "read";
    }

    //搜索
//    @ResponseBody
    @RequestMapping(value = "/book/search/{pageNum}",method = RequestMethod.GET)
    public String searchbook(@PathVariable("pageNum") Integer pageNum, Model model, HttpServletRequest request){

        p=pageNum;

        if(value==null && request.getParameter("search")!=null){
            value = request.getParameter("search");
            value = "%".concat(value) + "%";
            PageInfo<BookAndText> page = NovelService.searchBook(pageNum,value);
            model.addAttribute("page", page);
//            return page;
        } else if (value!=null && request.getParameter("search")!=null) {
            value = request.getParameter("search");
            value = "%".concat(value) + "%";
            PageInfo<BookAndText> page = NovelService.searchBook(pageNum,value);
            model.addAttribute("page", page);
//            return page;
        }else if (value!=null && request.getParameter("search")==null){

            PageInfo<BookAndText> page = NovelService.searchBook(pageNum,value);
            model.addAttribute("page", page);
//            return page;
        }
        model.addAttribute("back",p);

        return "searchbook";
    }

    //跳转增加小说
    @RequestMapping(value = "/book/add/{user}")
    public String add(@PathVariable("user") String username, Book book,Model model){
        book = NovelService.getMaxBookid();
        int max = book.getBookid() + 1;
        model.addAttribute("max",max);
        model.addAttribute("username",username);

        return "addbook";
    }


    //增加小说
//    @ResponseBody
    @RequestMapping(value = "/book/addbook/{user}/{bookid}",method = RequestMethod.POST)
    public String addBook(@PathVariable("user") String username,@PathVariable("bookid") Integer bookid,Book book,Model model){

        book.setBookid(bookid);
        NovelService.addBook(book);
        model.addAttribute("bookid",bookid);
        model.addAttribute("username",username);

        return "addpage";
    }

    //跳转增加章节
    @RequestMapping(value = "/book/addchapterpage/{user}/{bookid}")
    public String addChapterPage(@PathVariable("user") String username,@PathVariable("bookid") Integer bookid,Model model){

        model.addAttribute("bookid",bookid);
        model.addAttribute("username",username);

        return "addpage";
    }

    //增加章节
//    @ResponseBody
    @RequestMapping(value = "/book/addpage/{user}/{bookid}",method = RequestMethod.POST)
    public String addPage(@PathVariable("user") String username,@PathVariable("bookid") Integer bookid, Text text, Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        String chapter = request.getParameter("chapter");
        String page = request.getParameter("page");
        text.setBookid(bookid);
        text.setChapter(chapter);
        text.setPage(page);

        NovelService.addShenhePage(text);


//        PageInfo<BookAndText> page1 = NovelService.getNew(1,bookid);
//        model.addAttribute("page",page1);

        return "redirect:/book/addchapter/"+URLEncoder.encode(username,"UTF-8");
    }

    //更新列表
//    @ResponseBody
    @RequestMapping(value = "/book/addchapter/{user}")
    public String addChapter(@PathVariable("user") String username,Model model){
        Map books = new HashMap();

        List<Book> book = NovelService.getShenheBook(username);
        List bb = new ArrayList<>();
        books.put("book", book);
        for (Book bo : book){
            Integer bookid = bo.getBookid();
            bb.add(NovelService.getShenheList(bookid));

        }
        books.put("list",bb);

        model.addAttribute("books", books);

        return "addchapter";
    }


    //审核列表
//    @ResponseBody
    @RequestMapping(value = "/book/shenhe/{pageNum}")
    public String shenhelist(@PathVariable("pageNum") Integer pageNum, Model model){
        PageInfo<BookAndText> page = NovelService.getShenhe(pageNum);

        model.addAttribute("page",page);


//        return page;
        return "shenhelist";
    }

    //通过
    @RequestMapping(value = "/book/pass/{bid}")
    public String addShenhePage(@PathVariable("bid") Integer bid, Text text, HttpServletRequest request) throws IOException {

        text = NovelService.getShenhePage(bid);


//        String dir = "E:\\IdeaProjects\\SSM\\novel\\src\\main\\webapp\\static\\download\\"+""+text.getBookid()+".txt";
        String dir = request.getServletContext().getRealPath("/static/download/")+text.getBookid()+".txt";
        File file = new File(dir);
        System.out.println(file.getCanonicalPath());
        if (!file.exists())
            file.createNewFile();
        System.out.println(file.getCanonicalPath());
//创建FileWriter对象
        FileWriter writer = new FileWriter(dir,true);
//向文件中写入内容
        writer.write(text.getChapter().concat("\n\n").concat(text.getPage()).concat("\n\n"));
        writer.close();
//        NovelService.addPage(text);
//        NovelService.DeleteShenhePage(text.getBid());
//        if(a == b){
//            String tip = "操作成功";
//            String status = "success";
//            model.addAttribute("tip",tip);
//            model.addAttribute("status",status);
//        }else {
//            String tip = "操作失败";
//            String status = "warning";
//            model.addAttribute("tip",tip);
//            model.addAttribute("status",status);
//        }

//        PageInfo<BookAndText> page1 = NovelService.getNew(1,bookid);
//        model.addAttribute("page",page1);


        return "redirect:/book/shenhe/1";
    }

    //审核查看
    @RequestMapping(value = "/book/shenhe/read/{bookid}/{bid}")
    public String ShenheRead(@PathVariable("bookid") Integer bookid,@PathVariable("bid") Integer bid,Model model) {
        Book book = NovelService.getOneBook(bookid);
        Text text = NovelService.getShenhePage(bid);

        List<String> page = new ArrayList<>();


        if (text.getPage().contains("\n")) {
            List<String> pages = Arrays.asList(text.getPage().split("\n"));
            for (String page1 : pages) {
                page.add(page1);
            }

        } else {
            if (text.getPage().contains("。")) {
                List<String> pages = Arrays.asList(text.getPage().split("。"));
                for (String page1 : pages) {
                    page.add(page1 + "。");
                }

            }
            else {
                List<String> pages = Arrays.asList(text.getPage());
                for (String page1 : pages) {
                    page.add(page1);
                }
            }

        }

        model.addAttribute("page",page);
        model.addAttribute("book",book);
        model.addAttribute("text",text);

        return "shenheread";
    }

    //退回
    @RequestMapping(value = "/book/retreat/{bid}")
    public String Retreat(@PathVariable("bid") Integer bid,Model model){

        String status = "true";
        NovelService.addRetreat(status,bid);

        

        return "redirect:/book/shenhe/1";
    }

    //修改章节页面
    @RequestMapping(value = "/book/change/{user}/{bid}")
    public String Change1(@PathVariable("user") String username,@PathVariable("bid") Integer bid,Model model){


        Text book = NovelService.getShenhePage(bid);

        model.addAttribute("book",book);
        model.addAttribute("username",username);

        return  "updatepage";
    }

    //修改章节
//    @ResponseBody
    @RequestMapping(value = "/book/updatepage/{user}/{bid}")
    public String Change2(@PathVariable("user") String username,@PathVariable("bid") Integer bid, Model model, Text text) throws UnsupportedEncodingException {

        String chapter = text.getChapter();
        String page = text.getPage();

        NovelService.UpdatePage(chapter,page,bid);
        NovelService.addRetreat("fales",bid);
        String url = "/book/addchapter/"+ URLEncoder.encode(username,"UTF-8");
        System.out.println(url);

        return "redirect:"+url;
    }

    //删除
    @RequestMapping(value = "/book/delete/{bookid}")
    public String Delete(@PathVariable("bookid") Integer bookid){

        NovelService.DeleteBook(bookid);
        NovelService.DeletePage(bookid);

        return "redirect:/root/book/all/1";
    }

    //小说列表
    @RequestMapping(value = "/book/chapterlist/{bookid}")
    public String Chapter(@PathVariable("bookid") Integer bookid, Model model){

        List<BookAndText> books = NovelService.getBookChapter(bookid);
        model.addAttribute("books", books);

        return "chapter_list";
    }

    //删除
    @RequestMapping(value = "/book/deletechapter/{bookid}/{bid}")
    public String DeleteChapter(@PathVariable("bookid") Integer bookid, @PathVariable("bid") Integer bid){
        NovelService.DeleteChapter(bid);

        return "redirect:/book/chapterlist/"+bookid;
    }

    @RequestMapping(value = "/root/book/search/{pageNum}")
    public String RootSearch(@PathVariable("pageNum") Integer pageNum, HttpServletRequest request, Model model){

//        p=pageNum;

        if(value==null && request.getParameter("search")!=null){
            value = request.getParameter("search");
            value = "%".concat(value) + "%";
            PageInfo<BookAndText> page = NovelService.searchBook(pageNum,value);
            model.addAttribute("page", page);
//            return page;
        } else if (value!=null && request.getParameter("search")!=null) {
            value = request.getParameter("search");
            value = "%".concat(value) + "%";
            PageInfo<BookAndText> page = NovelService.searchBook(pageNum,value);
            model.addAttribute("page", page);
//            return page;
        }else if (value!=null && request.getParameter("search")==null){

            PageInfo<BookAndText> page = NovelService.searchBook(pageNum,value);
            model.addAttribute("page", page);
//            return page;
        }
//        model.addAttribute("back",p);

        return "book_list";

    }

//    @ResponseBody
//    @RequestMapping(value = "/login")
//    public int aaa(){
//
//        int a = NovelService.getstatus(value);
//
//        return a;
//    }



    //跳转登录
    @RequestMapping(value = "/login")
    public String tologin(){
        return "login";
    }

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session, HttpServletRequest request){

        String username = user.getUsername();
        String password = user.getPassword();
        String checkCode = request.getParameter ("check_code");
//        Object code = session.getAttribute("check_code");

        User user1 = NovelService.getUser(username);
        if(user1 != null){


            if (username.equals(user1.getUsername()) && password.equals(user1.getPassword()) && checkCode.equals(session.getAttribute("check_code"))){
                if(username.equals("root")){
                    session.setAttribute("ROOT_SESSION",user);
                    return "redirect:/root/book/all/1";
                }else {
                    session.setAttribute("USER_SESSION",user);
                    return "redirect:/book/all/1";
                }

            }else if (checkCode.equals(session.getAttribute("check_code"))) {
                model.addAttribute("msg","用户名或密码错误，请重新登录!");
                return "login";
            }else{
                model.addAttribute("msg","验证码错误，请重新登录!");
                return "login";
            }
        }else {
            model.addAttribute("msg","用户名错误，请重新登录!");
            return "login";
        }

//        return user1;




    }

    //验证码
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
//    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        CaptchaUtil.outputCaptcha(request,response);
    }

    //跳转注册用户
    @RequestMapping(value = "/register")
    public String toregister(){

        return "register";
    }

    //注册用户
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user,Model model, HttpSession session){
        int a = NovelService.getstatus(user.getUsername());
        if (a==1){
            model.addAttribute("msg","用户名已存在，请重新输入");
            return "register";
        }else {
            NovelService.setUser(user);
            session.setAttribute("USER_SESSION",user);

            return "redirect:/book/all/1";
        }

    }

    //登出
    @RequestMapping(value = "/logout/{user}")
    public String logout(@PathVariable("user") String username, HttpSession session){

        if (username.equals("root")){
            session.removeAttribute("ROOT_SESSION");
        }

        session.removeAttribute("USER_SESSION");

        return "redirect:/login";

    }

    //封面
//    @ResponseBody
    @RequestMapping(value = "/book/img/{bookid}")
    public void BookImg(@PathVariable("bookid") Integer bookid, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        NovelService.outputBookImg(request, response, bookid);
    }


    //上传
//    @ResponseBody
    @RequestMapping(value = "/book/upload/{user}/{bookid}",method = RequestMethod.POST)
    public String upload(@PathVariable("user") String username, @PathVariable("bookid") Integer bookid, @RequestParam("img") MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws IOException {

//        boolean aaaa  = file.isEmpty();
        byte[] file1 = file.getBytes();
//        OutputStream outputStream = response.getOutputStream();
//        outputStream.write(file1);
//        outputStream.close();
        NovelService.UpLoad(file1,bookid);

        return "redirect:/book/addchapter/"+URLEncoder.encode(username,"UTF-8");
//        return file1;

    }

    //下载
//    @ResponseBody
    @RequestMapping(value = "/book/download/{bookid}")
    public ResponseEntity<byte[]> downloadtxt(@PathVariable("bookid") Integer bookid, HttpServletRequest request) throws IOException {

//        String dir = "E:\\IdeaProjects\\SSM\\novel\\src\\main\\webapp\\static\\download\\"+""+bookid+".txt";
        String dir = request.getServletContext().getRealPath("/static/download/")+bookid+".txt";
        File file = new File(dir);

//        List<Text> text = NovelService.getText(bookid);
//        StringBuffer file = new StringBuffer();
//        for (Text text1 : text){
//            file.append(text1.getChapter().concat("\n\n").concat(text1.getPage()).concat("\n\n"));
//        }
//        byte[] file1 = file.toString().getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",bookid+".txt");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);

    }



}
