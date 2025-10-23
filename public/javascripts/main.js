/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ "./app/typescripts/Calendar.ts":
/*!*************************************!*\
  !*** ./app/typescripts/Calendar.ts ***!
  \*************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("{__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   Calendar: () => (/* binding */ Calendar)\n/* harmony export */ });\nvar __awaiter = (undefined && undefined.__awaiter) || function (thisArg, _arguments, P, generator) {\n    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }\n    return new (P || (P = Promise))(function (resolve, reject) {\n        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }\n        function rejected(value) { try { step(generator[\"throw\"](value)); } catch (e) { reject(e); } }\n        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }\n        step((generator = generator.apply(thisArg, _arguments || [])).next());\n    });\n};\nclass Calendar {\n    constructor() {\n        this.calendarObject = document.getElementById(\"calendar\");\n        if (!this.calendarObject) {\n            console.error(\"Calendar element not found.\");\n            return;\n        }\n        this.boatSelect = document.getElementById(\"boatId\");\n        this.dateInput = document.getElementById(\"date\");\n        this.timeStartInput = document.getElementById(\"timeStartString\");\n        this.timeEndInput = document.getElementById(\"timeEndString\");\n        for (let i = 0; i <= 1440; i += 15) {\n            let timeSection = document.createElement(\"div\");\n            timeSection.className = \"section\";\n            timeSection.id = i.toString();\n            const hours = Math.floor(i / 60);\n            const minutes = i % 60;\n            const formattedHours = String(hours).padStart(2, '0');\n            const formattedMinutes = String(minutes).padStart(2, '0');\n            timeSection.innerHTML = `${formattedHours}:${formattedMinutes}`;\n            this.calendarObject.appendChild(timeSection);\n        }\n        const reservationUpdateHandler = () => {\n            if (this.boatSelect && this.dateInput) {\n                this.fetchReservations(this.boatSelect.value, this.dateInput.value);\n            }\n        };\n        const previewUpdateHandler = () => {\n            if (this.timeStartInput && this.timeEndInput) {\n                this.previewReservation(this.timeStartInput.value, this.timeEndInput.value);\n            }\n        };\n        if (this.boatSelect && this.dateInput) {\n            this.boatSelect.addEventListener(\"change\", reservationUpdateHandler);\n            this.dateInput.addEventListener(\"change\", reservationUpdateHandler);\n        }\n        if (this.timeStartInput && this.timeEndInput) {\n            this.timeStartInput.addEventListener(\"change\", previewUpdateHandler);\n            this.timeEndInput.addEventListener(\"change\", previewUpdateHandler);\n        }\n        reservationUpdateHandler();\n        previewUpdateHandler();\n    }\n    fetchReservations(boatId, date) {\n        return __awaiter(this, void 0, void 0, function* () {\n            this.calendarObject.querySelectorAll('.section.reserved').forEach(sec => {\n                sec.classList.remove('reserved');\n            });\n            if (!boatId || !date) {\n                return;\n            }\n            try {\n                const response = yield fetch(`/reservation/${boatId}/${date}`);\n                if (!response.ok) {\n                    throw new Error(`Failed to fetch: ${response.statusText}`);\n                }\n                const reservations = yield response.json();\n                reservations.forEach(res => {\n                    const startMinutes = this.timeToMinutes(res.timeStart);\n                    const endMinutes = this.timeToMinutes(res.timeEnd);\n                    const gridStart = (Math.floor(startMinutes / 15) * 15) - 15;\n                    for (let i = gridStart; i < endMinutes + 15; i += 15) {\n                        const sectionDiv = document.getElementById(i.toString());\n                        if (sectionDiv) {\n                            sectionDiv.classList.add('reserved');\n                        }\n                    }\n                });\n            }\n            catch (error) {\n                console.error(\"Error updating calendar:\", error);\n            }\n        });\n    }\n    previewReservation(timeStart, timeEnd) {\n        return __awaiter(this, void 0, void 0, function* () {\n            this.calendarObject.querySelectorAll('.section.preview').forEach(sec => {\n                sec.classList.remove('preview');\n            });\n            if (!timeStart || !timeEnd) {\n                return;\n            }\n            try {\n                const startMinutes = this.timeToMinutes(timeStart);\n                const endMinutes = this.timeToMinutes(timeEnd);\n                const gridStart = Math.floor(startMinutes / 15) * 15;\n                const scrollTargetMinutes = Math.max(0, gridStart - 30);\n                const targetElement = document.getElementById(scrollTargetMinutes.toString());\n                for (let i = gridStart; i < endMinutes; i += 15) {\n                    const sectionDiv = document.getElementById(i.toString());\n                    if (sectionDiv) {\n                        sectionDiv.classList.add('preview');\n                    }\n                }\n                if (targetElement) {\n                    this.calendarObject.scrollTop = targetElement.offsetTop;\n                }\n            }\n            catch (error) {\n                console.error(\"Error updating calendar:\", error);\n            }\n        });\n    }\n    timeToMinutes(time) {\n        if (!time || !time.includes(':')) {\n            return 0;\n        }\n        const [hours, minutes] = time.split(':').map(Number);\n        return (hours * 60) + minutes;\n    }\n}\n\n\n//# sourceURL=webpack://bootsverleih/./app/typescripts/Calendar.ts?\n}");

/***/ }),

/***/ "./app/typescripts/Loader.ts":
/*!***********************************!*\
  !*** ./app/typescripts/Loader.ts ***!
  \***********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

eval("{__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   Loader: () => (/* binding */ Loader)\n/* harmony export */ });\n/* harmony import */ var _Calendar__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./Calendar */ \"./app/typescripts/Calendar.ts\");\n\nclass Loader {\n    constructor() {\n        window.addEventListener(\"load\", () => {\n            new _Calendar__WEBPACK_IMPORTED_MODULE_0__.Calendar();\n        });\n    }\n}\nnew Loader();\n\n\n//# sourceURL=webpack://bootsverleih/./app/typescripts/Loader.ts?\n}");

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
/******/ 	
/******/ 	// startup
/******/ 	// Load entry module and return exports
/******/ 	// This entry module can't be inlined because the eval devtool is used.
/******/ 	var __webpack_exports__ = __webpack_require__("./app/typescripts/Loader.ts");
/******/ 	
/******/ })()
;