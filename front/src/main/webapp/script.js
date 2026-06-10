async function postRequest(url, body, genericErrorMessage) {
    let jsonResponse = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }).then(
        async function(httpResponse) {
            let jsonResponse = null;

            try {
                jsonResponse = await httpResponse.json();
            } catch {}
            
            if (!httpResponse.ok || jsonResponse?.succes === false) {
                throw new Error("HTTP POST error " + httpResponse.status + ": " + (jsonResponse?.message ?? "Erreur inconnue"));
            }
            return jsonResponse;
        }
    ).catch(
        function(error) {
            swal("Oups !", genericErrorMessage + " " + error, "error");
            return null;
        }
    );

    return jsonResponse;
}

async function getRequest(url, genericErrorMessage) {
    let jsonResponse = await fetch(url, {
        method: 'GET'
    }).then(
        function(httpResponse) {
            let jsonResponse = null;

            try {
                jsonResponse = httpResponse.json();
            } catch {}

            if (!httpResponse.ok || jsonResponse?.succes === false) {
                throw new Error("HTTP GET error " + httpResponse.status + ": " + (jsonResponse?.message ?? "Erreur inconnue"));
            }
            return jsonResponse;
        }
    ).catch(
        function(error) {
            swal("Oups !", genericErrorMessage + " " + error, "error");
            return null;
        }
    );

    return jsonResponse;
}
