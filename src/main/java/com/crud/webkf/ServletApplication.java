package com.crud.webkf;

import com.crud.webkf.bean.Acconut;
import com.crud.webkf.bean.User;
import com.crud.webkf.dao.Dao;
import com.crud.webkf.dao.DaoImp;
import com.crud.webkf.util.MD5;
import com.crud.webkf.util.Tools;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author NJQ-PC
 */
@WebServlet(name = "main.do", value = "/main.do", loadOnStartup = 1)
public class ServletApplication extends HttpServlet {

    private Dao dao;
    private int length;

    @Override
    public void init() throws ServletException {
        dao = new DaoImp();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        switch (name) {
            case "select" -> inQuire(request, response);
            case "update" -> {
                System.out.println("修改数据UPDATE");
                User paramterUpdate = (User) Tools.getParameter(User.class, request);
                paramterUpdate.setName(request.getParameter("addname"));
                System.out.println(dao.update(paramterUpdate));
                response.sendRedirect("/main.do?name=select");
            }
            case "delete" -> {
                String id = request.getParameter("id");
                User user = new User();
                user.setUserId(id);
                boolean delete = dao.delete(user);
                System.out.println("删除DEL" + id + "is" + delete);
                inQuire(request, response);
            }
            case "add" -> {
                request.setCharacterEncoding("UTF-8");
                User paramter = (User) Tools.getParameter(User.class, request);
                paramter.setName(request.getParameter("addname"));
                boolean add = dao.add(paramter);
                System.out.println("add:" + add);
                response.sendRedirect("/main.do?name=select");
            }
            case "limitquery" -> {
                String page = request.getParameter("page");
                int i;
                if (page != null && !page.equals("")) {
                    i = Integer.parseInt(page);
                    if (i != 1) {
                        if (i == length) {
                            List<User> list = dao.limitQuery((i - 1) * 7, 7);
                            request.setAttribute("list", list);
                            request.setAttribute("pagesize", length);
                            try {
                                request.getRequestDispatcher("/infoTable.jsp?page=" + i).forward(request, response);

                            } catch (ServletException | IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        List<User> list = dao.limitQuery(i * 7, 7);
                        request.setAttribute("list", list);
                        request.setAttribute("pagesize", length);
                        try {
                            request.getRequestDispatcher("/infoTable.jsp?page=" + (++i)).forward(request, response);
                        } catch (ServletException | IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                }
                List<User> list = dao.limitQuery(7, 7);
                request.setAttribute("list", list);
                request.setAttribute("pagesize", length);
                i = 1;
                try {
                    request.getRequestDispatcher("/infoTable.jsp?page=" + (++i)).forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
            case "Previous" -> {
                String previous = request.getParameter("page");
                if (previous != null && !previous.equals("")) {
                    int p = Integer.parseInt(previous);
                    if (p == 1) {
                        List<User> listP = dao.limitQuery(0, 7);
                        request.setAttribute("list", listP);
                        request.setAttribute("pagesize", length);
                        request.getRequestDispatcher("/infoTable.jsp?page=" + p).forward(request, response);
                        return;
                    }
                    List<User> listP = dao.limitQuery((--p - 1) * 7, 7);
                    request.setAttribute("list", listP);
                    request.setAttribute("pagesize", length);
                    request.getRequestDispatcher("/infoTable.jsp?page=" + p).forward(request, response);
                    return;

                }
                List<User> listP = dao.limitQuery(0, 7);
                request.setAttribute("list", listP);
                request.setAttribute("pagesize", length);
                request.getRequestDispatcher("/infoTable.jsp?page=" + 1).forward(request, response);
            }
            case "firstPage" -> {
                List<User> firstPageList = dao.limitQuery(0, 7);
                request.setAttribute("list", firstPageList);
                request.setAttribute("pagesize", length);
                request.getRequestDispatcher("/infoTable.jsp?page=" + 1).forward(request, response);
            }
            case "lastPage" -> {
                List<User> lastPageList = dao.limitQuery((length - 1) * 7, 7);
                request.setAttribute("list", lastPageList);
                request.setAttribute("pagesize", length);
                request.getRequestDispatcher("/infoTable.jsp?page=" + length).forward(request, response);
            }
            case "login" ->{
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Acconut acconut = dao.loginAcconut(username);
                if (acconut!=null){
                    if (MD5.md5_2(password).equals(acconut.getPassword())){
                        response.sendRedirect("/main.do?name=select");
                    }else{
                        response.setCharacterEncoding("UTF-8");
                        response.sendRedirect("/login.jsp?err=ThePasswordIsIncorrect");

                    }
                    return;
                }
                response.setCharacterEncoding("UTF-8");
                response.sendRedirect("/login.jsp?err=notAcconut");
            }
        }


    }

    public void inQuire(HttpServletRequest request, HttpServletResponse response) {
        List<User> select = dao.select();
        length = (int) (Math.ceil(select.size() / 7.0));
        request.setAttribute("pagesize", length);
        request.setAttribute("list", dao.limitQuery(0, 7));
        try {
            request.getRequestDispatcher("/infoTable.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.err.println("POST");
        System.out.println(req.getParameter("name"));
        resp.addCookie(new Cookie("token", ""));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.err.println("GET");
    }
}
