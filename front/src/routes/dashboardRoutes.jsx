// import { Home,
//     // Profile, Tables, Notifications, Editor
// } from '@/pages/dashboard';
import { lazy } from 'react';

// 각 페이지를 동적으로 로드하도록 설정 (Lazy Loading)
const Home = lazy(() => import('@/pages/dashboard/Home'));
import { HomeIcon
    // , UserCircleIcon, TableCellsIcon, InformationCircleIcon
} from '@heroicons/react/24/solid';

const icon = {
    className: 'w-5 h-5 text-inherit',
};

export const dashboardRoutes = [
    {
        layout: 'dashboard',
        pages: [
            {
                icon: <HomeIcon {...icon} />,
                name: 'dashboard',
                path: '/home',
                element: <Home />,
            },
            // {
            //     icon: <UserCircleIcon {...icon} />,
            //     name: 'profile',
            //     path: '/profile',
            //     element: <Profile />,
            // },
            // {
            //     icon: <TableCellsIcon {...icon} />,
            //     name: 'tables',
            //     path: '/tables',
            //     element: <Tables />,
            // },
            // {
            //     icon: <InformationCircleIcon {...icon} />,
            //     name: 'notifications',
            //     path: '/notifications',
            //     element: <Notifications />,
            // },
            // {
            //     icon: <InformationCircleIcon {...icon} />,
            //     name: 'editor',
            //     path: '/editor',
            //     element: <Editor />,
            // },
        ],
    },
];
