import React from 'react';
import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Button, Form, Input} from 'antd';
import styled from "styled-components";
import {useNavigate} from "react-router-dom";
import {signUpApi} from "../../apis/signUpApi";

const SingUpContainer = () => {
    const navigate = useNavigate();

    const onFinish = async (params) => {
        try {
            console.log(params);
            await signUpApi(params);
            alert("회원가입이 완료되었습니다.");
            navigate('/login');
        } catch (e) {
            // 사용자에게 오류 메시지 표시
            alert(e.message || "회원가입 중 오류가 발생했습니다.");
        }
    };

    return (
        <Container>
            {/* 로그인 폼 영역 (하단) */}
            <LoginWrapper>
                <Form
                    name="signUp"
                    onFinish={onFinish}
                >
                    <Form.Item
                        name="username"
                        rules={[{required: true, message: 'Please input your username!'}]}>
                        <Input prefix={<UserOutlined/>} placeholder="username"/>
                    </Form.Item>
                    <Form.Item
                        name="password"
                        rules={[{required: true, message: 'Please input your password!'}]}>
                        <Input prefix={<LockOutlined/>} type="password" placeholder="Password"/>
                    </Form.Item>
                    <Form.Item
                        name="email"
                        rules={[{required: true, message: 'Please input your email!'}]}>
                        <Input prefix={<LockOutlined/>} type="email" placeholder="Email"/>
                    </Form.Item>
                    <Form.Item>
                        <Button
                            block
                            type="primary"
                            htmlType="submit"
                            style={{backgroundColor: '#1890FF', borderColor: '#1890FF'}}
                        >
                            SignUp
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

export default SingUpContainer;
