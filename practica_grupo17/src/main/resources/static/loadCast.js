let currentPage = 0;
const pageSize = 10;

document.addEventListener("DOMContentLoaded", () => {
    const loadMoreBtn = document.getElementById("loadMoreBtn");
    const catalogue = document.getElementById("catalogue");
    const spinner = document.getElementById("spinner");

    async function loadMoreCast() {
        spinner.style.display = "block";
        loadMoreBtn.disabled = true;

        try {
            const response = await fetch(`/api/cast/paginated?page=${currentPage}&size=${pageSize}`);
            const data = await response.json();

            data.forEach(actor => {
                const card = document.createElement("div");
                card.className = "catalogueSingleCard fade-in-up card-hover";
                card.innerHTML = `
                    <img src="/cast/${actor.id}/image" id="imageSize" />
                    <a href="/cast/${actor.id}">${actor.name}</a>
                `;
                const list = document.createElement("ul");
                const item = document.createElement("li");
                item.appendChild(card);
                list.appendChild(item);
                catalogue.appendChild(list);
            });

            currentPage++;

            if (data.length < pageSize) {
                loadMoreBtn.style.display = "none";
            }

        } catch (error) {
            console.error("Error cargando actores:", error);
        } finally {
            spinner.style.display = "none";
            loadMoreBtn.disabled = false;
        }
    }

    loadMoreCast(); 
    loadMoreBtn.addEventListener("click", loadMoreCast);
});
