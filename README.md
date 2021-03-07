# Taller de Modularización con Virtualización e Introducción a Docker y a AWS
## Descripción
En el **Taller de Modularización con Virtualización e Introducción a Docker y a AWS** se realizó primero el aprendizaje del manejo de contenedores en Docker, el cual se desplegó la primera instancia en Docker. Después, se realizó el respectivo aprendizaje de AWS para poder con el contenedor creado en Docker, desplegarlo en AWS en la máquina virtual, para así tener base para poder iniciar la tarea la cual consiste en una implementación de una arquitectura la cual consiste en un balanceador de carga en donde se usa el método de RoundRobin. Este balanceador de carga se encarga de realizar las respectivas peticiones a los LogService, los cuales se encargan de realizar la conexión con las bases de datos montada en MongoDB, que almacena todos los mensajes entrantes. En la arquitectura del programa se encuentran en total cinco contenedores, uno para el RoundRobin, tres para el LogService y uno para las bases de datos en Mongo.

## Prerrequisitos
Para la realización y ejecución tanto del programa como de las pruebas de este, se requieren ser instalados los siguientes programas:
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [Docker](https://www.docker.com/). Programa encargado de crear contenedores ligeros y portables para las aplicaciones software que puedan ejecutarse en cualquier máquina con Docker instalado, independientemente del sistema operativo que la máquina tenga por debajo, facilitando así también los despliegues.

Para asegurar que el usuario cumple con todos los prerrequisitos para poder ejecutar el programa, es necesario disponer de un Shell o Símbolo del Sistema para ejecutar los siguientes comandos para comprobar que todos los programas están instalados correctamente, para así compilar y ejecutar tanto las pruebas como el programa correctamente.

```
mvn -version
git --version
java -version
docker version
```

## Instalación
Para descargar el proyecto de GitHub, primero debemos clonar este repositorio, ejecutando la siguiente línea de comando en GIT.

```
git clone https://github.com/Skullzo/AREP-Lab5.git
```

## Ejecución
Para compilar el proyecto utilizando la herramienta Maven, nos dirigimos al directorio donde se encuentra alojado el proyecto, y dentro de este ejecutamos en un Shell o Símbolo del Sistema el siguiente comando:

```
mvn package
```
## Pruebas
Para realizar las pruebas correspondientes del proyecto utilizando la herramienta Maven, nos dirigimos al directorio donde se encuentra alojado el proyecto, y dentro de este ejecutamos en un Shell o Símbolo del Sistema el siguiente comando:

```
mvn test
```

Pruebas compiladas correctamente para el código fuente **RoundRobin**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/Prueba1.PNG)

Pruebas compiladas correctamente para el código fuente **LogService**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/Prueba2.1.PNG)

----------

### Localhost

Para probar ahora el correcto funcionamiento del Docker de manera local o localhost del programa **RoundRobin**, primero ejecutamos los siguientes comandos en orden.
```
docker build --tag arep-lab5/roundrobin .
docker images
docker run -d -p 8000:6000 --name balanceadordecarga arep-lab5/roundrobin
```
Luego de ejecutarlos en exactamente ese mismo orden, tenemos el siguiente resultado en pantalla.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/DockerRoundRobin1.PNG)

Ahora, se ejecuta el siguiente comando para comprobar que el Docker de **RoundRobin** se está ejecutando.
```
docker ps
```
Luego de ejecutar el comando, tenemos el siguiente resultado en pantalla.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/DockerRoundRobin2.PNG)

Ahora, para probar de manera local o localhost el programa **LogService** con sus tres respectivos logs, ejecutamos los siguientes comandos en orden.
```
docker build --tag arep-lab5/logservice .
docker images
```
Luego de ejecutarlos en exactamente ese mismo orden, tenemos el siguiente resultado en pantalla.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/DockerLogService1.PNG)

Ahora, para correr los tres logs en puertos diferentes, se ejecutan los siguientes comandos en orden.
```
docker run -d -p 8001:6000 --name logservice1 arep-lab5/logservice
docker run -d -p 8002:6000 --name logservice2 arep-lab5/logservice
docker run -d -p 8003:6000 --name logservice3 arep-lab5/logservice
```
Luego de ejecutarlos en exactamente ese mismo orden, tenemos el siguiente resultado en pantalla.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/DockerLogService1.2.3.PNG)

Ahora, para verificar que en la aplicación Docker se hayan desplegado con éxito los contenedores LogService y RoundRobin en sus respectivos puertos, se abre la aplicación de Docker de escritorio y se hace la verificación que todos los contenedores estén corriendo en sus respectivos puertos. Como se ve en la siguiente imagen, todos los contenedores están corriendo satisfactoriamente.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/DockerSirviendo.PNG)

