<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:authorized>
	<jsp:body>
          <div class="row">
            <div class="col-md-12">
              <div class="card ">
                <div class="card-header card-header-rose card-header-text">
                  <div class="card-text">
                    <h4 class="card-title">Editar Profesor</h4>
                  </div>
                </div>
                <div class="card-body ">
                  <form method="get" action="/" class="form-horizontal">
                     <div class="row">
                      <label class="col-sm-2 col-form-label">Legajo</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Legajo" value="12345" disabled>
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <label class="col-sm-2 col-form-label">Nombre y apellido</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Ingrese nombre y apellido" value="Kloster Daniel">
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <label class="col-sm-2 col-form-label">Fecha Nacimiento</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Ingrese fecha de nacimiento" value="12/08/1987">
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <label class="col-sm-2 col-form-label">Dirección</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Ingrese la dirección" value="Av. Maipu 312">
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <label class="col-sm-2 col-form-label">Localidad</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Ingrese la localidad" value="Olivos">
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <label class="col-sm-2 col-form-label">Provincia</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Ingrese la provincia" value="Buenos Aires">
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <label class="col-sm-2 col-form-label">E-Mail</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Ingrese el e-mail" value="kdaniel@gmail.com">
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <label class="col-sm-2 col-form-label">Teléfono</label>
                      <div class="col-sm-10">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Ingrese el teléfono" value="1231232">
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
                <div class="card-footer ">
                  <div class="row">
                    <div class="col-md-offset-11 col-md-1">
                      <button type="submit" class="btn btn-fill btn-rose">Agregar</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
    </jsp:body>
</layout:authorized>