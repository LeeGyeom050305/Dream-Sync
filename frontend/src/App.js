// App.js 수정 버전
import React from 'react';
import './App.css';
import { HashRouter, Navigate, Route, Routes } from "react-router-dom";
import { login, signUp } from "./consts/uri";
import HomePage from './pages/containers/HomePage';
import CommonLayout from "./layouts/layout";
import LoginContainer from './pages/containers/loginContainer';
import SingUpContainer from './pages/containers/SingUpContainer';
import NotFound from "./pages/notFound";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { persistQueryClient } from "@tanstack/react-query-persist-client";
import { createSyncStoragePersister } from "@tanstack/query-sync-storage-persister";
import { AuthProvider } from "./AuthContext";
import TempHomePage from "./pages/TempHomePage";
import {useSelector} from "react-redux";

function App() {
    const rootUri = useSelector(state => state.root.homeUri);

    // React Query 클라이언트 생성. 24 * 24 * 60 * 60 * 1000 (24일이 최대인듯. 이 이상으로 하면 캐싱 안 됨.)
    const queryClient = new QueryClient({
        defaultOptions: {
            queries: {
                staleTime: 0, // 10초 후에 데이터가 신선하지 않음
                gcTime: 0, // 5분 후에 가비지 컬렉션
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
                <HashRouter>
                    <CommonLayout>
                        <Routes>
                            <Route path="/" element={<Navigate replace to={rootUri}/>}/>
                            <Route path={login} element={<LoginContainer/>}/>
                            <Route path={signUp} element={<SingUpContainer/>}/>
                            <Route path="/home" element={<TempHomePage />} />
                            <Route path="*" element={<NotFound />} />
                            <Route path="/main" element={<HomePage />} />
                        </Routes>
                    </CommonLayout>
                </HashRouter>
            </AuthProvider>
        </QueryClientProvider>
    );
}

export default App;
