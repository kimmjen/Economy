import {Routes, Route} from "react-router-dom";
import {Sidenav} from "@/widgets/layout/Sidenav.jsx";
import {useMaterialTailwindController} from "@/context/index.jsx";
import routes from "@/routes/Routes.jsx";
import {DashboardNavbar} from "@/widgets/layout/DashboardNavbar.jsx";
import {DateProvider} from "@/context/DateContext.jsx";

export function Dashboard() {

    const [controller, dispatch] = useMaterialTailwindController();
    const {sidenavType} = controller;
    return (
        <div className="min-h-screen bg-blue-gray-50/50">
            <Sidenav
                routes={routes}
                brandImg={
                    sidenavType === "dark"
                    // sidenavType === "dark" ? "/img/logo-ct.png" : "/img/logo-ct-dark.png"
                }
            />
            <div className="p-4 xl:ml-80">
                <DateProvider>
                    <DashboardNavbar/>
                    <Routes>
                        {routes.map(
                            ({layout, pages}) =>
                                layout === "dashboard" &&
                                pages.map(({path, element}) => (
                                    <Route exact path={path} element={element}/>
                                ))
                        )}
                    </Routes>
                </DateProvider>
            </div>
        </div>
    );
};

Dashboard.displayName = "/src/layout/dashboard.jsx";

export default Dashboard;
