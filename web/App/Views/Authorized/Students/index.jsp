<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header card-header-primary card-header-icon">
            <div class="card-icon">
              <i class="material-icons">assignment</i>
            </div>
            <h4 class="card-title">Listado de alumnos</h4>
            <a href="students/add">Añadir alumno</a>
          </div>
          <div class="card-body">
            <div class="material-datatables">
              <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                <thead>
                <tr>
                  <th>Legajo</th>
                  <th>Nombre y apellido</th>
                  <th>Fecha de nacimiento</th>
                  <th>Direccion</th>
                  <th>Localidad</th>
                  <th>Provincia</th>
                  <th>Email</th>
                  <th>Teléfono</th>
                  <th class="disabled-sorting text-right"></th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>Legajo</th>
                  <th>Nombre y apellido</th>
                  <th>Fecha de nacimiento</th>
                  <th>Direccion</th>
                  <th>Localidad</th>
                  <th>Provincia</th>
                  <th>Email</th>
                  <th>Teléfono</th>
                  <th class="text-right"></th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="user" items="${ users }">
                  <tr>
                    <td>${ user.getDocket() }</td>
                    <td>${ user.getName() }, ${ user.getLastname() }</td>
                    <td>${ user.getBorndate(true) }</td>
                    <td>${ user.getAddress() }</td>
                    <td>${ user.getLocation() }</td>
                    <td>${ user.getProvince() }</td>
                    <td>${ user.getEmail() }</td>
                    <td>${ user.getPhoneNumber() }</td>
                    <td>
                      <a href="students/edit?docket=${ user.getDocket() }" class="btn btn-link btn-warning btn-just-icon edit">
                        <i class="material-icons">dvr</i>
                      </a>
                      <a href="students/remove?docket=${ user.getDocket() }" class="btn btn-link btn-danger btn-just-icon remove">
                        <i class="material-icons">close</i>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</layout:authorized>