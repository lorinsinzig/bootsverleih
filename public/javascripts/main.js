function bestaetigeLoeschen(id, event) {
    event.preventDefault();

    const userResponse = confirm("Bist du dir sicher?");

    if (userResponse) {
        const route = jsRoutes.controllers.BoatController.deleteboat(id);

        const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');

        fetch(route.url, {
            method: route.method,
            headers: {
                'Csrf-Token': csrfToken
            }
        })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert("Löschen fehlgeschlagen. Server hat einen Fehler gemeldet.");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Ein Netzwerkfehler ist aufgetreten.");
            });
    }
}