Para comprobar que la página web ha sido desplegada con éxito, se ingresa en el navegador la siguiente URL: ```localhost:8000```. Luego de ingresar la URL en el navegador, se obtiene el siguiente resultado.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/DockerRunning.PNG)

Luego de ingresar tres mensajes en el espacio provisto, **Mensaje 1**, **Mensaje 2** y **Mensaje 3** respectivamente, se observa que los mensajes han sido agregados con éxito a las bases de datos en **MongoDB** y a la página de forma estática como se ve a continuación.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/DockerRunningMessages.png)

----------

### AWS

Antes de iniciar a utilizar AWS, primero se debe subir cada uno de los contenedores creados a un repositorio. Para realizar esto, primero se creó el primer repositorio en **Docker Hub** llamado ```arep-lab5```, como se ve a continuación.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS1.PNG)

Luego, se ejecutaron los siguientes comandos en orden para poder subir los contenedores.
```
docker tag arep-lab5/roundrobin skullzo/arep-lab5
docker push skullzo/arep-lab5:latest
```
Luego de ejecutar los comandos en orden, se observa que el contenedor ha sido subido satisfactoriamente al repositorio.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS2.PNG)

Para comprobar que se ha subido satisfactoriamente, se accede nuevamente al repositorio creado en Docker Hub, y se verifica que el contenedor se encuentre dentro de dicho repositorio.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS3.PNG)

Para iniciar a desplegar el contenedor en una máquina virtual alojada en AWS, primero se selecciona el tipo de máquina virtual que se utilizará, en este caso, se utilizará **Amazon Linux 2 AMI (HVM), SSD Volume Type**. Para utilizarla, se realiza clic en el botón **Seleccionar**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS4.png)

Ahora se selecciona el tipo de instancia. Para esta máquina virtual, se selecciona **t2.micro**, la cual es apta para la capa gratuita. luego de seleccionarla, se realiza clic en **Revisar y lanzar**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS5.png)

A continuación se muestra la instancia para verificar la máquina virtual que está a punto de ser lanzada. Para lanzarla, se realiza clic en el botón **Lanzar**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS6.png)

Luego, se procede a crear un nuevo par de llaves para poder acceder a la máquina virtual desde el computador en cuestión. Para esto se selecciona la opción **Crear un nuevo par de llaves** y se escribe el nombre del par de claves, que en este caso es **AREP-Lab5KeyPair**. Para descargar la llave, se realiza clic en el botón **Descargar par de llaves**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS7.png)

Después de descargar el par de llaves, ahora se procede a realizar clic en el botón **Lanzar instancias**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS8.png)

Ahora, se muestra que la instancia ha sido lanzada con éxito. Para verificar que esta ha sido lanzada, se realiza clic en el botón **Ver instancias**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS9.png)

Para conectarse a la instancia, se realiza clic en el botón **Acciones**, para posteriormente realizar clic en el botón **Conectar**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS10.png)

Para realizar la respectiva conexión con la instancia, se realiza clic en el botón **Cliente SSH**, que es el medio en el cual se realizará la conexión con la instancia.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS11.png)

Ahora, se ejecuta el SSH desde el computador con el cual se desea realizar la conexión con la instancia, y se ejecuta el siguiente comando.
```
ssh -i "AREP-Lab5KeyPair.pem" ec2-user@ec2-54-167-15-7.compute-1.amazonaws.com
```
Luego de ejecutar el comando, se muestra que la conexiLn con la instancia ha sido exitosa. Para comprobar que la instancia se encuentra corriendo en un entorno Linux, se ejecutan los comandos ```pwd``` y ```whoami``` para comprobarlo como se muestra a continuación.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS12.PNG)

Para iniciar con el procedimiento de descarga de Docker en la máquina virtual, se ejecuta el siguiente comando.
```
sudo yum update -y
```
Luego de ejecutarlo, se comprueba que se encuentre actualizado, como se ve a continuación.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS13.PNG)

Para iniciar la instalación de Docker, se ejecuta el siguiente comando.
```
sudo yum install docker
```
Luego de ejecutarlo, se inicia la descarga e instalación de Docker. Para poder iniciar con la descarga e instalación, se escribe **yes** después de haber sido ejecutado el comando.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS14.PNG)

Luego de finalizar la instalación de Docker, se ejecuta el siguiente comando para configurar el respectivo usuario en el grupo de docker para no tener que ingresar “sudo” cada vez que invoca un comando.
```
sudo usermod -a -G docker ec2-user
```
Luego de ejecutado el comando, se muestra que el comando ha sido ejecutado satisfactoriamente.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS15.PNG)

