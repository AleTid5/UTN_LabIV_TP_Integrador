<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:authorized>
	<jsp:body>
          <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-header card-header-primary card-header-icon">
                  <div class="card-icon">
                    <i class="material-icons">assignment</i>
                  </div>
                  <h4 class="card-title">Listado de cursos</h4>
                </div>
                <div class="card-body">
                  <div class="toolbar">
                    <!--        Here you can write extra buttons/actions for the toolbar              -->
                  </div>
                  <div class="material-datatables">
                    <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                      <thead>
                        <tr>
                          <th>Materia</th>
                          <th>Semestre</th>
                          <th>Año</th>
                          <th>Docente</th>
                          <th class="disabled-sorting text-right"></th>
                        </tr>
                      </thead>
                      <tfoot>
                        <tr>
                          <th>Materia</th>
                          <th>Semestre</th>
                          <th>Año</th>
                          <th>Docente</th>
                          <th class="text-right"></th>
                        </tr>
                      </tfoot>
                      <tbody>
                        <tr>
                          <td>Programacion I</td>
                          <td>Primer Semestre</td>
                          <td>2019</td>
                          <td>Maximiliano Sar Fernandez</td>
                          <td class="text-right">
                            <a href="#" class="btn btn-link btn-warning btn-just-icon edit"><i class="material-icons">dvr</i></a>
                            <a href="#" class="btn btn-link btn-danger btn-just-icon remove"><i class="material-icons">close</i></a>
                          </td>
                        </tr>
                        <tr>
                          <td>Programacion II</td>
                          <td>Primer Semestre</td>
                          <td>2019</td>
                          <td>Kloster Daniel</td>
                          <td class="text-right">
                            <a href="#" class="btn btn-link btn-warning btn-just-icon edit"><i class="material-icons">dvr</i></a>
                            <a href="#" class="btn btn-link btn-danger btn-just-icon remove"><i class="material-icons">close</i></a>
                          </td>
                        </tr>
                        <tr>
                          <td>Laboratorio IV</td>
                          <td>Primer Semestre</td>
                          <td>2019</td>
                          <td>Tamara Herrera</td>
                          <td class="text-right">
                            <a href="#" class="btn btn-link btn-warning btn-just-icon edit"><i class="material-icons">dvr</i></a>
                            <a href="#" class="btn btn-link btn-danger btn-just-icon remove"><i class="material-icons">close</i></a>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
                <!-- end content-->
              </div>
              <!--  end card  -->
            </div>
            <!-- end col-md-12 -->
          </div>
    </jsp:body>
</layout:authorized>