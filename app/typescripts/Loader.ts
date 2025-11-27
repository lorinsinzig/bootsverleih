import {Calendar} from "./Calendar";
import {showAlert, showConfirm, confirmLogout} from "./Popup";
import {highlightActiveLink} from "./Listener";

(window as any).showAlert = showAlert;
(window as any).showConfirm = showConfirm;
(window as any).confirmLogout = confirmLogout;
(window as any).highlightActiveLink = highlightActiveLink;

export class Loader {
    constructor() {

        // If it needs to be executed on reload
        window.addEventListener("load", () => {
            highlightActiveLink();
            new Calendar();
        })
    }
}

new Loader();