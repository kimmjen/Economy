import {
    HomeIcon,
    ServerStackIcon,
    RectangleStackIcon,
} from "@heroicons/react/24/solid";
import {Home} from "@/pages/dashboard";
import {SignIn, SignUp} from "@/pages/auth";
import {EconomicIndicators} from "@/pages/dashboard/EconomicIndicators.jsx";

const icon = {
    className: "w-5 h-5 text-inherit",
};

export const routes = [
    {
        layout: "dashboard",
        pages: [
            {
                icon: <HomeIcon {...icon} />,
                name: "dashboard",
                path: "/home",
                element: <Home />,
                isDropdown: true,  // Enable dropdown for dashboard
                children: [
                    {
                        name: "sp500",
                        path: "/sp500",
                    },
                    {
                        name: "nasdaq100",
                        path: "/nasdaq100",
                    },
                    {
                        name: "dowjones",
                        path: "/dowjones",
                    },
                ],
            },
            {
                title: "Economic Indicators",
                layout: "dashboard",
                name: "economicIndicators",
                path: "/economicIndicators",
                element: <EconomicIndicators />,
            },
        ],
    },
    {
        title: "auth pages",
        layout: "auth",
        pages: [
            {
                icon: <ServerStackIcon {...icon} />,
                name: "sign in",
                path: "/sign-in",
                element: <SignIn />,
            },
            {
                icon: <RectangleStackIcon {...icon} />,
                name: "sign up",
                path: "/sign-up",
                element: <SignUp />,
            },
        ],
    },
];

export default routes;