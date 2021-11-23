package post.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import post.model.dao.PostDao;
import post.model.vo.Post;
import post.model.vo.PostPageData;

public class PostService {

	public int insertPost(Post p) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().insertPost(conn, p);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//내가 받은 쪽지
	public PostPageData selectPostList(int reqPage, String memberId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();

		int numPerPage = 5;
		
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		PostDao dao = new PostDao();
		ArrayList<Post> list = dao.selectPostList(conn, start, end, memberId);
		
		
		int totalCount = dao.selectTotalCount(conn, memberId);
		
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = (totalCount / numPerPage) + 1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination pagination-lg'>";
		
		
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/postList?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&lt;</a></li>";
		}
		
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/postList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/postList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}

			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}
		
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/postList?reqPage=" + pageNo + "'>";
			pageNavi += "&gt;</a></li>";
		}

		pageNavi += "<ul>";
		
		/*
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<form action='/postList' method='get'>";
			pageNavi +=	"<input type='hidden' name='reqPage' value='"+(pageNo-1)+"'>";
			pageNavi += "<input type='hidden' name='memberId' value='"+memberId+"'>";
			pageNavi += "<button type='submit'>&lt;</button></form></li>";
		}
		
		
		
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<form action='/postList' method='get'>";
				pageNavi += "<input type='hidden' name='reqPage' value='"+pageNo+"'>";
				pageNavi += "<input type='hidden' name='memberId' value='"+memberId+"'>";
				pageNavi += "<button type='submit'>"+pageNo+"</button>";
				pageNavi += "</form></li>";
			} else {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<form action='/postList' method='get'>";
				pageNavi += "<input type='hidden' name='reqPage' value='"+pageNo+"'>";
				pageNavi += "<input type='hidden' name='memberId' value='"+memberId+"'>";
				pageNavi += "<button type='submit'>"+pageNo+"</button>";
				pageNavi += "</form></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<form action='/postList' method='post'>";
			pageNavi +=	"<input type='hidden' name='reqPage' value='"+pageNo+"'>";
			pageNavi += "<input type='hidden' name='memberId' value='"+memberId+"'>";
			pageNavi += "<button type='submit'>&gt;</button></form></li>";
		}

		pageNavi += "<ul>";
		*/
		
		
		
		PostPageData ppd = new PostPageData(list, pageNavi, start, totalCount);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public PostPageData selectMySendList(int reqPage, String memberId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();

		int numPerPage = 5;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		PostDao dao = new PostDao();
		ArrayList<Post> myList = dao.selectMySendList(conn, start, end, memberId);
		
		
		int myTotalCount = dao.selectMyTotalCount(conn, memberId);
		
		int totalPage = 0;
		if (myTotalCount % numPerPage == 0) {
			totalPage = myTotalCount / numPerPage;
		} else {
			totalPage = (myTotalCount / numPerPage) + 1;
		}
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination pagination-lg'>";
		
		
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/myPostList?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&lt;</a></li>";
		}
		
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/myPostList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/myPostList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}

			pageNo++;

			if (pageNo > totalPage) {
				break;
			}
		}
		
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/myPostList?reqPage=" + pageNo + "'>";
			pageNavi += "&gt;</a></li>";
		}

		pageNavi += "<ul>";
		
		PostPageData myPpd = new PostPageData(myList, pageNavi, start, myTotalCount);
		
		JDBCTemplate.close(conn);
		return myPpd;
	}
	
	//쪽지 내용
	public Post selectOnePost(int postNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		PostDao dao = new PostDao();
		int result = dao.updateIsRead(conn, postNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Post p = dao.selectOnePost(conn, postNo);
		JDBCTemplate.close(conn);
		return p;
	}
	
	//보낸 쪽지 읽기
	public Post selectMyPost(int postNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Post p = new PostDao().selectOnePost(conn, postNo);
		JDBCTemplate.close(conn);
		return p;
	}

	public int deletePost(int postNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().deletePost(conn, postNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//안읽은 쪽지 수
	public int selectNotReadPost(String memberId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().selectNotReadPost(conn, memberId);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean myPageDeletePost(String postNum) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(postNum, "/");
		
		PostDao dao = new PostDao();
		
		boolean result = true;
		
		while(sT1.hasMoreTokens()) {
			int postNo = Integer.parseInt(sT1.nextToken());
			int result1 = dao.deletePost(conn, postNo);
			
			if(result1 == 0) {
				result = false;
				break;
			}
		}
		
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}



}
