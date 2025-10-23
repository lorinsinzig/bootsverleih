import {Calendar} from "./Calendar";

export class Loader {
    constructor() {

        window.addEventListener("load", () => {
            new Calendar();
        })
    }
}

new Loader();