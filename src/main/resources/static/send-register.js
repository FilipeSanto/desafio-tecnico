document.getElementById("save-button").addEventListener("click", () => {
    const contactPayload = {
        properties: {
            email: document.getElementById("email").value,
            firstname: document.getElementById("firstname").value,
            lastname: document.getElementById("lastname").value,
            website: document.getElementById("website").value,
            company: document.getElementById("company").value,
            phone: document.getElementById("phone").value
        }
    };

    const authorizationToken = localStorage.getItem("Authorization");

    if (!authorizationToken) {
        alert("Token de autorização não encontrado!");
        return;
    }

    fetch("http://localhost:10120/contact/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": authorizationToken
        },
        body: JSON.stringify(contactPayload)
    })
    .then(response => {
        if (response.ok) {
            alert("Dados enviados com sucesso!");
            window.location.href = "/success.html";
        } else {
            response.text().then(text => alert("Erro ao enviar dados: " + text));
        }
    })
    .catch(error => {
        console.error("Erro na requisição:", error);
        alert("Ocorreu um erro ao enviar os dados.");
    });
});