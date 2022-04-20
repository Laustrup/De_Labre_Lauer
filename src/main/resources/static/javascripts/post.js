async function createPost() {

    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;
    const author = document.getElementById("author").value;
    // TODO
    const image = document.getElementById("image").value;

    const post = {
        method: "POST",
        headers: {
            "Accept": "application.json",
            "Content-Type": "application.json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Headers": "X-Requested-With, Content-Type, Authorization"
        },
        body: JSON.stringify({
            title: title,
            content: content,
            author: author,
            imageLocation: image
            })
    }
    await fetch("https://laustrup.github.io/laupost",post);
    renderPosts();
}