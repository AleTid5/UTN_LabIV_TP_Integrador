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
                        <tr>
                          <td>1234</td>
                          <td>Tamara Herrera</td>
                          <td>12/08/1995</td>
                          <td>Av. Maipu 3390</td>
                          <td>Olivos</td>
                          <td>Buenos Aires</td>
                          <td>thererra@gmail.com</td>
                          <td>21312312</td>
                          <td class="text-right">
                            <a href="${ mainPath }/teachers/edit" class="btn btn-link btn-warning btn-just-icon edit"><i class="material-icons">dvr</i></a>
                            <a href="#" class="btn btn-link btn-danger btn-just-icon remove"><i class="material-icons">close</i></a>
                          </td>
                        </tr>
                        <tr>
                          <td>1232</td>
                          <td>Kloster Daniel</td>
                          <td>12/02/1995</td>
                          <td>Av. Maipu 3340</td>
                          <td>Olivos</td>
                          <td>Buenos Aires</td>
                          <td>kdaniel@gmail.com</td>
                          <td>2312324324</td>
                          <td class="text-right">
                            <a href="${ mainPath }/teachers/edit" class="btn btn-link btn-warning btn-just-icon edit"><i class="material-icons">dvr</i></a>
                            <a href="#" class="btn btn-link btn-danger btn-just-icon remove"><i class="material-icons">close</i></a>
                          </td>
                        </tr>
                        <tr>
                          <td>1233</td>
                          <td>Fernandez Sar Maximiliano</td>
                          <td>12/10/1995</td>
                          <td>Av. Maipu 3290</td>
                          <td>Olivos</td>
                          <td>Buenos Aires</td>
                          <td>fmaxi@gmail.com</td>
                          <td>546775844</td>
                          <td class="text-right">
                            <a href="${ mainPath }/teachers/edit" class="btn btn-link btn-warning btn-just-icon edit"><i class="material-icons">dvr</i></a>
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