import React from 'react';
import { Link } from 'react-router-dom';

function PostList() {
  // 예시 게시글 데이터 (제목, 작성자, 조회수, 좋아요, 주제)
  const posts = [
    { id: 1, title: '게시글 1', author: '작성자1', views: 120, likes: 15, topic: '주제1' },
    { id: 2, title: '게시글 2', author: '작성자2', views: 80, likes: 20, topic: '주제2' },
    { id: 3, title: '게시글 3', author: '작성자3', views: 200, likes: 50, topic: '주제3' },
    { id: 4, title: '게시글 4', author: '작성자4', views: 150, likes: 35, topic: '주제4' },
    { id: 5, title: '게시글 5', author: '작성자5', views: 300, likes: 70, topic: '주제5' },
  ];

  const listStyle = {
    display: 'flex',
    flexDirection: 'column',
    gap: '20px', // 항목 간격 조정
    padding: '10px 0',
  };

  const postStyle = {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center', // 세로 정렬
    backgroundColor: '#ffffff',
    color: '#333333',
    border: '1px solid #e2e8f0',
    borderRadius: '8px',
    padding: '15px',
    boxShadow: '0 2px 5px rgba(0,0,0,0.1)',
    fontSize: '16px',
    transition: 'all 0.3s ease',
  };

  const postInfoStyle = {
    display: 'flex',
    gap: '20px', // 정보 항목 간격
    fontSize: '14px',
    color: '#6b7280',
  };

  const buttonStyle = {
    padding: '8px 16px',
    backgroundColor: '#4A90E2',
    color: 'white',
    border: 'none',
    borderRadius: '8px',
    cursor: 'pointer',
    fontSize: '14px',
    fontWeight: 600,
  };

  return (
    <div style={listStyle}>
      {posts.map((post) => (
        <Link key={post.id} to={`/post/${post.id}`} style={{ textDecoration: 'none' }}>
          <div style={postStyle}>
            <div style={{ display: 'flex', flexDirection: 'column', flex: 1 }}>
              <span style={{ fontWeight: '600' }}>{post.title}</span>
              <div style={postInfoStyle}>
                <span>작성자: {post.author}</span>
                <span>조회수: {post.views}</span>
                <span>좋아요: {post.likes}</span>
                <span>주제: {post.topic}</span>
              </div>
            </div>
            <button style={buttonStyle}>읽기</button>
          </div>
        </Link>
      ))}
    </div>
  );
}

export default PostList;



