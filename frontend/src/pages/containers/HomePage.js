import React from 'react';
import Header from '../../layouts/Header';
import MenuBar from '../../layouts/MenuBar';
import PostList from '../../layouts/PostList'; 

function MainPage() {
  const handleLogout = () => {
    console.log('로그아웃되었습니다.');
  };

  const layoutStyle = {
    display: 'flex',
    flexDirection: 'row',
    height: '100vh',
    padding: '20px',
    boxSizing: 'border-box',
    gap: '20px', // 좌측, 우측 박스 사이에 20px 여백 추가
  };

  // 좌측 메인 콘텐츠에 회색 박스 스타일 추가
  const leftPaneStyle = {
    flex: 7, // 7비율
    backgroundColor: '#f5f5f5', // 회색 배경
    padding: '20px',
    borderRadius: '8px', // 둥근 모서리
    boxShadow: '0 1px 3px rgba(0,0,0,0.1)', // 살짝 그림자 효과
  };

  const rightPaneStyle = {
    flex: 3, // 3비율
    backgroundColor: '#f9f9f9',
    padding: '20px',
    borderRadius: '8px', // 둥근 모서리
    boxShadow: '0 1px 3px rgba(0,0,0,0.1)', // 살짝 그림자 효과
  };

  const boxStyle = {
    backgroundColor: '#fff',
    padding: '15px',
    boxShadow: '0 1px 3px rgba(0,0,0,0.1)',
    borderRadius: '8px',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: '10px',
  };

  const buttonStyle = {
    width: '100%',
    padding: '10px',
    backgroundColor: '#4A90E2',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '14px',
  };

  const topBoxStyle = {
    ...boxStyle,
    marginBottom: '30px', // 두 박스 사이에 30px 여백 추가
  };

  return (
    <div style={{ height: '100vh', margin: 0 }}>
      <Header onLogout={handleLogout} />
      <MenuBar />
      <div style={layoutStyle}>
        <div style={leftPaneStyle}>
          <h2>Bucket List</h2>
          <p>버킷리스트를 공유해봐요!</p>
          <div style={{ flex: 7, padding: '20px' }}>
            <h2>📄 게시글 목록 </h2>
            <PostList /> {/* 게시글 목록 */}
          </div>
        </div>
        <div style={rightPaneStyle}>
          <h2>📌 인기 태그</h2>
          <p>실시간으로 인기있는 주제를 확인해보세요!</p>
          <div style={topBoxStyle}>
            <h2> 이번 주 인기 태그 </h2>
            <button style={buttonStyle}>Tag 1</button>
            <button style={buttonStyle}>Tag 2</button>
            <button style={buttonStyle}>Tag 3</button>
          </div>
          <div style={boxStyle}>
            <h2> 지난 주 인기 태그 </h2>
            <button style={buttonStyle}>Tag A</button>
            <button style={buttonStyle}>Tag B</button>
            <button style={buttonStyle}>Tag C</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default MainPage;
