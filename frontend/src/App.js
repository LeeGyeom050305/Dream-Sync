// App.js 수정 버전
import React, { useState } from 'react';
import './App.css';
import { Navigate, Route, Routes } from "react-router-dom"; // ⛔ HashRouter 제거
import { login } from "./consts/uri";
import CommonLayout from "./layouts/layout";
import LoginContainer from './pages/containers/loginContainer';
import NotFound from "./pages/notFound";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { persistQueryClient } from "@tanstack/react-query-persist-client";
import { createSyncStoragePersister } from "@tanstack/query-sync-storage-persister";
import { AuthProvider } from "./AuthContext";
import TempHomePage from "./pages/TempHomePage";

function App() {
    const [homeUri] = useState("/home");

    const queryClient = new QueryClient({
        defaultOptions: {
            queries: {
                staleTime: 0,
                gcTime: 0,
                refetchOnWindowFocus: false,
            },
        },
    });

    const persister = createSyncStoragePersister({
        storage: window.localStorage,
    });

    persistQueryClient({
        queryClient,
        persister,
    });

    return (
        <QueryClientProvider client={queryClient}>
            <AuthProvider>
                <CommonLayout>
                    <Routes>
                        <Route path="/" element={<Navigate replace to={homeUri} />} />
                        <Route path={login} element={<LoginContainer />} />
                        <Route path="/home" element={<TempHomePage />} />
                        <Route path="*" element={<NotFound />} />
                    </Routes>
                </CommonLayout>
            </AuthProvider>
        </QueryClientProvider>
    );
}

export default App;
