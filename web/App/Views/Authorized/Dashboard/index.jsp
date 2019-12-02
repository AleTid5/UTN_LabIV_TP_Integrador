<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:authorized>
	<jsp:body>
          <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-6">
              <div class="card card-stats">
                <div
						class="card-header card-header-rose card-header-icon">
                  <div class="card-icon">
                    <i class="material-icons">equalizer</i>
                  </div>
                  <p class="card-category">Cantidad de alumnos</p>
                  <h3 class="card-title">75.521</h3>
                </div>
                <div class="card-footer">
                  <div class="stats">
                    <i class="material-icons">local_offer</i> En el año en curso (2019)
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
              <div class="card card-stats">
                <div
						class="card-header card-header-success card-header-icon">
                  <div class="card-icon">
                    <i class="material-icons">store</i>
                  </div>
                  <p class="card-category">Cantidad de cursos abiertos</p>
                  <h3 class="card-title">134</h3>
                </div>
                <div class="card-footer">
                  <div class="stats">
                    <i class="material-icons">date_range</i> En el cuatrimestre actual (2do cuatrimestre)
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
              <div class="card card-stats">
                <div
						class="card-header card-header-info card-header-icon">
                  <div class="card-icon">
                    <i class="fa fa-line-chart"></i>
                  </div>
                  <p class="card-category">Promedio mas alto</p>
                  <h3 class="card-title">8,79</h3>
                </div>
                <div class="card-footer">
                  <div class="stats">
                    <i class="material-icons">update</i> Desde el inicio
                  </div>
                </div>
              </div>
            </div>
          </div>

    </jsp:body>
</layout:authorized>
<script src="${ assetsPath }/js/plugins/chartist.min.js"></script>