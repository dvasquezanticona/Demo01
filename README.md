/*
Fecha: 		24/09/2018
Autor: 		Daniel Andres V.
Descpión: 	Planteamiento de los CUS del sistema (Casos de uso)
*/

----------------------------------------------------------------------------------
Sistema de registro de documentos (ingresos y egresos).
----------------------------------------------------------------------------------
DESCRIPCIÓN GENERAL BCDocument:
El sistema permite registrar al usuario (operario) documentos de ingreso/egresos los cuales se guardarán en la base de datos para luego ser consultados por el mismo usuario o por un administrador de la empresa junto con las sucursales desde las que se ingresan dichos documentos. El objetivo es que por cada empresa se visualice un informe de operarios con su respectivo total de ingresos/egresos en un rango de fechas y también se realice el informe detallado por cada empresa. La jerarquía es un usuario master configure una empresa y añada los usuarios de dicha empresa (como mínimo a los administradores) se podría rellenar una plantilla e importar los usuarios de manera masiva. Los usuarios podrán añadir sus documentos (indicando el número de documento, tipo de documento, articulo/concepto/descripción del item, cantidad, precio unitario, total, total de impuestos aplicado puede ser IGV, IVA, ETC..). El gerente debera realizar la apertura del periodo contable y el cierre del mismo ya que cada documento ingresado pertenecerá a un determinado periodo, cuando el gerente cierre el periodo los documentos se marcarán como validados indicando el código del periodo de esta manera los documentos ya no podrán ser modificados. Antes del cierre se puede modificar/anular los documentos. El usuario podrá realizar la búsqueda de sus documentos y visualizar sus detalles. El gerente podrá visualizar todos los documentos de las sucursales y empleados de su empresa. Los informes por pantalla deberán utilizar paginación.


--------------
ENTIDADES
--------------
Usuario
Tipo de usuario (operario, master, administrador)
Impuesto
Documento (el documento tiene los siguientes estados: creado, confirmado, anulado o finalizado)
Detalle Documento
Estado de documento
Empresa
Sucursal
Informe de ingresos por operarios
Informe de ingresos/egresos por sucursales
Periodo contable

--------------
TABLAS DEL SISTEMA
--------------
Usuario
TipoUsuario (operario, master, administrador) => revisar un tutorial sobre permisos y perfiles.
Impuesto
Documento
DetalleDocumento
ValoresMaestros
Empresa
Sucursal
PeriodoContable

-------------------------
CUS
-------------------------
CUS001.00.00 Inicio de session
CUS002.00.00 Mantenimiento de usuarios
CUS003.00.00 Registrar de documentos
CUS004.00.00 Modificación de documentos
CUS005.00.00 Anulación de documentos
CUS006.00.00 Inicio de periodo contable
CUS007.00.00 Cierre de periodo contable
CUS008.00.00 Consulta e informes

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