Para comprobar que el comando sirve, se sale y se ingresa nuevamente a la máquina virtual ejecutando los siguientes comandos en orden.
```
exit
ssh -i "AREP-Lab5KeyPair.pem" ec2-user@ec2-54-167-15-7.compute-1.amazonaws.com
```
Luego de haber ingresado a la máquina nuevamente, se observa que se ha realizado la conexión con la máquina satisfactoriamente.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS16.PNG)

Para iniciar el servicio de Docker en la máquina virtual, y para ver que Docker ya se encuentra en funcionamiento, se ejecutan los siguientes comandos en orden.
```
sudo service docker start
docker images
```
Como se puede observar a continuación, Docker ha sido inicializado satisfactoriamente, y para comprobarlo, se ejecuta un comando docker verificando que ha sido inicializado.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS17.PNG)

Para agregar reglas a los puertos para poder ejecutar el contenedor desde la máquina virtual, se procede a realizar clic en el botón **Seguridad**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS18.png)

Luego, en **Grupos de seguridad**, se realiza clic en el hipervínculo para poder ingresar al mismo.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS19.png)

Para agregar reglas de entrada para poder tener acceso a los puertos de los contenedores, se realiza clic en el botón **Editar reglas de entrada**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS20.png)

Para agregar una o varias reglas de entrada, se realiza clic en el botón de **Agregar regla**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS21.png)

Luego de agregar todos los puertos de los contenedores, que son: 8000, 8001, 8002, 8003 y 27017, se realiza clic en el botón **Guardar reglas**.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS22.png)

Para correr el contenedor de Docker en la máquina virtual, se ejecuta el siguiente comando.
```
docker run -d -p 8000:6000 --name balanceadordecarga skullzo/arep-lab5
```
Luego de ejecutar el comando, la máquina virtual hace los respectivos pulls al repositorio de Docker ```arep-lab5```. Para comprobar que la máquina virtual ha realizado el pull de manera exitosa, se ejecuta el comando ```docker images``` para poder visualizar el contenedor.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS23.PNG)

Ahora, para comprobar que el contenedor se encuentra activo desde la máquina virtual, ingresamos en el navegador la siguiente URL: http://ec2-54-167-15-7.compute-1.amazonaws.com:8000/. Como se puede observar, el contenedor ha sido desplegado satisfactoriamente desde la máquina virtual montada en AWS.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS24.png)

Por último, se ingresan tres mensajes para probar el funcionamiento del programa, que son **Mensaje 1**, **Mensaje 2** y **Mensaje 3** respectivamente. Como se puede observar, el programa funciona perfectamente, agregando los tres mensajes con su respectivo número del mensaje y fecha del mensaje.

![img](https://github.com/Skullzo/AREP-Lab5/blob/main/img/AWS25.png)

## Construido con
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [JUnit](https://junit.org/junit5/). Framework de Java que permite la realización de la ejecución de clases de manera controlada, para poder comprobar que los métodos realizan su cometido de forma correcta.
* [Eclipse](https://www.eclipse.org/ide/). Entorno de desarrollo integrado (IDE) utilizado en programación de computadoras. Contiene un espacio de trabajo básico y un sistema de complementos extensible para personalizar el entorno. Eclipse está escrito principalmente en Java y su uso principal es para desarrollar aplicaciones Java, pero también se puede usar para desarrollar aplicaciones en otros lenguajes de programación a través de complementos (plug-ins).
* [Java](https://www.oracle.com/java/). Lenguaje de programación de propósito general, es decir, que sirve para muchas cosas, para web, servidores, aplicaciones móviles, entre otros. Java también es un lenguaje orientado a objetos, y con un fuerte tipado de variables.
* [Docker](https://www.docker.com/). Programa encargado de crear contenedores ligeros y portables para las aplicaciones software que puedan ejecutarse en cualquier máquina con Docker instalado, independientemente del sistema operativo que la máquina tenga por debajo, facilitando así también los despliegues.
* [AWS](https://aws.amazon.com/es/). Conjunto de herramientas y servicios de cloud computing de Amazon, que engloba una gran cantidad de servicios para poder realizar distintos tipos de actividades en la nube. Desde almacenamiento a la gestión de instancias, imágenes virtuales, desarrollo de aplicaciones móviles, etc.
* [CircleCI](https://circleci.com/). Plataforma moderna de integración continua y entrega continua (CI / CD) que se encarga de automatizar la construcción, pruebas e implementación de software.

     [![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://app.circleci.com/pipelines/github/Skullzo/AREP-Lab5)

## Autor
[Alejandro Toro Daza](https://github.com/Skullzo)
## Licencia & Derechos de Autor
**©** Alejandro Toro Daza, Estudiante de Ingeniería de Sistemas de la [Escuela Colombiana de Ingeniería Julio Garavito](https://www.escuelaing.edu.co/es/).

Licencia bajo la [GNU General Public License](https://github.com/Skullzo/AREP-Lab5/blob/main/LICENSE).
