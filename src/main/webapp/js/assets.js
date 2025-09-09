function redireccionarARegistro(){
	
	window.location.href = "ServletLogin?accion=mostrarRegistro";
}


$(document).ready(function(){
	
	

	function mostrarEtiquetaTemporal(mensaje) {
		
		
	        const etiqueta = document.getElementById('etiqueta-respuesta');
	        if (!etiqueta) {
	            console.error("No se encontró el elemento con id 'etiqueta-respuesta'");
	            return;
	        }

	        etiqueta.innerHTML = '';
	        const p = document.createElement('p');
	        etiqueta.appendChild(p);

	        p.textContent = mensaje;
	        etiqueta.style.display = 'block';
	        etiqueta.classList.add('mover-etiqueta');
	        
	        setTimeout(() => {
				
	            
	            etiqueta.classList.remove('mover-etiqueta');
				etiqueta.style.display = 'none';
			}, 2000);
	    }
	
		function limpiarCamposRegistro() {
		      $('#nombre-txt').val('');
		      $('#apellido-txt').val('');
		      $('#correo-txt').val('');
		      $('#contrasena-txt').val('');
		  }
		  
		  
		  
		  
		  
	
	$('#btnlogin').on('click',function(event){
		
		event.preventDefault();
		
		
		
		let correo = $('#correo-txt').val();
		let contrasena = $('#contrasena-txt').val();
		if(	correo===""||contrasena===""		){
				alert("completa todos los campos");
				return;}
				
		
		// Petición AJAX
			$.ajax({
				url:'ServletLogin',
				type: 'POST',
				data:{
					correo: correo,
					contrasena: contrasena
				},
				dataType: 'json',
				success : 	 function(response){
					
					/*
					SE OBTIENE UN 'RESPONSE' COMO UN OBJETO QUE ALMACENA EL CONTENIDO 
					DEL PROTOCOLO HTTP 
					*/
					
				    if (response.estado === true) {
						
					
				      
				        mostrarEtiquetaTemporal(response.mensaje);
			window.location.href ="GestionPacientes.jsp";				    }
			 else {
				        mostrarEtiquetaTemporal("Error: " + response.mensaje);
				    }
				}
				
				,
				// ... en tu llamada AJAX
				error: function(xhr, status, error) {
				    console.error("Error de AJAX:", status, error);
				    console.log("Respuesta del servidor:", xhr.responseText); // Esta línea es para depuración.
				    alert('Ocurrió un error en el registro. Revisa la consola para más detalles.');
				}
			})
;
		
				
		
	})
	




	$('#btnProcesarGestion').on('click', function(event){

		event.preventDefault();
		let nombre = $('#txtNombre').val();
		let fecha = $('#txtfecha').val();
		let sexo = $('#cboSexo').val();
		let telefono = $('#txtTelefono').val();
		let direccion = $('#txtDireccion').val();
		let consulta= $('#txtMotivo').val();
		
		const jsonObject={'nombre': nombre   , "fecha" : fecha  ,"sexo":sexo,"telefono":telefono,
			"direccion":direccion,"consulta":consulta }
		console.log(jsonObject);
		
		
	
		
		
		
	
		$.ajax({
			url:'servletGestionPac',
			type: 'POST',
			contentType: 'application/json', // SERVLET RECIBE UN TIPO DE DATO JSON
			 data: JSON.stringify(jsonObject),
			/**
			 *  SE COLOCA LA DATA QUE RECIBE EL SERVIDOR 
			 * 
			 *  */ 
			 dataType: 'text',   // EL TIPO DE DATO DE LA RESPUESTA DEL SERVIDOR 
			success : function(response){
			
				console.log( "respuesta del servidor: "+" "+response)
				
				console.log(typeof response)
				
				mostrarEtiquetaTemporal("¡Bienvenido, " + response +"!");
				
			}
			
			,
			error: function(xhr, status, error){
				alert('Ocurrió un error en el registro: ' + error);
				console.log(error)
			}
		});

	

				
				
		
		
		
	})

	
	
		
	
	$('#btnProcesar').on('click', function(event){
		
		event.preventDefault(); 

		
		
		
		
		
		
		let nombre = $('#nombre-txt').val();
		let apellido = $('#apellido-txt').val();
		let correo = $('#correo-txt').val();
		let contrasena = $('#contrasena-txt').val(); 
		
		
		if(	nombre===""||apellido===""||correo===""||contrasena===""		){
			alert("completa todos los campos");
			return;
			
		}
		
		
		
		
		// Petición AJAX
		$.ajax({
			url:'ServletRegistro',
			type: 'POST',
			data:{
				
				
				
				nombre: nombre,
				apellido: apellido,
				correo: correo,
				contrasena: contrasena
			},
			success : 
			
	
			
			function(response){
			               if (response.estado === true) {
			                   mostrarEtiquetaTemporal(response.mensaje);
			                   limpiarCamposRegistro(); 
			               } else {
			                   mostrarEtiquetaTemporal("Error: " + response.mensaje);
			               }
			           },
			
			
			error: function(xhr, status, error){
				alert('Ocurrió un error en el registro: ' + error);
			}
		});
	}








);
});