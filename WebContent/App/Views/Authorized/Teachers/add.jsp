<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:authorized>
    <jsp:body>
        <form method="POST" action="" class="form-horizontal" validate>
            <div class="row">
                <div class="col-md-12">
                    <div class="card ">
                        <div class="card-header card-header-rose card-header-text">
                            <div class="card-text">
                                <h4 class="card-title">Agregar Profesor	</h4>
                            </div>
                        </div>
                        <div class="card-body ">
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Nombre ${ error }</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="name" class="form-control" placeholder="Ingrese nombre">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Apellido</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="lastname" class="form-control" placeholder="Ingrese apellido">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Fecha Nacimiento</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="borndate" class="form-control" placeholder="Ingrese fecha de nacimiento">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Dirección</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="address" class="form-control" placeholder="Ingrese la dirección">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Localidad</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <select required name="location" class="selectpicker" data-style="select-with-transition">
                                                ${ optionLocations }
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Provincia</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <select required name="province" class="selectpicker" data-style="select-with-transition">
                                                ${ optionProvinces }
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">E-Mail</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="email" class="form-control" placeholder="Ingrese el e-mail">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Teléfono</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" class="form-control" placeholder="Ingrese el teléfono">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer ">
                            <div class="row">
                                <div class="col-md-offset-11 col-md-1">
                                    <button type="submit"  class="btn btn-fill btn-rose">Agregar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:body>
</layout:authorized>