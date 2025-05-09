import React from 'react';

function MenuBar() {
  const menuStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5',
    padding: '15px 0',
    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
  };

  const buttonStyle = {
    margin: '0 10px',
    padding: '10px 20px',
    backgroundColor: '#4A90E2',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    fontSize: '16px',
    cursor: 'pointer',
  };

  return (
    <div style={menuStyle}>
      <button style={buttonStyle}>전체</button>
      <button style={buttonStyle}>My List</button>
      <button style={buttonStyle}>Other List</button>
    </div>
  );
}

export default MenuBar;
