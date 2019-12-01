<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:authorized>
	<jsp:body>
            <div class="row">
              <div class="col-md-12">
                <div class="card">
                  <div
						class="card-header card-header-icon card-header-rose">
                    <div class="card-icon">
                      <i class="material-icons">assignment</i>
                    </div>
                    <h4 class="card-title ">Cargar notas</h4>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive" style="overflow-x: hidden;">
                      <table class="table">
                        <thead class=" text-primary">
                          <th>Legajo</th>
                          <th>Nombre y apellido</th>
                          <th>Materia</th>
                          <th>Nota Parcial 1</th>
                          <th>Nota Parcial 2</th>
                          <th>Nota Recuperatorio Parcial 1</th>
                          <th>Nota Recuperatorio Parcial 2</th>
                          <th></th>
                        </thead>
                        <tbody>
                        <tr>
                        <td>12345</td>
                        <td>Tidele Alejandro</td>
                        <td>Laboratorio IV</td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                    <td>
                     <div class="togglebutton">
                        <label>
                          Libre
                          <input type="checkbox" checked="">
                          <span class="toggle"></span>
                          Regular
                        </label>
                      </div>
                    </td>
                        </tr>
                        <tr>
                        <td>12342</td>
                        <td>Heinz Mariano</td>
                        <td>Laboratorio IV</td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                        <td>
                     <div class="row">
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Nota">
                        </div>
                      </div>
                    </div>
                    </td>
                    <td>
                     <div class="togglebutton">
                        <label>
                          Libre
                          <input type="checkbox">
                          <span class="toggle"></span>
                          Regular
                        </label>
                      </div>
                    </td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                  <div class="card-footer" style="margin: 0 0px 10px;">
                  <div class="row">
                    <div class="offset-8 offset-sm-10 col-sm-2">
                      <button type="submit" class="btn btn-fill btn-rose">Guardar</button>
                    </div>
                  </div>
                </div>
                </div>
              </div>
              </div>
    </jsp:body>
</layout:authorized>