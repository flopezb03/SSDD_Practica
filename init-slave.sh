#!/bin/bash

# Espera hasta que el maestro esté listo
until mysql -hmaster -uroot -ppassword -e "status"; do
  >&2 echo "MySQL maestro no está listo aún - esperando..."
  sleep 5
done

# Crear la base de datos si no existe
mysql -uroot -ppassword -e "CREATE DATABASE IF NOT EXISTS testDB;"

# Obtener la información del maestro
MASTER_STATUS=$(mysql -hmaster -uroot -ppassword -e "SHOW MASTER STATUS\G")
FILE=$(echo "$MASTER_STATUS" | grep File | awk '{print $2}')
POSITION=$(echo "$MASTER_STATUS" | grep Position | awk '{print $2}')

# Configurar la replicación
mysql -uroot -ppassword <<EOF
CHANGE MASTER TO
  MASTER_HOST='master',
  MASTER_USER='replicator',
  MASTER_PASSWORD='password',
  MASTER_LOG_FILE='$FILE',
  MASTER_LOG_POS=$POSITION;
START SLAVE;
EOF
