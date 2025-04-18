let currentReviewPage = 1;
const reviewPageSize = 5;

document.addEventListener("DOMContentLoaded", () => {
    const movieId = window.location.pathname.split("/")[2];
    const reviewContainer = document.getElementById("reviewContainer");
    const spinner = document.getElementById("reviewSpinner");
    const loadMoreBtn = document.getElementById("loadMoreReviewsBtn");

    const reviewItems = document.querySelectorAll("#reviewContainer .review-item");
    reviewItems.forEach((item, index) => {
        if (index >= reviewPageSize) {
            item.style.display = "none";
        }
    });

    if (reviewItems.length <= reviewPageSize) {
        loadMoreBtn.style.display = "none";
    }

    async function loadMoreReviews() {
        spinner.style.display = "block";
        loadMoreBtn.disabled = true;

        try {
            const res = await fetch(`/api/reviews/movie/${movieId}/paginated?page=${currentReviewPage}&size=${reviewPageSize}`);
            const data = await res.json();

            data.forEach(review => {
                const li = document.createElement("li");
                li.classList.add("review-item");
                li.innerHTML = `
                    <p id="infoText">${review.author.username} : ${review.title}</p>
                    <div id="reviewBox"><p>${review.text}</p></div>
                `;
                reviewContainer.appendChild(li);
            });

            currentReviewPage++;

            if (data.length < reviewPageSize) {
                loadMoreBtn.style.display = "none";
            }
        } catch (error) {
            console.error("Error cargando reseñas:", error);
        } finally {
            spinner.style.display = "none";
            loadMoreBtn.disabled = false;
        }
    }

    loadMoreBtn.addEventListener("click", loadMoreReviews);
});
