<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="assets.js" type="text/javascript"></script>

<link rel="stylesheet" href="estilosGestionPac.css">
</head>
<body>



    <div id="contenedorGeneral">
        <div id="titulo">
            <strong>Nuevo Paciente</strong>
        </div>
        <div class="contenedorFormulario">
            <form>
                <div class="labelcontainer">
                    
                        <label>Nombre</label>
                        <input id="txtNombre" type="text" name="nombre" >
                    <div class="labelcontainer">
                        <label>Fecha de Nacimiento</label>
                        <input  id="txtfecha" type="date" name="fecha_nacimiento" >
                    </div>
                    <div class="labelcontainer">
                        <label>Género</label>
                        <select id="cboSexo"  name="genero" >
                            <option>Masculino</option>
                            <option>Femenino</option>
                            <option>Otro</option>
                        </select>
                    </div>
                    <div class="labelcontainer">
                        <label>Teléfono</label>
                        <input  id="txtTelefono" type="text" name="telefono" >
                    </div>
                </div>
                <div class="labelcontainer">
                    <label>Dirección</label>
                    <input id="txtDireccion"  type="text" name="direccion"  ></input>
                </div  >
                <div  class="labelcontainer">
                	<label>Motivo Consulta </label>	
                	<input id="txtMotivo" type ="text" name="consulta"  >
                
                </div>
                
                
             
             <div  id:"contenedor-boton">
                <button id="btnProcesarGestion" type="submit" >Guardar</button>
            </div>
            </form>
        </div>
    </div>
    <div id="etiqueta-respuesta"></div>

</body>
</html>