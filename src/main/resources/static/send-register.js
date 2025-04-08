const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
};

const validateForm = (data) => {
    if (!data.properties.email || !data.properties.firstname) {
        alert("Por favor, preencha os campos obrigatórios!");
        return false;
    }
    return true;
};
const sendData = (payload, token) => {
    fetch("http://localhost:10120/contact/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": token
        },
        body: JSON.stringify(payload)
    })
    .then(response => {
        if (response.ok) {
            alert("Dados enviados com sucesso!");
            window.location.href = "/success.html";
        } else if (response.status === 401) {
            alert("Token inválido ou expirado. Faça login novamente.");
        } else if (response.status === 500) {
            alert("Erro interno no servidor. Tente novamente mais tarde.");
        } else {
            response.text().then(text => alert("Erro ao enviar dados: " + text));
        }
    })
    .catch(error => {
        console.error("Erro na requisição:", error);
        alert("Ocorreu um erro ao enviar os dados.");
    });
};

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

    if (!validateForm(contactPayload)) return;

    const authorizationToken = getCookie("Authorization");

    if (!authorizationToken) {
        alert("Token de autorização não encontrado!");
        return;
    }

    sendData(contactPayload, authorizationToken);
});