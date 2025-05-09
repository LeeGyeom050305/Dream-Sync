import React from 'react';
import { Link } from 'react-router-dom';

function PostList() {
  // 예시 게시글 데이터
  const posts = [
    { id: 1, title: '게시글 1' },
    { id: 2, title: '게시글 2' },
    { id: 3, title: '게시글 3' },
    { id: 4, title: '게시글 4' },
    { id: 5, title: '게시글 5' },
    // 추가 게시글들
  ];

  const listStyle = {
    display: 'flex',
    flexDirection: 'column',
    gap: '10px',
    height: '100%', // 부모 요소 높이 꽉 채우기
  };

  const buttonStyle = {
    flex: 1, // 각 버튼이 세로로 균등하게 크기 조정
    padding: '10px',
    backgroundColor: '#4A90E2',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '16px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    width: '100%',
  };

  return (
    <div style={listStyle}>
      {posts.map((post) => (
        <Link key={post.id} to={`/post/${post.id}`} style={{ textDecoration: 'none' }}>
          <button style={buttonStyle}>{post.title}</button>
        </Link>
      ))}
    </div>
  );
}

export default PostList;
