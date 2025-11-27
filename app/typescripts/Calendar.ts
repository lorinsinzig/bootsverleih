export class Calendar {
    calendarObject: HTMLElement;
    boatSelect: HTMLSelectElement;
    dateInput: HTMLInputElement;
    timeStartInput: HTMLInputElement;
    timeEndInput: HTMLInputElement;

    constructor() {
        this.calendarObject = document.getElementById("calendar");

        this.boatSelect = document.getElementById("boatId") as HTMLSelectElement;
        this.dateInput = document.getElementById("date") as HTMLInputElement;
        this.timeStartInput = document.getElementById("timeStartString") as HTMLInputElement;
        this.timeEndInput = document.getElementById("timeEndString") as HTMLInputElement;

        for (let i = 0; i <= 1440; i += 15) {
            let timeSection = document.createElement("div");
            timeSection.className = "section"
            timeSection.id = i.toString();

            const hours = Math.floor(i / 60);
            const minutes = i % 60;

            const formattedHours = String(hours).padStart(2, '0');
            const formattedMinutes = String(minutes).padStart(2, '0');
            timeSection.innerHTML = `${formattedHours}:${formattedMinutes}`;

            this.calendarObject.appendChild(timeSection);
        }

        const reservationUpdateHandler = () => {
            if (this.boatSelect && this.dateInput) {
                this.fetchReservations(this.boatSelect.value, this.dateInput.value);
            }
        };

        const previewUpdateHandler = () => {
            if (this.timeStartInput && this.timeEndInput) {
                this.previewReservation(this.timeStartInput.value, this.timeEndInput.value);
            }
        };

        if (this.boatSelect && this.dateInput) {
            this.boatSelect.addEventListener("change", reservationUpdateHandler);
            this.dateInput.addEventListener("change", reservationUpdateHandler);
        }

        if (this.timeStartInput && this.timeEndInput) {
            this.timeStartInput.addEventListener("change", previewUpdateHandler);
            this.timeEndInput.addEventListener("change", previewUpdateHandler);
        }

        reservationUpdateHandler();
        previewUpdateHandler();
    }

    async fetchReservations(boatId: string, date: string) {
        this.calendarObject.querySelectorAll('.section.reserved').forEach(sec => {
            sec.classList.remove('reserved');
        });

        if (!boatId || !date) {
            return;
        }

        try {
            const response = await fetch(`/reservation/${boatId}/${date}`);

            interface Reservation {
                timeStart: string;
                timeEnd: string;
            }

            const reservations: Reservation[] = await response.json();

            reservations.forEach(res => {
                const startMinutes = this.timeToMinutes(res.timeStart);
                const endMinutes = this.timeToMinutes(res.timeEnd);

                const gridStart = (Math.floor(startMinutes / 15) * 15) - 15;
                const gridEnd = (Math.floor(endMinutes / 15) * 15) + 15;

                for (let i = gridStart; i <= gridEnd; i += 15) {
                    const sectionDiv = document.getElementById(i.toString());
                    if (sectionDiv) {
                        sectionDiv.classList.add('reserved');
                    }
                }
            });
        } catch (error) {
            console.error("Error updating calendar:", error)
        }
    }

    async previewReservation(timeStart: string, timeEnd: string) {
        this.calendarObject.querySelectorAll('.section.preview').forEach(sec => {
            sec.classList.remove('preview');
        });

        try {
            const startMinutes = this.timeToMinutes(timeStart);
            const endMinutes = this.timeToMinutes(timeEnd);

            const gridStart = Math.floor(startMinutes / 15) * 15;
            const gridEnd = Math.floor(endMinutes / 15) * 15;

            const scrollTargetMinutes = Math.max(0, gridStart - 30);

            const targetElement = document.getElementById(scrollTargetMinutes.toString());

            for (let i = gridStart; i < gridEnd; i += 15) {
                const sectionDiv = document.getElementById(i.toString());
                if (sectionDiv) {
                    sectionDiv.classList.add('preview');
                }
            }

            if (targetElement) {
                this.calendarObject.scrollTop = targetElement.offsetTop;
            }
        } catch (error) {
            console.error("Error updating calendar:", error)
        }
    }

    timeToMinutes(time: string): number {
        if (!time || !time.includes(':')) {
            return 0;
        }
        const [hours, minutes] = time.split(':').map(Number);
        return (hours * 60) + minutes;
    }
}