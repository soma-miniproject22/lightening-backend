# lightening-backend

POST API

<API URL>
  
url/posts 전체 포스트 출력
  
url/posts/{postTag} 태그로 구분
  
url/posts/{postType} 모집상태로 구분

< Post >

Long postId; // 포스트 id

Long accountId; // 작정자 id

PostTag postTag; // post 태그

PostType postType; // post 모집완료상태

List<LikeDto> likes; // 관심/참여 인원
  
String postContent; // 내용
  
Date meetDate; // 약속 시간
  
Date recruitEndDate; // 게시 종료 시간
  
int maxCount; // 최대 인원

  
  
< Like 참여자 >

Long accountId; // 참여자
  
LikeType likeType; // 참여 타입(관심, 참가)
  
