import React from 'react';
import { Result, Button } from 'antd';
import { Link } from 'react-router-dom';

const NotFound = () => {
    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '80vh' }}>
            <Result
                status="404"
                title="404"
                subTitle="죄송합니다. 요청하신 페이지를 찾을 수 없습니다."
                extra={
                    <Link to="/">
                        <Button type="primary">홈으로 돌아가기</Button>
                    </Link>
                }
            />
        </div>
    );
};

export default NotFound;
