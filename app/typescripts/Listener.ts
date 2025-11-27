export function highlightActiveLink() {
    const links = document.querySelectorAll('.link');
    const currentUrl = window.location.pathname.replace(/\/+$/, "") || "/";

    links.forEach((link) => {
        const anchor = link as HTMLAnchorElement;
        const url = anchor.pathname.replace(/\/+$/, "") || "/";

        if (url === currentUrl) {
            anchor.classList.add('active');
        } else {
            anchor.classList.remove('active');
        }
    });
}