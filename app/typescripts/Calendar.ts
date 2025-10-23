const today = new Date();
const month = today.getMonth();
const year = today.getFullYear()
const dayOfMonth = today.getDate();



export class Calendar {

    constructor() {
        for (let i = 1; i <= this.getDays(year, month); i++) {

            let calendarObject = document.getElementById("calendar");

            let calendarDay = document.createElement("span");
            calendarDay.innerHTML = i.toString();
            calendarDay.id = i.toString()
            calendarDay.className = "day"

            if (i == dayOfMonth) {
                calendarDay.classList.add("today");
            }

            calendarObject.appendChild(calendarDay);
        }
    }

    private getDays(year: number, month: number): number {
        return new Date(year, month + 1, 0).getDate();
    }
}