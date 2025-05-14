import React, { useState } from 'react';

function MenuBar() {
  const [selected, setSelected] = useState('전체');

  const menus = ['전체', 'My List', 'Other List'];

  const wrapperStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: '20px 0',
    backgroundColor: '#f9fafb',
  };

  const containerStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: '10px 20px',
    borderRadius: '50px', // 타원 모양으로 설정
    backgroundColor: '#e5e7eb', // 배경색
  };

  const buttonStyle = (isActive) => ({
    width: '120px', // 가로 길이를 확장
    height: '50px', // 세로 길이를 줄여서 타원형 모양으로 설정
    borderRadius: '30px', // 둥근 모서리로 타원형 만들기
    backgroundColor: isActive ? '#2563eb' : '#e5e7eb',
    color: isActive ? '#ffffff' : '#1f2937',
    border: 'none',
    fontSize: '14px',
    fontWeight: 600,
    margin: '0 12px',
    cursor: 'pointer',
    transition: 'all 0.2s ease-in-out',
    boxShadow: isActive ? '0 4px 10px rgba(37, 99, 235, 0.2)' : 'none',
  });

  return (
    <div style={wrapperStyle}>
      <div style={containerStyle}>
        {menus.map((menu) => (
          <button
            key={menu}
            style={buttonStyle(selected === menu)}
            onClick={() => setSelected(menu)}
          >
            {menu}
          </button>
        ))}
      </div>
    </div>
  );
}

export default MenuBar;
