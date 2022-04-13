async function createPost() {

    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;
    const author = document.getElementById("author").value;
    const image = document.getElementById("image").value;

    const post = {
        method: "POST",
        headers: {
            "Accept": "application.json",
            "Content-Type": "application.json"
        },
        body: JSON.stringify({
            title: title,
            content: content,
            author: author,
            image: image
            })
    }
}