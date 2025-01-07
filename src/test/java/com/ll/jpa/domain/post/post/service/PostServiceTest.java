package com.ll.jpa.domain.post.post.service;

import com.ll.jpa.domain.post.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest {
	@Autowired
	private PostService postService;

	@Test
	@DisplayName("글 2개 생성")
	@Transactional
	void t1() {
		postService.write("title1", "content1");
		postService.write("title2", "content2");
	}

	@Test
	@DisplayName("findAll")
	void t2() {
		List<Post> all = this.postService.findAll();
		assertEquals(3, all.size());

		Post post1 = all.get(0);
		assertEquals("title1", post1.getTitle());
	}

	@Test
	@DisplayName("findById")
	void t3() {
		Optional<Post> opPost = this.postService.findById(1L);
		if(opPost.isPresent()) {
			Post post = opPost.get();
			assertEquals("title1", post.getTitle());
		}
	}

	@Test
	@DisplayName("findByTitle")
	void t4() {
		Post post = postService.findByTitle("title1").get(0);
		assertEquals("title1", post.getTitle());
	}

	@Test
	@DisplayName("findByTitleAndContent")
		// SELECT * FROM post WHERE title = 'title1' AND content = 'content1';
	void t5() {
		Post post = postService.findByTitleAndContent("title1", "content1").get(0);
		assertEquals(1, post.getId());
	}

	@Test
	@DisplayName("findByTitleLike")
		// SELECT * FROM post WHERE title LIKE '%2';
	void t6() {
		List<Post> posts = postService.findByTitleLike("%2");
		Post post = posts.get(0);
		assertEquals("title2", post.getTitle());
	}


	@Test
	@DisplayName("findByTitleLikeOrderByIdDesc")
		// SELECT * FROM post WHERE title LIKE 'title%' ORDER BY id DESC;
	void t7() {
		List<Post> posts = postService.findByTitleLikeOrderByIdDesc("title%");
		assertEquals(3, posts.size());

		Post post = posts.get(0);
		assertEquals(3, post.getId());
		assertEquals("title3", post.getTitle());
	}

	@Test
	@DisplayName("findByOrderByIdDesc")
		// SELECT * FROM post ORDER BY id DESC;
	void t8() {
		List<Post> posts = postService.findByOrderByIdDesc();
		assertEquals(3, posts.size());

		Post post = posts.get(0);
		assertEquals(3, post.getId());
		assertEquals("title3", post.getTitle());
	}

	@Test
	@DisplayName("findTop2ByTitleLikeOrderByIdDesc")
		// SELECT * FROM post WHERE title LIKE 'title%' ORDER BY id DESC LIMIT 2;
	void t9() {
		List<Post> posts = postService.findTop2ByTitleLikeOrderByIdDesc("title%");
		assertEquals(2, posts.size());

		Post post = posts.get(0);
		assertEquals(3, post.getId());
		assertEquals("title3", post.getTitle());
	}

	@Test
	@DisplayName("findTop2ByOrderByIdDesc")
		// SELECT * FROM post WHERE ORDER BY id DESC LIMIT 2;
	void t10() {
		List<Post> posts = postService.findTop2ByOrderByIdDesc("title%");
		assertEquals(2, posts.size());

		Post post = posts.get(0);
		assertEquals(3, post.getId());
		assertEquals("title3", post.getTitle());
	}
}
