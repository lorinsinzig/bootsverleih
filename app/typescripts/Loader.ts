import {Calendar} from "./Calendar";
import {showAlert, showConfirm, confirmLogout} from "./Popup";

(window as any).showAlert = showAlert;
(window as any).showConfirm = showConfirm;
(window as any).confirmLogout = confirmLogout;

export class Loader {
    constructor() {

        window.addEventListener("load", () => {
            new Calendar();
        })
    }
}

new Loader();