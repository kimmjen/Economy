import {
    HomeIcon,
    ServerStackIcon,
    RectangleStackIcon,
    ListBulletIcon
} from "@heroicons/react/24/solid";
import {Home} from "@/pages/dashboard";
import {SignIn, SignUp} from "@/pages/auth";
import {EconomicIndicators} from "@/pages/dashboard/EconomicIndicators.jsx";
import {Finviz} from "@/pages/dashboard/Finviz.jsx";
import {YoutubeStream} from "@/pages/dashboard/YoutubeStream.jsx";

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
                icon: <ListBulletIcon {...icon} />,
                title: "Economic Indicators",
                layout: "dashboard",
                name: "economicIndicators",
                path: "/economicIndicators",
                element: <EconomicIndicators />,
            },
            {
                icon: <ListBulletIcon {...icon} />,
                title: "Finviz Map",
                layout: "dashboard",
                name: "finviz",
                path: "/finviz-map",
                element: <Finviz />,
            },
            {
                icon: <ListBulletIcon {...icon} />,
                title: "Youtube Stream",
                layout: "dashboard",
                name: "youtube",
                path: "/youtube",
                element: <YoutubeStream />,
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