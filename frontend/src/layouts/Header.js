import React from 'react';

function Header({ onLogout }) {
  const headerStyle = {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: '#4A90E2',
    padding: '10px 20px',
    color: 'white',
    fontFamily: '"Segoe UI", Tahoma, Geneva, Verdana, sans-serif',
    fontSize: '20px',
    boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
  };

  const logoStyle = {
    fontWeight: 'bold',
    fontSize: '24px',
  };

  const buttonStyle = {
    backgroundColor: 'white',
    color: '#4A90E2',
    border: 'none',
    borderRadius: '4px',
    padding: '8px 12px',
    cursor: 'pointer',
    fontWeight: 'bold',
  };

  return (
    <div style={headerStyle}>
      <div style={logoStyle}>DreamSync</div>
      <button style={buttonStyle} onClick={onLogout}>로그아웃</button>
    </div>
  );
}

export default Header;
