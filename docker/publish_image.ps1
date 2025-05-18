$dockerHubUser = "tempusfugit04"
$appName = "practica_grupo17"
$version = "1.0.0"
$imageName = "${dockerHubUser}/${appName}:${version}"


Write-Host "Publicando imagen Docker: $imageName ..."
try {
    docker push $imageName
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Imagen publicada correctamente: $imageName" -ForegroundColor Green
    } else {
        Write-Host "Error al publicar la imagen" -ForegroundColor Red
        exit 1
    }
} catch {
    Write-Host "Excepción al publicar la imagen: $_" -ForegroundColor Red
    exit 1
}