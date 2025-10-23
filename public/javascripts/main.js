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

eval("{__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   Calendar: () => (/* binding */ Calendar)\n/* harmony export */ });\nconst today = new Date();\nconst month = today.getMonth();\nconst year = today.getFullYear();\nconst dayOfMonth = today.getDate();\nclass Calendar {\n    constructor() {\n        for (let i = 1; i <= this.getDays(year, month); i++) {\n            let calendarObject = document.getElementById(\"calendar\");\n            let calendarDay = document.createElement(\"span\");\n            calendarDay.innerHTML = i.toString();\n            calendarDay.id = i.toString();\n            calendarDay.className = \"day\";\n            if (i == dayOfMonth) {\n                calendarDay.classList.add(\"today\");\n            }\n            calendarObject.appendChild(calendarDay);\n        }\n    }\n    getDays(year, month) {\n        return new Date(year, month + 1, 0).getDate();\n    }\n}\n\n\n//# sourceURL=webpack://bootsverleih/./app/typescripts/Calendar.ts?\n}");

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