import React, {useEffect} from 'react';
import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Button, Form, Input} from 'antd';
import styled from "styled-components";
import {useNavigate} from "react-router-dom";
import {loginApi} from "../../apis/userApi";
import {useAuth} from "../../AuthContext";
// import {useSelector} from "react-redux";

const LoginContainer = () => {
    const navigate = useNavigate();
    const {isAuthenticated} = useAuth();
    // const rootUri = useSelector(state => state.root.homeUri);

    const onFinish = async (loginInfo) => {
        try {
            await loginApi(loginInfo);

            navigate('/');
        } catch (e) {
            console.error(e);
        }
    };

    // 로그인되어 있으면 홈으로 자동 리디렉트
    // useEffect(() => {
    //     if (isAuthenticated()) {
    //         navigate(rootUri);
    //     }
    // }, [isAuthenticated, navigate]);
    useEffect(() => {
        if (isAuthenticated()) {
            navigate('/home');
        }
    }, [isAuthenticated, navigate]);


    return (
        <Container>
            {/* 배경 이미지 영역 (상단) */}
            <BackgroundImage/>

            {/* 로그인 폼 영역 (하단) */}
            <LoginWrapper>
                <Form
                    name="login"
                    onFinish={onFinish}
                >
                    <Form.Item
                        name="username"
                        rules={[{required: true, message: 'Please input your username!'}]}>
                        <Input prefix={<UserOutlined/>} placeholder="Username"/>
                    </Form.Item>
                    <Form.Item
                        name="password"
                        rules={[{required: true, message: 'Please input your password!'}]}>
                        <Input prefix={<LockOutlined/>} type="password" placeholder="Password"/>
                    </Form.Item>
                    <Form.Item>
                        <Button
                            block
                            type="primary"
                            htmlType="submit"
                            style={{backgroundColor: '#1890FF', borderColor: '#1890FF'}}
                        >
                            Log In
                        </Button>
                    </Form.Item>
                </Form>
            </LoginWrapper>
        </Container>
    );
};

// 전체 컨테이너 스타일 (상하로 나누지 않고 이미지 위에 로그인 폼 배치)
const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: flex-start; // 로그인 폼을 수직 방향으로 중앙에 배치
    height: 95vh; // 화면 전체 높이 사용
    position: relative; // 절대 위치 배치를 위해 relative로 설정
`;

// 이미지 배경 스타일
const BackgroundImage = styled.div`
    width: 100%;
    height: 100%; // 화면 전체 크기
    background: no-repeat center center;
    background-size: contain; // 이미지를 화면 크기에 맞게 축소, 비율 유지
    background-position: center center; // 이미지 중앙 정렬
    position: absolute; // 배경 이미지를 절대 위치로 배치
    top: 0;
    left: 0;
`;

// 로그인 폼을 담는 스타일
const LoginWrapper = styled.div`
    position: absolute;
    top: 60%; // 이미지 위에 폼을 30% 지점에 배치 (이미지 상단에서 살짝 내려오게 설정)
    width: 100%;
    max-width: 400px; // 로그인 폼 최대 너비 설정
    background-color: #fff;
    padding: 40px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    z-index: 10; // 로그인 폼이 이미지 위로 보이도록 설정

    @media (max-width: 768px) {
        width: 100%;
        top: 20%;  // 작은 화면에서는 더 위로 올라가게 설정
    }
`;

export default LoginContainer;
