import {Suspense} from "react";
import {Routes, Route, Navigate} from "react-router-dom";
import {Dashboard, Auth} from "./layouts/index.js";

function App() {
    // const { t, i18n } = useTranslation();
    //
    // // 언어를 변경하는 함수
    // const changeLanguage = (lng) => {
    //     i18n.changeLanguage(lng);
    // };

    return (
        <Suspense fallback={<div>Loading...</div>}>
            <Routes>
                <Route path="/dashboard/*" element={<Dashboard/>}/>
                <Route path="/auth/*" element={<Auth/>}/>
                <Route path="*" element={<Navigate to="/dashboard/home" replace/>}/>
            </Routes>
        </Suspense>
    );
}

export default App;
