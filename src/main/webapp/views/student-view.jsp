<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean 
    id="single_student" 
    scope="request" 
    class="com.academik.mvc.model.Student"/>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | ${single_student.fullName}"/>
</jsp:include>

<h1>Información del estudiante</h1>

    <div class="row">
        <div class="col">Nombres</div>
        <div class="col">${single_student.firstName}</div>
    </div>
    <div class="row">
        <div class="col">Apellidos</div>
        <div class="col">${single_student.lastName}</div>
    </div>
    <div class="row">
        <div class="col">Correo electrónico</div>
        <div class="col">${single_student.email}</div>
    </div>
    <div class="row">
        <div class="col">Fecha de nacimiento</div>
        <div class="col">${single_student.birthday}</div>
    </div>
    <div class="row">
        <div class="col">Encargado (a)</div>
        <div class="col">${single_student.guardian}</div>
    </div>
    <div class="row">
        <div class="col">Teléfono de contacto</div>
        <div class="col">${single_student.contactPhone}</div>
    </div>
    <a class="btn btn-primary" href="edit?id=${single_student.code}">Editar</a>
    <input class="btn btn-danger" type="submit" form="form-to-delete" value="Eliminar">
    <form method="POST" name="form-to-delete" id="form-to-delete">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="code" value="${single_student.code}"/>
    </form>

<%@include file="../templates/footer.jsp" %>