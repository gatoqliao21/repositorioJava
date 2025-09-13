/**
 * 
 */

$(document).ready(function(){
	

	
	
	
	
	
	const miBoton = document.getElementById('miBoton');
	const miFormulario = document.getElementById('contenedorGeneral');
		const btnCerrarFormulario = document.getElementById('cerrar-formulario');
		// Es crucial verificar que los elementos existen antes de usar addEventListener
		if (miBoton && miFormulario) {
		    miBoton.addEventListener('click', function() {
				if (window.getComputedStyle(miFormulario).display === 'none') {
		            miFormulario.style.display = 'block';
					miFormulario.style.position= 'absolute';
		        }  
				
				else {
		            miFormulario.style.display = 'none';
		        }
		    });
		} else {
		    console.error("No se encontraron los elementos 'miBoton' o 'contenedorGeneral'.");
		}
		

		btnCerrarFormulario.addEventListener('click',function(){

			const btnEstilo= window.getComputedStyle(miFormulario);
				if(btnEstilo.display !== 'none'){
					
					miFormulario.style.display='none';
					
				}
				

			
			
			
			
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

	
})