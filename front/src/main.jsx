// import {StrictMode} from 'react'
// import {createRoot} from 'react-dom/client'
// import App from './App.jsx'
// import './index.css'
// import "./i18n/i18n.js"; // i18n 설정 가져오기
//
// import './assets/css/tailwind.css';
// createRoot(document.getElementById('root')).render(
//     <StrictMode>
//         <App/>
//     </StrictMode>,
// )

/**
 =========================================================
 * Material Tailwind Dashboard React - v2.1.0
 =========================================================
 * Product Page: https://www.creative-tim.com/product/material-tailwind-dashboard-react
 * Copyright 2023 Creative Tim (https://www.creative-tim.com)
 * Licensed under MIT (https://github.com/creativetimofficial/material-tailwind-dashboard-react/blob/main/LICENSE.md)
 * Coded by Creative Tim
 =========================================================
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 */
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { ThemeProvider } from "@material-tailwind/react";
import { MaterialTailwindControllerProvider } from "@/context";
// import "../public/css/tailwind.css";

import './assets/css/tailwind.css';
ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <BrowserRouter>
            <ThemeProvider>
                <MaterialTailwindControllerProvider>
                    <App />
                </MaterialTailwindControllerProvider>
            </ThemeProvider>
        </BrowserRouter>
    </React.StrictMode>
);