$dockerHubUser = "tempusfugit04"
$appName = "practica_grupo17-compose"
$version = "1.0.0"
$tag = "${dockerHubUser}/${appName}:${version}"
$composeFile = "docker-compose.prod.yml"

docker login 

docker compose -f $composeFile publish $tag --with-env