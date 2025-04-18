let currentMoviePage = 0;
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
            const result = await response.json();
            const data = result.content;

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

            if (data.length < moviePageSize || result.last) {
                loadMoreBtn.style.display = "none";
            } else {
                loadMoreBtn.style.display = "block";
            }

        } catch (error) {
            console.error("Error al cargar películas:", error);
        } finally {
            spinner.style.display = "none";
            loadMoreBtn.disabled = false;
        }
    }

    loadMoreMovies();

    loadMoreBtn.addEventListener("click", loadMoreMovies);
});
