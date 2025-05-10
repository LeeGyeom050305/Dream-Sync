// src/pages/TempHomePage.js

import React from 'react';
import styled from 'styled-components';

const TempHomePage = () => {
    return (
        <Wrapper>
            <Message>홈 화면은 현재 준비 중입니다 🛠️</Message>
            <SubMessage>조금만 기다려 주세요!</SubMessage>
        </Wrapper>
    );
};

const Wrapper = styled.div`
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #f9f9f9;
`;

const Message = styled.h1`
    font-size: 2rem;
    color: #333;
`;

const SubMessage = styled.p`
    font-size: 1rem;
    color: #777;
    margin-top: 10px;
`;

export default TempHomePage;
