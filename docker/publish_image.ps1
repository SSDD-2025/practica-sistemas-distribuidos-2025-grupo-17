# Variables
$dockerHubUser = "tempusfugit04"
$appName = "practica_grupo17"
$version = "1.0.0"
$imageName = "${dockerHubUser}/${appName}:${version}"

# Publicar imagen
Write-Host "Publicando imagen en DockerHub: $imageName ..."
docker push $imageName
