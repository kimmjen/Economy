import i18n from "i18next";
import { initReactI18next } from "react-i18next";

// 번역 데이터
const resources = {
    en: {
        translation: {
            welcome: "Welcome to the Dashboard",
            settings: "Settings",
            logout: "Logout",
        },
    },
    ko: {
        translation: {
            welcome: "대시보드에 오신 것을 환영합니다",
            settings: "설정",
            logout: "로그아웃",
        },
    },
};

i18n.use(initReactI18next).init({
    resources,
    lng: "en", // 기본 언어 설정
    fallbackLng: "en", // 기본 언어를 찾지 못할 때 사용할 언어
    interpolation: {
        escapeValue: false, // React는 이미 자동으로 XSS 보호를 하고 있음
    },
});

export default i18n;
