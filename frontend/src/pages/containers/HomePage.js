import React from 'react';
import Header from '../../layouts/Header';
import MenuBar from '../../layouts/MenuBar';
import PostList from '../../layouts/PostList';

function MainPage() {
  const handleLogout = () => {
    console.log('로그아웃되었습니다.');
  };

  const containerStyle = {
    backgroundColor: '#f9fafb', 
    minHeight: '100vh',
    fontFamily: 'Pretendard, sans-serif',
    margin: 0, // 여백 제거
    padding: 0, // padding 제거
  };

  const layoutStyle = {
    display: 'flex',
    flexDirection: 'row',
    maxWidth: '1200px',
    margin: '0 auto',
    padding: '40px 20px',
    gap: '24px',
  };

  const leftPaneStyle = {
    flex: 7,
    backgroundColor: '#fff',
    borderRadius: '12px',
    boxShadow: '0 2px 8px rgba(0,0,0,0.03)',
    padding: '32px',
    display: 'flex',
    flexDirection: 'column',
    gap: '16px',
  };

  const rightPaneStyle = {
    flex: 3,
    backgroundColor: '#fff',
    borderRadius: '12px',
    boxShadow: '0 2px 8px rgba(0,0,0,0.03)',
    padding: '32px',
    display: 'flex',
    flexDirection: 'column',
    gap: '32px',
  };

  const sectionTitle = {
    fontSize: '20px',
    fontWeight: 600,
    color: '#1c1c1e',
    marginBottom: '4px',
    padding: '8px 16px', // 버튼 느낌을 주기 위해 padding 추가
    borderRadius: '8px', // 둥근 모서리
    backgroundColor: '#4A90E2', // 버튼 배경 색
    color: 'white', // 버튼 텍스트 색
    cursor: 'pointer', // 마우스 올리면 클릭할 수 있다는 느낌
    display: 'inline-block', // 버튼처럼 나타나게
  };

  const sectionSub = {
    fontSize: '14px',
    color: '#6e6e73',
  };

  const tagBoxStyle = {
    display: 'flex',
    flexDirection: 'column',
    gap: '12px',
  };

  const tagButtonStyle = {
    textAlign: 'left',
    padding: '10px 16px',
    border: '1px solid #e6e6ea',
    borderRadius: '8px',
    backgroundColor: '#f5f7fa',
    fontSize: '14px',
    fontWeight: 500,
    color: '#1a1a1a',
    cursor: 'pointer',
  };

  return (
    <div style={containerStyle}>
      <Header onLogout={handleLogout} />
      <MenuBar />
      <div style={layoutStyle}>
        <div style={leftPaneStyle}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '8px' }}>
            <h2 style={sectionTitle}>📋 버킷리스트</h2>
            <input
              type="text"
              placeholder="버킷 검색..."
              style={{
                padding: '12px 20px',
                borderRadius: '6px',
                border: '1px solid #d1d5db',
                fontSize: '14px',
                outline: 'none',
                width: '300px', 
              }}
            />
          </div>
          <p style={sectionSub}>다른 유저들의 목표를 확인하고 공유해보세요!</p>
          <PostList />
        </div>
        <div style={rightPaneStyle}>
          <div>
            <h2 style={sectionTitle}>🔥 이번 주 인기 태그</h2>
            <p style={sectionSub}>지금 가장 핫한 키워드를 만나보세요</p>
            <div style={tagBoxStyle}>
              <button style={tagButtonStyle}># 여행</button>
              <button style={tagButtonStyle}># 자기계발</button>
              <button style={tagButtonStyle}># 금융자유</button>
            </div>
          </div>
          <div>
            <h2 style={sectionTitle}>🕒 지난 주 인기 태그</h2>
            <p style={sectionSub}>지난 주 뜨거웠던 키워드를 만나보세요</p>
            <div style={tagBoxStyle}>
              <button style={tagButtonStyle}># 운동</button>
              <button style={tagButtonStyle}># 영어공부</button>
              <button style={tagButtonStyle}># 독서습관</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MainPage;
