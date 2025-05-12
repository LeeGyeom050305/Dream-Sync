import React, {useEffect} from 'react';
import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Button, Form, Input} from 'antd';
import styled from "styled-components";
import {useNavigate} from "react-router-dom";
import {loginApi} from "../../apis/userApi";
import {useAuth} from "../../AuthContext";
import {useSelector} from "react-redux";

const LoginContainer = () => {
    const navigate = useNavigate();
    const {isAuthenticated} = useAuth();
    const rootUri = useSelector(state => state.root.homeUri);

    const onFinish = async (loginInfo) => {
        try {
            await loginApi(loginInfo);

            navigate('/home');
        } catch (e) {
            console.error(e);
        }
    };

    // 로그인되어 있으면 홈으로 자동 리디렉트
    useEffect(() => {
        if (isAuthenticated()) {
            navigate(rootUri);
        }
    }, [isAuthenticated, navigate, rootUri]);

    return (
        <Container>
            {/* 로그인 폼 영역 (하단) */}
            <LoginWrapper>
                <Form
                    name="login"
                    onFinish={onFinish}
                >
                    <Form.Item
                        name="username"
                        rules={[{required: true, message: 'Please input your username!'}]}>
                        <Input prefix={<UserOutlined/>} placeholder="UserName"/>
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

// 전체 컨테이너 스타일
const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    position: relative;
    background-color: #f0f2f5; // 💡연한 회색 배경
`;

// 로그인 폼을 담는 스타일
const LoginWrapper = styled.div`
    position: relative;
    z-index: 10;
    max-width: 400px;
    width: 100%;
    background-color: #fff;
    padding: 40px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
`;

export default LoginContainer;
