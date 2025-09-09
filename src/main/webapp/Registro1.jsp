<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="js/assets.js" type="text/javascript"></script>

<link rel="stylesheet" href="estilos.css">
</head>
<body>
    <div id="registro-contenedor">
        <form>
            <div class="content-divs">
                <label>nombre</label>
                <input class="inputs" id="nombre-txt" type="text"/>
            </div>
            <br>
            <div class="content-divs">
                <label>apellido</label>
                <input class="inputs" id="apellido-txt" type="text"/>
            </div>
            <br>
            <div class="content-divs">
                <label>correo</label>
                <input class="inputs" type="email" id="correo-txt" />
            </div>
            <br>
            <div class="content-divs">
                <label>contraseña</label>
                <input class="inputs" type="password" id="contrasena-txt"/>
            </div>
            <br>
            <button id="btnProcesar" type="submit">Registrar</button>
        </form>
    </div>
    <div id="etiqueta-respuesta"></div>
</body>
