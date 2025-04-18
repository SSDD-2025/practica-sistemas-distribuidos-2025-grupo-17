let currentMoviePage = 1; 
const moviePageSize = 5;

document.addEventListener("DOMContentLoaded", () => {
    const catalogue = document.getElementById("catalogue");
    const spinner = document.getElementById("spinner");
    const loadMoreBtn = document.getElementById("loadMoreBtn");

    async function loadMoreMovies() {
        spinner.style.display = "block";
        loadMoreBtn.disabled = true;

        try {
            const response = await fetch(`/api/movies/paginated?page=${currentMoviePage}&size=${moviePageSize}`);
            const data = await response.json();

            data.forEach(movie => {
                const card = document.createElement("div");
                card.className = "catalogueSingleCard fade-in-up card-hover";
                card.innerHTML = `
                    <img src="/movies/${movie.id}/image" id="imageSize">
                    <a href="/movies/${movie.id}">${movie.name}</a>
                `;
                const li = document.createElement("li");
                li.appendChild(card);
                const ul = document.createElement("ul");
                ul.className = "movie-list";
                ul.appendChild(li);
                catalogue.appendChild(ul);
            });

            currentMoviePage++;

            if (data.length < moviePageSize) {
                loadMoreBtn.style.display = "none";
            }

        } catch (error) {
            console.error("Error al cargar películas:", error);
        } finally {
            spinner.style.display = "none";
            loadMoreBtn.disabled = false;
        }
    }

    loadMoreBtn.addEventListener("click", loadMoreMovies);
});
