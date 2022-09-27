package com.crud.webkf;

import com.crud.webkf.bean.User;
import com.crud.webkf.dao.Dao;
import com.crud.webkf.dao.DaoImp;
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
            case "select":
                inQuire(request, response);
                break;
            case "update":
                System.out.println("修改数据UPDATE");
                User paramterUpdate = (User) Tools.getParamter(User.class, request);
                paramterUpdate.setName(request.getParameter("addname"));
                System.out.println(dao.update(paramterUpdate));
                response.sendRedirect("/main.do?name=select");
                break;
            case "delete":
                String id = request.getParameter("id");
                User user = new User();
                user.setUserId(id);
                boolean delete = dao.delete(user);
                System.out.println("删除DEL" + id + "is" + delete);
                inQuire(request, response);
                break;

            case "add":
                request.setCharacterEncoding("UTF-8");
                User paramter = (User) Tools.getParamter(User.class, request);
                paramter.setName(request.getParameter("addname"));
                boolean add = dao.add(paramter);
                System.out.println("add:" + add);
                response.sendRedirect("/main.do?name=select");
                break;
            case "limitquery":
                String page = request.getParameter("page");
                int i;
                if (page != null && !page.equals("")) {
                    i = Integer.parseInt(page);
                    if (i != 1) {
                        List<User> list = dao.limitQuery((i - 1) * 9, 9);
                        request.setAttribute("list", list);
                        request.setAttribute("pagesize", length);
                        try {
                            request.getRequestDispatcher("/infoTable.jsp?page=" + (++i)).forward(request, response);
                        } catch (ServletException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                List<User> list = dao.limitQuery(9, 9);
                request.setAttribute("list", list);
                request.setAttribute("pagesize", length);
                i = 1;
                try {
                    request.getRequestDispatcher("/infoTable.jsp?page=" + (++i)).forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Previous":
                String previous = request.getParameter("page");
                if (previous != null && !previous.equals("")) {
                    int p = Integer.parseInt(previous);
                    if (p == 1) {
                        List<User> listP = dao.limitQuery(0, 9);
                        request.setAttribute("list", listP);
                        request.setAttribute("pagesize", length);
                        request.getRequestDispatcher("/infoTable.jsp?page=" + p).forward(request, response);
                    }
                    List<User> listP = dao.limitQuery((--p-1) * 9, 9);
                    request.setAttribute("list", listP);
                    request.setAttribute("pagesize", length);
                    request.getRequestDispatcher("/infoTable.jsp?page=" + p).forward(request, response);

                }
                break;
        }


    }

    public void inQuire(HttpServletRequest request, HttpServletResponse response) {
        List<User> select = dao.select();
        length = (int) (Math.ceil(select.size() / 9.0));
        request.setAttribute("pagesize", length);
        request.setAttribute("list", dao.limitQuery(0, 9));
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