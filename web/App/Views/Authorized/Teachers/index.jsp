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
                  <h4 class="card-title">Listado de profesores</h4>
                </div>
                <div class="card-body">
                  <div class="toolbar">
                    <!--        Here you can write extra buttons/actions for the toolbar              -->
                  </div>
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
                        ${ userList }
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