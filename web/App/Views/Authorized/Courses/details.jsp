<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:authorized>
	<jsp:body>          
          <div class="row">
            <div class="col-md-12">
              <div class="card ">
                <div class="card-header ">
                	<div class="row">
                	<div class="col-sm-11">
                		<h4 class="card-title">Detalle del curso -
                    		<small class="description">Herrera Tamara</small>
                  		</h4>
                	</div>
                	<div class="col-sm-1">
                	<button class="btn btn-warning btn-round btn-fab" onclick="window.location.href = '${mainPath}/courses/uploadNotes'">
                      <i class="material-icons">flash_on</i>
                    <div class="ripple-container"></div>
								</button>
                	</div>
                	</div>
                </div>
                <div class="card-body ">
                  <div class="row">
                    <div class="col-md-4">
                      <ul
									class="nav nav-pills nav-pills-rose flex-column" role="tablist">
                        <li class="nav-item">
                          <a class="nav-link active" data-toggle="tab"
										href="#link4" role="tablist">
                            Laboratorio IV
                          </a>
                        </li>                       
                      </ul>
                    </div>
                    <div class="col-md-8">
                      <div class="tab-content">
                        <div class="tab-pane active" id="link4">
                        	<h4>Alumnos</h4>
                        	<div>Alejandro Tidele</div>
                        	<div>Matias Heinz</div>
                         </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
    </jsp:body>
</layout:authorized>