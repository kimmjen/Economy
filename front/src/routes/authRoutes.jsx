// import { SignIn, SignUp } from '@/pages/auth';
import { ServerStackIcon, RectangleStackIcon } from '@heroicons/react/24/solid';
import {lazy} from "react";
const SignIn = lazy(() => import('@/pages/auth/SignIn'));
const SignUp = lazy(() => import('@/pages/auth/SignUp'));
const icon = {
    className: 'w-5 h-5 text-inherit',
};

export const authRoutes = [
    {
        layout: 'auth',
        pages: [
            {
                icon: <ServerStackIcon {...icon} />,
                name: 'sign in',
                path: '/sign-in',
                element: <SignIn />,
            },
            {
                icon: <RectangleStackIcon {...icon} />,
                name: 'sign up',
                path: '/sign-up',
                element: <SignUp />,
            },
        ],
    },
];
