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