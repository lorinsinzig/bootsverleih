export function showAlert(message: String) {
    alert(`${message}`);
}

export function showConfirm(message: string) {
    return confirm(`${message}`);
}

export function confirmLogout(originalUrl: string) {
    const message = "Wollen Sie sich wirklich ausloggen?"

    if (showConfirm(message)) {
        window.location.href = originalUrl;
    }
}

export function confirmDelete(id: number, event: any) {
    event.preventDefault();
    const message = "Wollen Sie das Boot wirklich löschen?"

    if (showConfirm(message)) {
        const route = (window as any).jsRoutes.controllers.BoatController.deleteBoat(id);

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