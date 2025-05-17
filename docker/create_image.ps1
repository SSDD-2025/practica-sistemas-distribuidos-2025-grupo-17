#Variables
$dockerfile = "./docker/Dockerfile"
$dockerHubUser = "tempusfugit04"
$appName = "practica_grupo17"
$version = "1.0.0"
$imageName = "${dockerHubUser}/${appName}:${version}"

# Creating image using Dockerfile
Write-Host "Construyendo imagen Docker: $imageName ..."
docker build -f $dockerfile -t $imageName .