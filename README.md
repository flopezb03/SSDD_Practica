# Paso 1: Configurar el Maestro (Master)
## 1. Crear el contenedor del maestro

Archivo: docker-compose.yml

Cambios:
```yaml
version: '3.8'

services:
    master:
        image: mysql:8.0
        container_name: master
        environment:
            MYSQL_ROOT_PASSWORD: password
            MYSQL_DATABASE: testDB
        volumes:
        - master-data:/var/lib/mysql
        ports:
        - "3306:3306"
        command: >
          --server-id=1
          --log-bin=mysql-bin

volumes:
  master-data:
```
Explicación:

server-id, log-bin: Pasan estos parámetros al iniciar el contenedor para configurar el maestro.
Reiniciar el servicio MySQL dentro del contenedor maestro:

Comando:
```shell
docker-compose up -d
docker exec -it master mysql -u root -p
```

Crear un usuario de replicación en el maestro:

Comando SQL:
```sql
CREATE USER 'replicator'@'%' IDENTIFIED BY 'password';
GRANT REPLICATION SLAVE ON *.* TO 'replicator'@'%';
FLUSH PRIVILEGES;
```
Obtener la posición del log binario en el maestro:

Comando SQL:
````sql
SHOW MASTER STATUS;
````

Resultado esperado: Algo como esto

Copiar código
````text
+------------------+----------+--------------+------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB |
+------------------+----------+--------------+------------------+
| mysql-bin.000001 |      154 | testDB       |                  |
+------------------+----------+--------------+------------------+
````
# Paso 2: Configurar el Esclavo (Slave)

## Crear el contenedor del esclavo

Archivo: docker-compose.yml

Cambios:
````yaml
version: '3.8'

services:
    master:
        image: mysql:8.0
        container_name: master
        environment:
          MYSQL_ROOT_PASSWORD: password
          MYSQL_DATABASE: testDB
        volumes:
        - master-data:/var/lib/mysql
        ports:
        - "3306:3306"
        command: >
         --server-id=1
         --log-bin=mysql-bin
    
    slave:
        image: mysql:8.0
        container_name: slave
        environment:
         MYSQL_ROOT_PASSWORD: password
        volumes:
        - slave-data:/var/lib/mysql
        ports:
        - "3307:3306"
        depends_on:
        - master
        command: >
         --server-id=2
         --relay-log=mysql-relay-bin

volumes:
    master-data:
    slave-data:
````


Explicación:

server-id, relay-log: Pasan estos parámetros al iniciar el contenedor para configurar el esclavo.
Reiniciar el servicio MySQL dentro del contenedor esclavo:

Comando:
````shell
docker-compose up -d
docker exec -it slave mysql -u root -p
````

Configurar el esclavo para que se conecte al maestro:

Comandos SQL (Ejecutar en el esclavo):
````sql
CHANGE MASTER TO MASTER_HOST='master', MASTER_USER='replicator', MASTER_PASSWORD='password', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=154;

START SLAVE;
````

Explicación:

MASTER_HOST: Dirección del maestro.
MASTER_USER, MASTER_PASSWORD: Credenciales del usuario de replicación.
MASTER_LOG_FILE, MASTER_LOG_POS: Especifican el archivo de log binario y la posición obtenidos del maestro.
Verificar el estado del esclavo:

Comando SQL:
````sql
SHOW SLAVE STATUS\G;
````

**Verifica** que Slave_IO_Running y Slave_SQL_Running estén ambos en Yes.

# Configuración de la Aplicación Spring Boot
Tu aplicación Spring Boot debe conectarse al maestro para las operaciones de escritura. Para las operaciones de lectura, puedes configurar el balanceo de carga para leer desde el esclavo. Aquí hay una configuración básica para conectarse al maestro:

Archivo application.properties:

````properties
spring.datasource.url=jdbc:mysql://master:3306/testDB
spring.datasource.username=root
spring.datasource.password=password
Configuración adicional: Si quieres balancear las cargas de lectura y escritura, podrías considerar usar un proxy como ProxySQL o MySQL Router.

````

# Verificación
Levantar los servicios:

````shell
docker-compose up --build
````
Probar la aplicación:

Asegúrate de que tu aplicación Spring Boot puede conectarse y realizar operaciones CRUD.
Verifica que los datos escritos en el maestro aparezcan en el esclavo.
Siguiendo estos pasos, habrás configurado una replicación master-slave en MySQL dentro de contenedores Docker. Esto distribuye tu base de datos y mejora su disponibilidad y escalabilidad.