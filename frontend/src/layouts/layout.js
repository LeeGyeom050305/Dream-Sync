import React from 'react';
import {Layout} from 'antd';
// import CustomHeader from "./components/header";
import {useLocation} from "react-router-dom";
import {login} from "../consts/uri";
import styled from "styled-components";

const { Header, Content, Footer } = Layout;

const contentStyle = {
    display: "flex",
    justifyContent: "center",
    minHeight: "80vh",
    minWidth: "80vw",
    textAlign: "center",
    background: "white",
};

const CommonLayout = ({children}) => {
    const location = useLocation();
    const hideHeaderPageArr = [login];

    return (
        <Layout>
            {!hideHeaderPageArr.includes(location.pathname)}
            <Content style={contentStyle}><MainContent>
                {children}
            </MainContent>
            </Content>
        </Layout>
    );
};

const MainContent = styled.div`
    width: 100%;
    height: 100%;
    // 화면 크기의 3%는 좌우 여백으로 사용
    padding: calc(1% / 2) calc(3% / 2) calc(3% / 2) calc(3% / 2);
`;

export default CommonLayout;