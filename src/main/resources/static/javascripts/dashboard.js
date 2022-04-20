renderPosts();

async function renderPosts() {
    const response = await fetch("https://laustrup.github.io/questions");
    const posts = await response.json();
    renderHTML(posts);
}

function renderHTML(posts) {
    document.getElementById("dashboard_content").innerHTML = ``;
    for (let i = 0; i < posts.length;i++) {
        document.getElementById("dashboard_content").innerHTML += `
            <section class="post_section">
                <h4 class="post_title">${posts[i].title}</h4>
                <h5 class="post_author">Af ${posts[i].author}</h5>
                <p class="post_content">${posts[i].content}</p>
                <h5 class="post_timestamp">Skrevet ${posts[i].timestamp}</h5>
            </section>
        `;
    }
}