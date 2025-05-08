$dockerHubUser = "fomo123"
$appName = "practica_grupo17-compose"
$version = "1.0.0"
$tag = "${dockerHubUser}/${appName}:${version}"

# Empaquetar el archivo
tar -cf docker-compose.prod.yml.tar docker-compose.prod.yml

# Importar como imagen OCI
docker import docker-compose.prod.yml.tar $tag

# Publicar
docker push $tag
