package com.example.service;

import com.example.dao.TagRepository;
import com.example.dao.TypeRepository;
import com.example.handler.NotFoundException;
import com.example.pojo.Tag;
import com.example.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        Optional<Tag> t = tagRepository.findById(id);
        return t.get();
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(converToList(ids));
    }

    private List<Long> converToList(String ids) {
        List<Long> list = new ArrayList<>();
        if(ids != null && !ids.equals("")) {
            String[] idArray = ids.split(",");
            for(String id : idArray) {
                list.add(new Long(id));
            }
        }
        return list;
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogList.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Optional<Tag> t = tagRepository.findById(id);
        if(t == null)
            throw new NotFoundException("不存在该类型");
        BeanUtils.copyProperties(tag, t.get());
        return tagRepository.save(t.get());
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
