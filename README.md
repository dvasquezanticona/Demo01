/*
Fecha: 		24/09/2018
Autor: 		Daniel Andres V.
Descpión: 	Planteamiento de los CUS del sistema (Casos de uso)
*/


----------------------------------------------------------------------------------
CUS001.00.00 Inicio de session
----------------------------------------------------------------------------------

CUS001.01.00 Acceder a la ventana de login
Para acceder a la ventana de login se puede realizar de tres maneras:
	- entrando la dirección raíz de la aplicación htp//localhost:4200
	- entrando directamente a la dirección de login http://localhost:4200/login
	- cuando ha finalizado la session o cuando se accede a un recurso con un token invalido (o sin token) la aplicación redirige al usuario a la ventana de login.

CUS001.02.00 Realizar login en la aplicación
Dentro de la ventana de login se ingresa un usuario/contraseña que esté registrado en la base de datos. Desde esta ventana se invoca al webservice de inicio de sessión quien validará contra la base de datos si los datos ingresados son correctos. De ser correctos generará un token y lo devolverá en la respuesta al front-end. En caso de haber un error se mostrará el mensaje en la sección de mensajes de la aplicación. Los posibles errores son:
	- servicio no disponible
	- clave o login incorrectos

	
	
----------------------------------------------------------------------------------
CUS002.00.00 Mantenimiento de usuarios
----------------------------------------------------------------------------------

CUS002.01.00 Ventana de mantenimiento
Una vez realizado el inicio de sessión y ti tiene los permisos necesarios (CUS003-Permisos de usuario) se podrá acceder mediante el menú "General/Mantenimiento de Usuarios" al listado de todos los usuarios. Dentro del listado se puede modificar o eliminar o crear un registro.

















----------------------------------------------------------------------------------
ERRORES POR RESOLVER
----------------------------------------------------------------------------------
- al presionar el botón 'Acceder' en el login con credenciales erróneas o correctas se invoca al servicio pero da este error sin llegar la petición al back-end:
"Mensaje de error desde el handleErrorObservable. Devolvemos un Observable.throw. Failed to execute 'setRequestHeader' on 'XMLHttpRequest': 'Bearer function () {
        return localStorage.getItem(_app_const_module__WEBPACK_IMPORTED_MODULE_1__["environment"].TOKEN_NAME);
    }' is not a valid HTTP header field value. - undefined"
