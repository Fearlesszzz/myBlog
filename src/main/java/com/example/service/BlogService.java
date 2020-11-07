package com.example.service;

import com.example.pojo.Blog;
import com.example.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    Page<Blog> listBlog(String query, Pageable pageable);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
