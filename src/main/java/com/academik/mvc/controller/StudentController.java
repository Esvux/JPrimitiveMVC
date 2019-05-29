package com.academik.mvc.controller;

import com.academik.mvc.dao.StudentDAO;
import com.academik.mvc.model.Student;
import com.academik.mvc.utils.TimeUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author esvux
 */
@WebServlet("/students/*")
public class StudentController extends HttpServlet {

    StudentDAO dao = new StudentDAO();
    
    /**
     * Determina que "recurso" mostrar al usuario.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String complement = req.getPathInfo();
        if(complement == null)
            complement = "";
        System.err.println(complement);
        String redirectPage;
        switch(complement) {
            case "/create":
                redirectPage = "student-create.jsp";
                break;
            case "/view":
                //Obtengo el parametro desde la URL
                long idToView = Long.parseLong(req.getParameter("id"));
                Student sToView = dao.findById(idToView);
                req.setAttribute("single_student", sToView);
                redirectPage = "student-view.jsp";
                break;
            case "/edit":
                long idToEdit = Long.parseLong(req.getParameter("id"));
                Student sToEdit = dao.findById(idToEdit);
                req.setAttribute("single_student", sToEdit);
                redirectPage = "student-edit.jsp";
                break;
            case "/list":
            case "/":
            case "":
                List<Student> allStudents = dao.queryAll();
                req.setAttribute("list_of_students", allStudents);
                redirectPage = "student-list.jsp";
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/students");
                return;
        }

        //Renderice este JSP
        RequestDispatcher rd = req.getRequestDispatcher(
                "/views/" + redirectPage
        );
        //Adelante con la renderización
        rd.forward(req, resp);
    }

    /**
     * Para recibir la petición de crear un nuevo estudiante.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        if("PUT".equals(req.getParameter("_method"))){
            doPut(req, resp);
            return;
        }
        if("DELETE".equals(req.getParameter("_method"))){
            doDelete(req, resp);
            return;
        }
            
        System.out.println("Creating a new student");
        //Variable vacia
        Student noob = new Student();
        
        //Valores para las propiedades que vienen desde el formulario HTML
        noob.setFirstName(req.getParameter("s_firstname"));
        noob.setLastName(req.getParameter("s_lastname"));
        noob.setGender(req.getParameter("s_gender"));
        noob.setEmail(req.getParameter("s_email"));
        noob.setContactPhone(req.getParameter("s_contactphone"));
        noob.setGuardian(req.getParameter("s_guardian"));
        noob.setBirthday(TimeUtils.getFromDDMMYYYY(req.getParameter("s_birthday")));
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.create(noob);
        resp.sendRedirect(req.getContextPath() + "/students");
    }

    /**
     * Para modificar un estudiante ya existente.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Editing an student...");
        //Variable vacia
        Student edited = new Student();
        
        //Valores NUEVOS para las propiedades que vienen desde el formulario HTML
        edited.setFirstName(req.getParameter("s_firstname"));
        edited.setLastName(req.getParameter("s_lastname"));
        edited.setGender(req.getParameter("s_gender"));
        edited.setEmail(req.getParameter("s_email"));
        edited.setContactPhone(req.getParameter("s_contactphone"));
        edited.setGuardian(req.getParameter("s_guardian"));
        edited.setBirthday(TimeUtils.getFromDDMMYYYY(req.getParameter("s_birthday")));
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.edit(Integer.parseInt(req.getParameter("code")), edited);
        resp.sendRedirect(req.getContextPath() + "/students");
    }

    /**
     * Para eliminar un estudiante.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("Deleting student...");
        long id = Long.parseLong(req.getParameter("code"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/students");
    }
    
}
